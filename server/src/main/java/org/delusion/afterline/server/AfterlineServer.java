package org.delusion.afterline.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.delusion.afterline.server.message.Message;
import org.delusion.afterline.server.util.TriConsumer;
import io.netty.buffer.PooledByteBufAllocator;

import java.io.IOException;
import java.util.*;
import java.nio.file.Path;
import java.lang.Integer;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.ssl.SslProvider;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class AfterlineServer {
    private static final int MAX_PORT = 65535;
    private static final int DEFAULT_PORT = 40020;

    public static final Logger LOGGER = LogManager.getLogger("Main");

    private int port;
    // private static Map<String, List<TriConsumer<AfterlineServer, Any, Channel>>> handlers = new HashMap<>();
    private Thread httpThread;


    // private void initMessageHandlers() {
    //     addHandler(AfterlineServer::onColorReq, GetColorRequest.class);
    // }


    public AfterlineServer(int port) throws Exception {
        Message.initMessages();
        AfterlineServerHandler.initHandlers();


        int httpPort = Integer.parseInt(System.getenv("AFTERLINE_HTTP_PORT"));
        httpThread = new Thread(() -> AfterlineHTTPServer.create(httpPort, false));
        httpThread.start();

        this.port = port;
        // initMessageHandlers();

        LOGGER.info("Running Afterline Server on port {}", port);
        LOGGER.info("Initializing SSL");

        SslContext sslContext = SslContextBuilder
                .forServer(Path.of(System.getenv("AFTERLINE_CERT")).toFile(), Path.of(System.getenv("AFTERLINE_KEY")).toFile())
                .sslProvider(SslProvider.OPENSSL)
                .trustManager(Path.of(System.getenv("AFTERLINE_CERT")).toFile()).build();

        LOGGER.info("SSL Initialized");

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        final AfterlineServer srv = this;
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            SSLEngine sslEngine = sslContext.newEngine(PooledByteBufAllocator.DEFAULT);
                            ch.pipeline().addLast(new SslHandler(sslEngine));
                            ch.pipeline().addLast(new AfterlineServerHandler(srv));
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind("0.0.0.0", this.port).sync();
            LOGGER.info("Afterline server started");

            f.channel().closeFuture().sync();
            LOGGER.info("Beginning shutdown");
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            httpThread.interrupt();
            LOGGER.info("Afterline server shutdown gracefully");
        }
    }

    public static void main(String[] args) throws Exception {


        int port = DEFAULT_PORT;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
            if (port < 0 || port > MAX_PORT) {
                port = DEFAULT_PORT;
            }
        }

        new AfterlineServer(port);
    }

    // static Random random = new Random();

    // private void onColorReq(GetColorRequest req, Channel channel) {
    //     post(GetColorResponse.newBuilder().setColor(random.nextInt()).build(), channel);
    // }

    // // code adapted from com.google.protobuf.Any
    // private static String getTypeNameFromTypeUrl(String typeUrl) {
    //     int pos = typeUrl.lastIndexOf('/');
    //     return pos == -1 ? "" : fixProtoClassName(typeUrl.substring(pos + 1));
    // }

    // private static String fixProtoClassName(String s) {
    //     if (s.startsWith("afterline.") && s.lastIndexOf('.') == s.indexOf('.')) {
    //         return "org.delusion.afterline.proto." + s.substring(10);
    //     }
    //     return s;
    // }

    // public void post(Message message, Channel channel) {
    //     Any anymsg = Any.pack(message);
    //     System.out.println(getTypeNameFromTypeUrl(anymsg.getTypeUrl()));

    //     channel.writeAndFlush(Unpooled.wrappedBuffer(anymsg.toByteArray()));

    // }

    // private static <T extends Message> void addHandler(TriConsumer<AfterlineServer, T, Channel> consumer, Class<T> cls) {

    //     handlers.putIfAbsent(cls.getName(), new ArrayList<>());
    //     handlers.get(cls.getName()).add((afterlineServer, message, channel) -> {
    //         try {
    //             T unpack = message.unpack(cls);
    //             consumer.accept(afterlineServer, unpack, channel);
    //         } catch (InvalidProtocolBufferException e) {
    //             e.printStackTrace();
    //         }
    //     });
    // }

    // public List<TriConsumer<AfterlineServer, Any, Channel>> getHandlers(String name) {
    //     return handlers.getOrDefault(name, List.of());
    // }
}
