package org.delusion.afterline.net;

import com.badlogic.gdx.Gdx;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.ssl.SslProvider;
import org.delusion.afterline.AfterlineClient;
import org.delusion.afterline.net.message.*;
import org.delusion.afterline.net.message.login.FederatedLoginRequest;

import javax.net.ssl.SSLEngine;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class AfterlineNetClient extends Thread {
    private static final String SERVER_ADDR = "afterline.worldofcat.org";
    private static final int SERVER_PORT = 40020;

    private final AfterlineClient client;
    private Channel channel;

    public AfterlineNetClient(AfterlineClient client) {
        initMessages();
        initMessageHandlers();

        this.client = client;
    }

    private void initMessages() {
        Message.register(EchoMessage.class);
        Message.register(FederatedLoginRequest.class);
    }

    private void initMessageHandlers() {
        initAllFrom(Handlers.class);
    }

    private void initAllFrom(Class<?> clazz) {
        // scan class

        Arrays.stream(clazz.getMethods()).filter(method -> method.isAnnotationPresent(SubscribeMessage.class) && Modifier.isStatic(method.getModifiers()) && verifyArgs(method)).forEach(method -> {
            if (method.getAnnotation(SubscribeMessage.class).id() == -1) {
                Class<?> msg = method.getParameterTypes()[1];
                if (msg.isAnnotationPresent(MessageID.class)) AfterlineClientHandler.registerHandler(createMsgHandler(msg, method), msg.getAnnotation(MessageID.class).id());
                else AfterlineClientHandler.registerHandler(createMsgHandler(msg, method), Message.getMessageID(msg));
            } else {
                AfterlineClientHandler.registerHandler((netChannel, message) -> {
                    try {
                        method.invoke(null, netChannel, message);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        AfterlineClient.LOGGER.catching(e);
                        AfterlineClient.LOGGER.error("Failed to invoke message handler {}", method.getName());
                    }
                }, method.getAnnotation(SubscribeMessage.class).id());
            }
        });
    }

    private MessageHandler createMsgHandler(Class<?> msg, Method method) {
        return (netChannel, message) -> {
            try {
                method.invoke(null, netChannel, msg.cast(message));
            } catch (IllegalAccessException | InvocationTargetException e) {
                AfterlineClient.LOGGER.catching(e);
                AfterlineClient.LOGGER.error("Failed to invoke message handler {}", method.getName());
            }
        };
    }

    private boolean verifyArgs(Method method) {
        return method.getParameterTypes().length == 2 && method.getParameterTypes()[0].isAssignableFrom(Channel.class) && Message.class.isAssignableFrom(method.getParameterTypes()[1]);
    }

    @Override
    public void run() {
        try {

            SslContext sslContext = SslContextBuilder.forClient()
                    .sslProvider(SslProvider.OPENSSL)
                    .trustManager(Gdx.files.internal("cert.pem").read()).build();

            SSLEngine sslEngine = sslContext.newEngine(PooledByteBufAllocator.DEFAULT);

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
                        ch.pipeline().addLast(new SslHandler(sslEngine));
                        ch.pipeline().addLast(new AfterlineClientHandler(nc));
                    }
                });

                ChannelFuture f = b.connect(SERVER_ADDR, SERVER_PORT).sync();
                channel = f.channel();

                AfterlineClient.INSTANCE.onConnectToServer();

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

    public void stopClient() {
        channel.close();
    }

    public void postMessage(Message message) {
        ByteBuf buf = message.writeBuffer(channel, channel.alloc());
        try {
            channel.writeAndFlush(buf).sync();
        } catch (InterruptedException e) {
            AfterlineClient.LOGGER.catching(e);
        }
//        ReferenceCountUtil.release(buf);
    }
}
