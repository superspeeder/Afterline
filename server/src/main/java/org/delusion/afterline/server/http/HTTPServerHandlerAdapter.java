package org.delusion.afterline.server.http;

import io.netty.channel.Channel;

public class HTTPServerHandlerAdapter extends HTTPServerHandler {

    @Override
    protected HTTPResponse onGet(Channel channel, HTTPRequest request) {
        return HTTPResponse.methodNotAllowed;
    }

    @Override
    protected HTTPResponse onHead(Channel channel, HTTPRequest request) {
        return HTTPResponse.methodNotAllowed;
    }

    @Override
    protected HTTPResponse onPost(Channel channel, HTTPRequest request) {
        return HTTPResponse.methodNotAllowed;
    }

    @Override
    protected HTTPResponse onPut(Channel channel, HTTPRequest request) {
        return HTTPResponse.methodNotAllowed;
    }

    @Override
    protected HTTPResponse onDelete(Channel channel, HTTPRequest request) {
        return HTTPResponse.methodNotAllowed;
    }

    @Override
    protected HTTPResponse onConnect(Channel channel, HTTPRequest request) {
        return HTTPResponse.methodNotAllowed;
    }

    @Override
    protected HTTPResponse onOptions(Channel channel, HTTPRequest request) {
        return HTTPResponse.methodNotAllowed;
    }

    @Override
    protected HTTPResponse onTrace(Channel channel, HTTPRequest request) {
        return HTTPResponse.methodNotAllowed;
    }

    @Override
    protected HTTPResponse onPatch(Channel channel, HTTPRequest request) {
        return HTTPResponse.methodNotAllowed;
    }
}
