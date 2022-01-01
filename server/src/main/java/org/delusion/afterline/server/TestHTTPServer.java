package org.delusion.afterline.server;

import io.netty.channel.Channel;
import org.delusion.afterline.server.http.HTTPRequest;
import org.delusion.afterline.server.http.HTTPResponse;
import org.delusion.afterline.server.http.HTTPServerHandlerAdapter;
import org.delusion.afterline.server.http.SimpleHTTPServer;

import javax.net.ssl.SSLException;
import java.net.InetSocketAddress;
import java.time.Duration;
import java.time.Instant;

public class TestHTTPServer {
    private Instant startupTime;

    private TestHTTPServer() {
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

        private TestHTTPServer srv;

        TestHandler(TestHTTPServer srv) {
            this.srv = srv;
        }



        @Override
        protected HTTPResponse onGet(Channel channel, HTTPRequest request) {
            if (request.getPath().equals("/"))
                return HTTPResponse.createHTML("<!DOCTYPE html><html><head><title>Test Page</title></head><body style=\"font-family: 'Roboto', sans-serif\"><h1>Hello!</h1><h2>Afterline has been online for <span style=\"font-family: 'Lucida Console', 'Courier New', monospace\">" + srv.hrUptime() + "</span></body></html>");
            if (request.getPath().equals("/testRedirect"))
                return HTTPResponse.redirectTo("https://example.com");
            return HTTPResponse.notFound;
        }
    }

    public static void create(int port, boolean ssl) {
        TestHTTPServer srv = new TestHTTPServer();

        try {
            new SimpleHTTPServer(() -> new TestHandler(srv), new InetSocketAddress("127.0.0.1", port), ssl);
        } catch (InterruptedException | SSLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException, SSLException {
        TestHTTPServer srv = new TestHTTPServer();
        new SimpleHTTPServer(() -> new TestHandler(srv), new InetSocketAddress("localhost", 8080), true);
    }
}
