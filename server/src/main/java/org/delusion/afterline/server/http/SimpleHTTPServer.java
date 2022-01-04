package org.delusion.afterline.server.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.ssl.SslProvider;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;
import javax.net.ssl.X509TrustManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetSocketAddress;
import java.nio.file.Path;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.function.Supplier;

public class SimpleHTTPServer {
    private EventLoopGroup bossGroup, workerGroup;
    private InetSocketAddress bindAddress;

    public static final Logger LOGGER = LogManager.getLogger("HTTP");

    public SimpleHTTPServer(Supplier<HTTPServerHandler> handler, InetSocketAddress bindAddress) throws InterruptedException, SSLException {
        this(handler, bindAddress, false);
    }

    public SimpleHTTPServer(Supplier<HTTPServerHandler> handler, InetSocketAddress bindAddress, boolean tls) throws InterruptedException, SSLException {
        this.bindAddress = bindAddress;
        this.bossGroup = new NioEventLoopGroup();
        this.workerGroup = new NioEventLoopGroup();
        LOGGER.error("test");

        if (tls) {

            LOGGER.info("Starting HTTPS Server at {}", bindAddress);

            SslContext sslContext = SslContextBuilder
                    .forServer(Path.of(System.getenv("AFTERLINE_CERT")).toFile(), Path.of(System.getenv("AFTERLINE_KEY")).toFile() )
                    .sslProvider(SslProvider.OPENSSL)
                    .trustManager(new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    })
                    .build();


            try {
                ServerBootstrap b = new ServerBootstrap();
                b.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            public void initChannel(SocketChannel ch) throws Exception {
                                //  uncomment to allow the server to speak TLS
                                SSLEngine sslEngine = sslContext.newEngine(PooledByteBufAllocator.DEFAULT);
                                ch.pipeline().addLast(new SslHandler(sslEngine));
                                ch.pipeline().addLast(handler.get());
                            }
                        })
                        .option(ChannelOption.SO_BACKLOG, 128)
                        .childOption(ChannelOption.SO_KEEPALIVE, true)
                ;

                ChannelFuture f = b.bind(bindAddress).sync();
                LOGGER.info("HTTPS Server started at {}", bindAddress);

                f.channel().closeFuture().sync();
            } finally {
                workerGroup.shutdownGracefully();
                bossGroup.shutdownGracefully();
                LOGGER.info("HTTPS Server at {} has shut down gracefully", bindAddress);
            }
        } else {
            LOGGER.info("Starting HTTP Server at {}", bindAddress);

            try {
                ServerBootstrap b = new ServerBootstrap();
                b.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            public void initChannel(SocketChannel ch) throws Exception {
                                //  uncomment to allow the server to speak TLS
                                //                            ch.pipeline().addLast(new SslHandler(sslEngine));
                                ch.pipeline().addLast(handler.get());
                            }
                        })
                        .option(ChannelOption.SO_BACKLOG, 128)
                        .childOption(ChannelOption.SO_KEEPALIVE, true)
                ;

                ChannelFuture f = b.bind(bindAddress).sync();
                LOGGER.info("HTTP Server started at {}", bindAddress);
                
                f.channel().closeFuture().sync();
            } finally {
                workerGroup.shutdownGracefully();
                bossGroup.shutdownGracefully();
                LOGGER.info("HTTP Server at {} has shut down gracefully", bindAddress);
            }
        }
    }

    public InetSocketAddress getBindAddress() {
        return bindAddress;
    }

}
