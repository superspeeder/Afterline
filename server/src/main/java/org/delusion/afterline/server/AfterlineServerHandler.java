package org.delusion.afterline.server;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.delusion.afterline.server.message.CastingMessageHandler;
import org.delusion.afterline.server.message.Message;
import org.delusion.afterline.server.message.MessageHandler;
import org.delusion.afterline.server.message.MessageID;

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
        ByteBuf in = (ByteBuf)msg;
        try {

            int msgID = in.readUnsignedShort();
            long msgSize = in.readUnsignedInt();

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

    // private void processMessage(Any msg, Channel ch) throws InvalidProtocolBufferException {
    //     String name = getTypeNameFromTypeUrl(msg.getTypeUrl());
    //     server.getHandlers(name).forEach(cons -> cons.accept(server, msg, ch));
    // }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    public static void registerHandler(MessageHandler hndlr, int msgID) {
        messageHandlers.putIfAbsent(msgID, new ArrayList<>());
        messageHandlers.get(msgID).add(hndlr);
        AfterlineServer.LOGGER.debug("Registered messsage handler for message id {}", msgID);
    }

    public static <T extends Message> void registerHandler(CastingMessageHandler<T> hndlr) {
        Class<T> msgC = (Class<T>)((ParameterizedType)hndlr.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Optional.ofNullable(msgC.getAnnotation(MessageID.class)).ifPresent(anno -> {
            registerHandler(MessageHandler.fromCasting(hndlr), anno.id());
        });
    }
}
