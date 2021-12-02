package org.delusion.afterline.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.*;
import org.delusion.afterline.AfterlineClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import java.security.Provider;

public class AfterlineNetClient extends Thread {
    private static final String SERVER_ADDR = "localhost";
    private static final int SERVER_PORT = 9000;

    private final AfterlineClient client;

    public AfterlineNetClient(AfterlineClient client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {


//            SslContext sslContext = SslContextBuilder.forClient()
//                    .sslProvider(SslProvider.OPENSSL)
//                    .keyManager()
//                    .build();
//
//            SSLEngine sslEngine = sslContext.newEngine(PooledByteBufAllocator.DEFAULT);


            EventLoopGroup workerGroup = new NioEventLoopGroup();
            final AfterlineNetClient nc = this;
            try {
                Bootstrap b = new Bootstrap();
                b.group(workerGroup);
                b.channel(NioSocketChannel.class);
                b.option(ChannelOption.SO_KEEPALIVE, true);
                b.handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
//                        ch.pipeline().addLast(new SslHandler(sslEngine));
                        ch.pipeline().addLast(new AfterlineClientHandler(nc));
                    }
                });

                ChannelFuture f = b.connect(SERVER_ADDR, SERVER_PORT).sync();

                f.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                workerGroup.shutdownGracefully();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AfterlineClient getClient() {
        return client;
    }
}
