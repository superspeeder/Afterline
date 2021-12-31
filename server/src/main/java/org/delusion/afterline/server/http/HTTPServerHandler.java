package org.delusion.afterline.server.http;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public abstract class HTTPServerHandler extends ChannelInboundHandlerAdapter {

    private StringBuilder currentMessage = new StringBuilder();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Connection from " + ctx.channel().remoteAddress().toString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        currentMessage.append(buf.getCharSequence(0, buf.readableBytes(), Charsets.UTF_8));
        buf.release();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        String msg = currentMessage.toString();
        System.out.println("READ: " + msg);
        HTTPResponse resp = onRequest(ctx.channel(), new HTTPRequest(msg));
        String rst = resp.toString();
        System.out.println(rst);
        ByteBuf buffer = ctx.alloc().buffer(rst.length() * 2);
        buffer.writeBytes(rst.getBytes());
        ctx.writeAndFlush(buffer);
        currentMessage.delete(0, currentMessage.length());
        System.out.println("Wrote!");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (cause.getMessage().equals("Bad HTTP Request") && cause instanceof RuntimeException) return;
        cause.printStackTrace();
    }

    private HTTPResponse onRequest(Channel channel, HTTPRequest request) {

        switch (request.getMethod()) {

            case GET -> {
                return onGet(channel, request);
            }
            case HEAD -> {
                return onHead(channel, request);
            }
            case POST -> {
                return onPost(channel, request);
            }
            case PUT -> {
                return onPut(channel, request);
            }
            case DELETE -> {
                return onDelete(channel, request);
            }
            case CONNECT -> {
                return onConnect(channel, request);
            }
            case OPTIONS -> {
                return onOptions(channel, request);
            }
            case TRACE -> {
                return onTrace(channel, request);
            }
            case PATCH -> {
                return onPatch(channel, request);
            }
            default -> throw new IllegalStateException("Unexpected request method: " + request.getMethod());
        }
    }

    protected abstract HTTPResponse onGet(Channel channel, HTTPRequest request);
    protected abstract HTTPResponse onHead(Channel channel, HTTPRequest request);
    protected abstract HTTPResponse onPost(Channel channel, HTTPRequest request);
    protected abstract HTTPResponse onPut(Channel channel, HTTPRequest request);
    protected abstract HTTPResponse onDelete(Channel channel, HTTPRequest request);
    protected abstract HTTPResponse onConnect(Channel channel, HTTPRequest request);
    protected abstract HTTPResponse onOptions(Channel channel, HTTPRequest request);
    protected abstract HTTPResponse onTrace(Channel channel, HTTPRequest request);
    protected abstract HTTPResponse onPatch(Channel channel, HTTPRequest request);

}
