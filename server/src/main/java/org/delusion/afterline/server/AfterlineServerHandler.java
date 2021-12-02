package org.delusion.afterline.server;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class AfterlineServerHandler extends ChannelInboundHandlerAdapter {

    private AfterlineServer server;

    public AfterlineServerHandler(AfterlineServer server) {
        this.server = server;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Read!");
        ByteBuf in = (ByteBuf)msg;
        try {
            Any pmsg = Any.parseFrom(in.nioBuffer());
            processMessage(pmsg, ctx.channel());
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }


    // code adapted from com.google.protobuf.Any
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
        server.getHandlers(name).forEach(cons -> cons.accept(server, msg, ch));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf buf = PooledByteBufAllocator.DEFAULT.buffer(4);
        buf.writeInt(0xFFFF00FF);
        ctx.writeAndFlush(buf);
        System.out.println("Returned color from activity");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
