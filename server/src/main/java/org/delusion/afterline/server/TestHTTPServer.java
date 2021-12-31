package org.delusion.afterline.server;

import io.netty.channel.Channel;
import org.delusion.afterline.server.http.HTTPRequest;
import org.delusion.afterline.server.http.HTTPResponse;
import org.delusion.afterline.server.http.HTTPServerHandlerAdapter;
import org.delusion.afterline.server.http.SimpleHTTPServer;

import javax.net.ssl.SSLException;
import java.net.InetSocketAddress;

public class TestHTTPServer {
    static class TestHandler extends HTTPServerHandlerAdapter {
        @Override
        protected HTTPResponse onGet(Channel channel, HTTPRequest request) {
            if (request.getPath().equals("/"))
                return HTTPResponse.createHTML("<!DOCTYPE html><html><head><title>Test Page</title></head><body><h1>Hello!</h1></body></html>");
            if (request.getPath().equals("/testRedirect"))
                return HTTPResponse.redirectTo("https://example.com");
            return HTTPResponse.notFound;
        }
    }

    public static void create(int port, boolean ssl) {
        try {
            new SimpleHTTPServer(TestHandler::new, new InetSocketAddress("127.0.0.1", port), ssl);
        } catch (InterruptedException | SSLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException, SSLException {
        new SimpleHTTPServer(TestHandler::new, new InetSocketAddress("localhost", 8080), true);
    }
}
