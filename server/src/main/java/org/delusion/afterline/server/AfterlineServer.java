package org.delusion.afterline.server;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.delusion.afterline.proto.GetColorRequest;
import org.delusion.afterline.proto.GetColorResponse;
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


public class AfterlineServer {
    private static final int MAX_PORT = 65535;
    private static final int DEFAULT_PORT = 9000;

    private int port;
    private static Map<String, List<TriConsumer<AfterlineServer, Any, Channel>>> handlers = new HashMap<>();
    private Thread httpThread;


    private void initMessageHandlers() {
        addHandler(AfterlineServer::onColorReq, GetColorRequest.class);
    }


    public AfterlineServer(int port) throws Exception {
        int httpPort = Integer.parseInt(System.getenv("AFTERLINE_HTTP_PORT"));
        httpThread = new Thread(() -> TestHTTPServer.create(httpPort, false));

        this.port = port;
        initMessageHandlers();

        System.out.println("Running server on port " + this.port);


        SslContext sslContext = SslContextBuilder
                .forServer(Path.of(System.getenv("AFTERLINE_CERT")).toFile(), Path.of(System.getenv("AFTERLINE_KEY")).toFile())
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
                    }).build();

        SSLEngine sslEngine = sslContext.newEngine(PooledByteBufAllocator.DEFAULT);


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
//  uncomment to allow the server to speak TLS
                            ch.pipeline().addLast(new SslHandler(sslEngine));
                            ch.pipeline().addLast(new AfterlineServerHandler(srv));
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind(this.port).sync();

            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
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

    static Random random = new Random();

    private void onColorReq(GetColorRequest req, Channel channel) {
        post(GetColorResponse.newBuilder().setColor(random.nextInt()).build(), channel);
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

    public void post(Message message, Channel channel) {
        Any anymsg = Any.pack(message);
        System.out.println(getTypeNameFromTypeUrl(anymsg.getTypeUrl()));

        channel.writeAndFlush(Unpooled.wrappedBuffer(anymsg.toByteArray()));

    }

    private static <T extends Message> void addHandler(TriConsumer<AfterlineServer, T, Channel> consumer, Class<T> cls) {

        handlers.putIfAbsent(cls.getName(), new ArrayList<>());
        handlers.get(cls.getName()).add((afterlineServer, message, channel) -> {
            try {
                T unpack = message.unpack(cls);
                consumer.accept(afterlineServer, unpack, channel);
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        });
    }

    public List<TriConsumer<AfterlineServer, Any, Channel>> getHandlers(String name) {
        return handlers.getOrDefault(name, List.of());
    }
}
