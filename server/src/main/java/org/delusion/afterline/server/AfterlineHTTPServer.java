package org.delusion.afterline.server;

import io.netty.channel.Channel;

import org.apache.http.client.protocol.RequestExpectContinue;
import org.delusion.afterline.server.http.HTTPRequest;
import org.delusion.afterline.server.http.HTTPResponse;
import org.delusion.afterline.server.http.HTTPServerHandlerAdapter;
import org.delusion.afterline.server.http.HTTPStatusCode;
import org.delusion.afterline.server.http.SimpleHTTPClient;
import org.delusion.afterline.server.http.SimpleHTTPServer;
import org.delusion.afterline.server.util.Utils;

import javax.net.ssl.SSLException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;

public class AfterlineHTTPServer {
    private Instant startupTime;

    private AfterlineHTTPServer() {
        startupTime = Instant.now();
    }
    

    String hrUptime() {
        Duration ut = Duration.between(startupTime, Instant.now());
        long days_ = ut.toDays();
        int hours = ut.toHoursPart();
        int minutes = ut.toMinutesPart();
        double seconds = ut.toSecondsPart() + ut.toMillisPart() / 1000.0;
        
        int days = (int)(days_ % 365);
        long years_ = (long)Math.floorDiv(days_, 365);
        long millenia = (long)Math.floorDiv(years_, (long)1e3);
        int years = (int)(years_ % (long)1e3);

        StringBuilder sb = new StringBuilder();

        if (millenia == 1) {
            sb.append("1 millenium, ");
        } else {
            sb.append(millenia).append(" millenia, ");   
        }

        if (years == 1) {
            sb.append("1 year, ");
        } else {
            sb.append(years).append(" years, ");   
        }

        if (days == 1) {
            sb.append("1 day, ");
        } else {
            sb.append(days).append(" days, ");   
        }

        if (hours == 1) {
            sb.append("1 hour, ");
        } else {
            sb.append(hours).append(" hours, ");   
        }

        if (minutes == 1) {
            sb.append("1 minute, ");
        } else {
            sb.append(minutes).append(" minutes, ");   
        }

        if (seconds == 1.0) {
            sb.append("and 1.000 second");
        } else {
            sb.append(String.format("and %.03f seconds", seconds));
        }
        return sb.toString();
    }

    static class TestHandler extends HTTPServerHandlerAdapter {

        private AfterlineHTTPServer srv;

        TestHandler(AfterlineHTTPServer srv) {
            this.srv = srv;
        }



        @Override
        protected HTTPResponse onGet(Channel channel, HTTPRequest request) {
            URL url = request.getPathURL("https://afterline.worldofcat.org");


            if (url.getPath().equals("/")) {
                try {
                    new SimpleHTTPClient(new URI("https://google.com"), new HTTPRequest(HTTPRequest.Method.GET).setPath("/"), (ch, resp) -> {
                        SimpleHTTPServer.LOGGER.info(resp.getContent());
                        ch.close();
                    });
                } catch (URISyntaxException e) {
                    SimpleHTTPServer.LOGGER.catching(e);
                }

                return HTTPResponse.createHTML("<!DOCTYPE html><html><head><title>Test Page</title></head><body style=\"font-family: 'Roboto', sans-serif\"><h1>Hello!</h1><h2>Afterline has been online for <span style=\"font-family: 'Lucida Console', 'Courier New', monospace\">" + srv.hrUptime() + "</span></body></html>");
            } else if (url.getPath().equals("/testRedirect"))
                return HTTPResponse.redirectTo("https://example.com");
            else if (url.getPath().equals("/authredirect")) {
                if (ClientSession.handleAuthCallback(channel, url.getQuery(), request.getClientIP()))
                    return HTTPResponse.createHTML("<!DOCTYPE html><html><head><title>Authentication Complete!</title></head><body style=\"font-family: 'Roboto', sans-serif\"><h1>Authentication Successful!</h1><h2>You can now return to the AfterlineEngine App!</h2></body></http>");
                else
                    return new HTTPResponse().setStatusCode(HTTPStatusCode.Unauthorized);
            }
            return HTTPResponse.notFound;
        }
    }

    public static void create(int port, boolean ssl) {
        AfterlineHTTPServer srv = new AfterlineHTTPServer();

        try {
            new SimpleHTTPServer(() -> new TestHandler(srv), new InetSocketAddress("127.0.0.1", port), ssl);
        } catch (InterruptedException | SSLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException, SSLException {
        AfterlineHTTPServer srv = new AfterlineHTTPServer();
        new SimpleHTTPServer(() -> new TestHandler(srv), new InetSocketAddress("localhost", 8080), true);
    }
}
