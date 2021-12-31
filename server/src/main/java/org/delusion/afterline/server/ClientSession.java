package org.delusion.afterline.server;

import io.netty.channel.Channel;
import org.apache.http.client.methods.RequestBuilder;
import org.delusion.afterline.server.util.Utils;
import org.delusion.afterline.proto.FederatedLoginRequest;
import org.delusion.afterline.proto.FederatedLoginResponse;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ClientSession {

//    //////////////////////////////////////////
//    //        BEGIN IPC WATCH THREAD        //
//    //////////////////////////////////////////
//
//    private static Thread watchThread;
//    private static final int WATCH_THREAD_PORT = 20019;
//    private static Queue<String> ipcOutgoing = new PriorityBlockingQueue<>();
//
//    public static void startWatchThread() {
//        if (watchThread != null) watchThread.interrupt();
//        watchThread = new Thread(() -> {
//            try {
//                try (ServerSocket sock = new ServerSocket(WATCH_THREAD_PORT)) {
//                    Socket clientSock = sock.accept();
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
//                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSock.getOutputStream()));
//                    StringBuilder inStr = new StringBuilder();
//                    while (clientSock.isConnected()) {
//                        // cycle in/out
//
//                        ipcCycle(inStr, reader, writer);
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        watchThread.start();
//    }
//
//    private static void ipcCycle(StringBuilder inStr, BufferedReader reader, BufferedWriter writer) throws IOException {
//        while (reader.ready()) { // data to read
//            int i = reader.read();
//            String s = Character.toString(i);
//            if (isEnd(s)) {
//                handleInputStr(inStr.toString());
//                inStr.delete(0, inStr.length()); // clear string builder
//            } else {
//                inStr.append(s);
//            }
//
//            if (!ipcOutgoing.isEmpty()) {
//                writer.write(ipcOutgoing.poll() + '\0'); // null terminate so the receiver knows when this msg ends
//            }
//        }
//
//    }
//
//    private enum IPCActionIncoming {
//        FEDERATED_AUTH_1(ClientSession::onAuth1Callback_raw);
//
//        private Consumer<List<String>> callback;
//
//        IPCActionIncoming(Consumer<List<String>> callback) {
//
//            this.callback = callback;
//        }
//
//        void onRecv(List<String> args) { callback.accept(args); }
//    }
//
//    private enum IPCActionOutgoing {
//        FEDERATED_AUTH_VERIFY,
//        FEDERATED_AUTH_START
//    }
//
//
//    /**
//     *
//     *
//     * Expected format
//     *
//     *
//     * a semicolon separated list of string
//     * first string corresponds to a given {@link IPCActionIncoming}
//     *
//     *
//     *
//     */
//    private static void handleInputStr(String s) {
//        // parse this string
//        String[] components = s.strip().split(";");
//
//        String action = components[0];
//        List<String> args = Arrays.stream(components).skip(1).collect(Collectors.toList());
//
//        try {
//            IPCActionIncoming ipcAction = IPCActionIncoming.valueOf(action);
//            try {
//                ipcAction.onRecv(args);
//            } catch (IllegalArgumentException e2) {
//                e2.printStackTrace();
//                System.err.println("This is likely caused because there weren't enough parameters for the IPC action '" + action + "'");
//            }
//        } catch (IllegalArgumentException e) {
//            System.err.println("[Watch Thread] There is no IPC action called '" + action + "'");
//        }
//    }
//
//    private static boolean isEnd(String s) {
//        return Objects.equals(s, "\0");
//    }
//
//    /**
//     * Args should contain:
//     *  1. source addr
//     *  2. state value
//     */
//    private static void onAuth1Callback_raw(List<String> args) {
//        if (args.size() != 2) throw new IllegalArgumentException("2 arguments are required for IPC action 'FEDERATED_AUTH_1'");
//        String srcAddr_str = args.get(0);
//        String state_val = args.get(1);
//
//        // check to maintain CSRF protection
//
//        boolean verified = sessionCSRFStates.get(srcAddr_str).equals(state_val);
//        if (verified) {
//            ipcOutgoing.add("FEDERATED_AUTH_VERIFY;"+srcAddr_str+";"+"True");
//        } else {
//            ipcOutgoing.add("FEDERATED_AUTH_VERIFY;"+srcAddr_str+";"+"False"); // receiver should kill the connection and internally flag the sender IP (for firewalls, not yet ready to be implemented, for now just kill conn).
//        }
//    }
//
//    private static void onAuthCompleteCallback_raw(List<String> args) {
//        onAuthCompleted();
//    }

    //////////////////////////////////////////
    //         BEGIN FEDERATED AUTH         //
    //////////////////////////////////////////

    // TODO: load the credentials from file under env var AFTERLINE_GAPI_CREDENTIALS

    private static final URI GOOGLE_AUTH_URI = URI.create("https://accounts.google.com/o/oauth2/v2/auth");
    private static final URI REDIRECT_URL = URI.create("http://afterline.world-of-cat.club/authredirect");
    private static Map<String, String> sessionCSRFStates = new ConcurrentHashMap<>(); // ip -> csrf token. This is done with cookies in a web browser, but this is more of a cross-server forgery token to prevent users other than the verified user to send this message, couples with the fact that future communication w/ server will be TLS encrypted using a certificate.
    private static Map<String, ClientSession> sessions = new ConcurrentHashMap<>();

    public static void beginFederatedAuth(AfterlineServer server, FederatedLoginRequest req, Channel ch) {

        String state_ = new BigInteger(130, new SecureRandom()).toString(32);
        sessions.put(Utils.getBasicIPString(ch), new ClientSession(ch));
        sessionCSRFStates.put(Utils.getBasicIPString(ch), state_);

        FederatedLoginResponse flr = FederatedLoginResponse.newBuilder().setLink(createAuthLink(state_)).build();

        server.post(flr, ch);
    }

    private static void onAuthCompleted() {


    }

    private static String createAuthLink(String state_) {
        return RequestBuilder.get(GOOGLE_AUTH_URI)
                .addParameter("client_id", System.getenv("AFTERLINE_CLIENT_ID"))
                .addParameter("redirect_uri", REDIRECT_URL.toString())
                .addParameter("state", state_)
                .addParameter("response_type", "code")
                .addParameter("scope", "openid email profile")
                .getUri().toString();
    }

    //////////////////////////////////////////
    //         BEGIN CLIENT SESSION         //
    //////////////////////////////////////////

    private Channel channel;



    public ClientSession(Channel ch) {
        this.channel = ch;
    }

    private void endSession() {
    }


}
