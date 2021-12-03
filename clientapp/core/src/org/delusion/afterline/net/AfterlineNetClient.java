package org.delusion.afterline.net;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.delusion.afterline.AfterlineClient;
import org.delusion.afterline.proto.GetColorResponse;
import org.delusion.afterline.util.TriConsumer;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class AfterlineNetClient extends Thread {
    private static final String SERVER_ADDR = "localhost";
    private static final int SERVER_PORT = 9000;

    private final AfterlineClient client;
    private Channel channel;
    private static Map<String, List<BiConsumer<AfterlineClient, Any>>> handlers = new HashMap<>();


    public AfterlineNetClient(AfterlineClient client) {
        this.client = client;
        initMessageHandlers();
    }

    @Override
    public void run() {
        try {

            /* uncomment to allow the client to speak tls
            SslContext sslContext = SslContextBuilder.forClient()
                    .sslProvider(SslProvider.OPENSSL)
                    .build();

            SSLEngine sslEngine = sslContext.newEngine(PooledByteBufAllocator.DEFAULT);
             */

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
//  uncomment to allow the client to speak TLS
//                        ch.pipeline().addLast(new SslHandler(sslEngine));
                        ch.pipeline().addLast(new AfterlineClientHandler(nc));
                    }
                });

                ChannelFuture f = b.connect(SERVER_ADDR, SERVER_PORT).sync();

                channel = f.channel();
                channel.closeFuture().sync();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                workerGroup.shutdownGracefully();
                System.out.println("Gracefully closed connection");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AfterlineClient getClient() {
        return client;
    }

    public Channel getChannel() {
        return channel;
    }

    public void stopServer() {
        channel.close();
    }

    // code taken from com.google.protobuf.Any
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

    public void post(Message message) {
        Any anymsg = Any.pack(message);
        channel.writeAndFlush(Unpooled.wrappedBuffer(anymsg.toByteArray()));
    }

    private <T extends Message> void addHandler(TriConsumer<AfterlineClient, T, Channel> consumer, Class<T> cls) {
        handlers.putIfAbsent(cls.getName(), new ArrayList<>());
        handlers.get(cls.getName()).add((afterlineServer, message) -> {
            try {
                T unpack = message.unpack(cls);
                consumer.accept(client, unpack, channel);
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        });
    }

    public List<BiConsumer<AfterlineClient, Any>> getHandlers(String name) {
        return handlers.getOrDefault(name, List.of());
    }

    private void initMessageHandlers() {
    }
}
