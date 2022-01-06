package org.delusion.afterline.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;
import org.delusion.afterline.AfterlineClient;
import org.delusion.afterline.net.message.Message;
import org.delusion.afterline.net.message.MessageHandler;

import java.util.*;

public class AfterlineClientHandler extends ChannelInboundHandlerAdapter {
    private final AfterlineNetClient client;
    private static Map<Integer, List<MessageHandler>> messageHandlers = new HashMap<>();


    public AfterlineClientHandler(AfterlineNetClient afterlineNetClient) {
        client = afterlineNetClient;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        AfterlineClient.LOGGER.debug("CHANNEL ACTIVE");
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf)msg;
        try {

            int msgID = in.readInt();
            long msgSize = in.readInt();

            AfterlineClient.LOGGER.debug("Received message with id {}", msgID);

            Channel ch = ctx.channel();
            Optional<Message> messageOpt = Optional.ofNullable(Message.create(msgID, msgSize, in));
            messageOpt.ifPresentOrElse(msg_ -> {
                messageHandlers.getOrDefault(msgID, List.of()).forEach(mh -> mh.handle(ch, msg_));
            }, () -> AfterlineClient.LOGGER.error("Cannot process message: failed to create a message object from message data"));

        } finally {
            ReferenceCountUtil.release(msg);
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    public static void registerHandler(MessageHandler hndlr, int msgID) {
        if (msgID < 0) {
            AfterlineClient.LOGGER.error("Cannot register a message handler for message with id {}", msgID);
            return;
        }

        if (!Message.exists(msgID)) {
            AfterlineClient.LOGGER.error("Cannot register a message handler for message with id {}", msgID);
            return;
        }
        messageHandlers.putIfAbsent(msgID, new ArrayList<>());
        messageHandlers.get(msgID).add(hndlr);
        AfterlineClient.LOGGER.debug("Registered messsage handler for message id {}", msgID);
    }

}
