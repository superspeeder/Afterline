package org.delusion.afterline.net;

import com.badlogic.gdx.graphics.Color;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.concurrent.EventExecutorGroup;

public class AfterlineClientHandler extends ChannelInboundHandlerAdapter {
    private final AfterlineNetClient client;

    public AfterlineClientHandler(AfterlineNetClient afterlineNetClient) {

        client = afterlineNetClient;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf col = (ByteBuf) msg;
        int c = col.readInt();
        client.getClient().bg(new Color(c));
        col.release();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
