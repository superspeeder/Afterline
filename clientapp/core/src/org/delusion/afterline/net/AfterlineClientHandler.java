package org.delusion.afterline.net;

import com.badlogic.gdx.graphics.Color;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutorGroup;

public class AfterlineClientHandler extends ChannelInboundHandlerAdapter {
    private final AfterlineNetClient client;

    public AfterlineClientHandler(AfterlineNetClient afterlineNetClient) {

        client = afterlineNetClient;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf)msg;
        try {
            Any pmsg = Any.parseFrom(in.nioBuffer());
            processMessage(pmsg, ctx.channel());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    private static String getTypeNameFromTypeUrl(String typeUrl) {
        int pos = typeUrl.lastIndexOf('/');
        return pos == -1 ? "" : fixProtoClassName(typeUrl.substring(pos + 1));
    }

    private static String fixProtoClassName(String s) {
        if (s.startsWith("afterline.") && s.lastIndexOf('.') == s.indexOf('.')) {
            return "org.delusion.afterline.proto." + s.substring(10);
        }
        return s;
    }

    private void processMessage(Any msg, Channel ch) throws InvalidProtocolBufferException {
        String name = getTypeNameFromTypeUrl(msg.getTypeUrl());
        client.getHandlers(name).forEach(cons -> cons.accept(client.getClient(), msg));
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
