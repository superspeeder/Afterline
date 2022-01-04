package org.delusion.afterline.server;

import io.netty.channel.Channel;
import org.apache.http.client.methods.RequestBuilder;
import org.delusion.afterline.server.http.HTTPHeaderField;
import org.delusion.afterline.server.http.HTTPRequest;
import org.delusion.afterline.server.http.SimpleHTTPClient;
import org.delusion.afterline.server.http.SimpleHTTPServer;
import org.delusion.afterline.server.util.Utils;
import org.json.JSONObject;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.function.Consumer;
import java.util.stream.Collectors;


public class ClientSession {

    //////////////////////////////////////////
    //         BEGIN FEDERATED AUTH         //
    //////////////////////////////////////////

    private static final URI GOOGLE_AUTH_URI = URI.create("https://accounts.google.com/o/oauth2/v2/auth");
    private static final URI REDIRECT_URL = URI.create("https://afterline.worldofcat.org/authredirect");
    private static final URI GOOGLE_TOKEN_URI = URI.create("https://oauth2.googleapis.com/token");
    private static final URI TOKEN_REDIRECT_URL = URI.create("https://afterline.worldofcat.org/tokenredirect");
    private static Map<String, String> sessionCSRFStates = new ConcurrentHashMap<>(); // ip -> csrf token. This is done with cookies in a web browser, but this is more of a cross-server forgery token to prevent users other than the verified user to send this message, couples with the fact that future communication w/ server will be TLS encrypted using a certificate.
    private static Map<String, ClientSession> sessions = new ConcurrentHashMap<>();

    public static String createStateString(Channel ch) {
        String state_ = new BigInteger(130, new SecureRandom()).toString(32);
        sessions.put(((InetSocketAddress) ch.remoteAddress()).getAddress().getHostAddress(), new ClientSession(ch));
        sessionCSRFStates.put(((InetSocketAddress) ch.remoteAddress()).getAddress().getHostAddress(), state_);
        return state_;
    }

    public static String createAuthLink(Channel ch) {
        return RequestBuilder.get(GOOGLE_AUTH_URI)
                .addParameter("client_id", System.getenv("AFTERLINE_CLIENT_ID"))
                .addParameter("redirect_uri", REDIRECT_URL.toString())
                .addParameter("state", createStateString(ch))
                .addParameter("response_type", "code")
                .addParameter("scope", "openid")
                .build().getURI().toString();
    }

    //////////////////////////////////////////
    //         BEGIN CLIENT SESSION         //
    //////////////////////////////////////////

    private Channel channel;
    private String userID;

    public ClientSession(Channel ch) {
        this.channel = ch;
    }

    public static boolean handleAuthCallback(Channel ch, String query, String ip) {
        Map<String, String> qMap = Utils.parseHTTPQuery(query);
        if (!sessionCSRFStates.containsKey(ip)) {
            SimpleHTTPServer.LOGGER.warn("Received an auth callback from an IP which hasn't originated an auth request. IP: {}", ip);
            return false;
        }

        sessionCSRFStates.remove(ip);
        
        // obtain the token

        StringBuilder content = new StringBuilder();
        content.append("code=").append(qMap.get("code")).append("&\r\n");
        content.append("client_id=").append(System.getenv("AFTERLINE_CLIENT_ID")).append("&\r\n");
        content.append("client_secret=").append(getClientSecret()).append("&\r\n");
        content.append("redirect_uri=").append(TOKEN_REDIRECT_URL).append("&\r\n");
        content.append("grant_type=authorization_code");


        new SimpleHTTPClient(GOOGLE_TOKEN_URI, new HTTPRequest(HTTPRequest.Method.POST)
                .setPath(GOOGLE_TOKEN_URI.getPath())
                .setHeader(HTTPHeaderField.ContentType, "application/x-www-form-urlencoded")
                .setContent(content.toString()),
                
                (ch_, resp) -> {
                    SimpleHTTPServer.LOGGER.info(resp.getContent());

                    String jsonData = resp.getContent();
                    JSONObject obj = new JSONObject(jsonData);
                    ClientSession csession = sessions.get(ip);
                    JSONObject jwt_id = obj.getJSONObject("id_token");
                    csession.setUserIdentifier(jwt_id.getString("sub"));

                    ch_.close();
                });


        return true;
    }

    private void setUserIdentifier(String userID) {
        this.userID = userID;
    }

    private static String getClientSecret() {
        String cs = null;
        try(BufferedReader br = new BufferedReader(new FileReader(System.getenv("AFTERLINE_CS_FILE")))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
        
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String text = sb.toString();

            JSONObject obj = new JSONObject(text);
            JSONObject web_obj = obj.getJSONObject("web");
            cs = web_obj.getString("client_secret");
        } catch (IOException e) {
            AfterlineServer.LOGGER.catching(e);
        }

        return cs;
    }


}
