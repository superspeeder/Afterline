package org.delusion.afterline.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.print.attribute.HashDocAttributeSet;

import org.delusion.afterline.server.message.CastingMessageHandler;
import org.delusion.afterline.server.message.EchoMessage;
import org.delusion.afterline.server.message.Message;
import org.delusion.afterline.server.message.MessageHandler;
import org.delusion.afterline.server.message.MessageHandlers;
import org.delusion.afterline.server.message.MessageID;
import org.delusion.afterline.server.message.SubscribeMessage;
import org.delusion.afterline.server.message.login.FederatedLoginRequest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class AfterlineServerHandler extends ChannelInboundHandlerAdapter {

    private static Map<Integer, List<MessageHandler> > messageHandlers = new HashMap<>();

    private AfterlineServer server;

    public AfterlineServerHandler(AfterlineServer server) {
        this.server = server;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        AfterlineServer.LOGGER.info("received some data");
        ByteBuf in = (ByteBuf)msg;
        try {

            int msgID = in.readInt();
            long msgSize = in.readInt();

            Channel ch = ctx.channel();
            Optional<Message> messageOpt = Optional.ofNullable(Message.create(msgID, msgSize, in));
            messageOpt.ifPresentOrElse(msg_ -> {
                messageHandlers.getOrDefault(msgID, List.of()).forEach(mh -> mh.handle(ch, msg_));
            }, () -> AfterlineServer.LOGGER.error("Cannot process message: failed to create a message object from message data"));
            

            // Any pmsg = Any.parseFrom(in.nioBuffer());
            // processMessage(pmsg, ctx.channel());

        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        AfterlineServer.LOGGER.info("Connection from {}", ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    public static void registerHandler(MessageHandler hndlr, int msgID) {
        if (msgID < 0) {
            AfterlineServer.LOGGER.error("Cannot register a message handler for message with id {}", msgID);
            return;
        }

        if (!Message.exists(msgID)) {
            AfterlineServer.LOGGER.error("Cannot register a message handler for message with id {}", msgID);
            return;
        }
        messageHandlers.putIfAbsent(msgID, new ArrayList<>());
        messageHandlers.get(msgID).add(hndlr);
        AfterlineServer.LOGGER.debug("Registered messsage handler for message id {}", msgID);
    }

    private static void initAllFrom(Class<?> clazz) {
        // scan class

        Arrays.stream(clazz.getMethods()).filter(method -> method.isAnnotationPresent(SubscribeMessage.class) && Modifier.isStatic(method.getModifiers()) && verifyArgs(method)).forEach(method -> {
            if (method.getAnnotation(SubscribeMessage.class).id() == -1) {
                Class<?> msg = method.getParameterTypes()[1];
                if (msg.isAnnotationPresent(MessageID.class)) registerHandler(createMsgHandler(msg, method), msg.getAnnotation(MessageID.class).id());
                else registerHandler(createMsgHandler(msg, method), Message.getMessageID(msg));
            } else {
                registerHandler((netChannel, message) -> {
                    try {
                        method.invoke(null, netChannel, message);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        AfterlineServer.LOGGER.catching(e);
                        AfterlineServer.LOGGER.error("Failed to invoke message handler {}", method.getName());
                    }
                }, method.getAnnotation(SubscribeMessage.class).id());
            }
        });
    }

    private static MessageHandler createMsgHandler(Class<?> msg, Method method) {
        return (netChannel, message) -> {
            try {
                method.invoke(null, netChannel, msg.cast(message));
            } catch (IllegalAccessException | InvocationTargetException e) {
                AfterlineServer.LOGGER.catching(e);
                AfterlineServer.LOGGER.error("Failed to invoke message handler {}", method.getName());
            }
        };
    }

    public static void send(Channel ch, Message m) {
        ByteBuf buf = m.writeBuffer(ch, ch.alloc());
        try {
            ch.writeAndFlush(buf).sync();
        } catch (InterruptedException e) {
            AfterlineServer.LOGGER.catching(e);
        }
//        ReferenceCountUtil.release(buf);
    }

    private static boolean verifyArgs(Method method) {
        return method.getParameterTypes().length == 2 && method.getParameterTypes()[0].isAssignableFrom(Channel.class) && Message.class.isAssignableFrom(method.getParameterTypes()[1]);
    }

    public static void initHandlers() {
        AfterlineServer.LOGGER.info("Starting messsage handler registry");
        initAllFrom(MessageHandlers.class);
    }
}
