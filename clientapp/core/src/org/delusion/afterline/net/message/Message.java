package org.delusion.afterline.net.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.delusion.afterline.AfterlineClient;
import org.delusion.afterline.util.Utils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Message Structure
 * 
 * first 2 bytes: message ID
 * next 4 bytes: message Size
 * 
 * 
 */
public abstract class Message {

    private static Map<Integer, Class<? extends Message>> messageRegistry = new HashMap<>();
    private static Map<Class<? extends Message>, Integer> reverseMessageRegistry = new HashMap<>();

    private int messageID;

    public Message() {
        messageID = reverseMessageRegistry.get(getClass());
    }

    public static int getMessageID(Class<?> msg) {
        return reverseMessageRegistry.getOrDefault(msg, -1);
    }

    public static boolean exists(int msgID) {
        return messageRegistry.containsKey(msgID);
    }

    protected abstract void readFromBuffer(long msgSize, ByteBuf msgData);
    public abstract ByteBuf writeBuffer(Channel ch, ByteBufAllocator alloc);


    public int getID() {
        return messageID;
    }

    public static void register(Class<? extends Message> msgClass) {
        MessageID annotation = msgClass.getAnnotation(MessageID.class);
        if (annotation == null) {
            AfterlineClient.LOGGER.error("Cannot register a message class without an explicitly provided id through Message#register or the @MessageID annotation");
        } else {
            register(msgClass, annotation.id());
        }
    }

    public static void register(Class<? extends Message> msgClass, int id) {
        messageRegistry.put(id, msgClass);
        reverseMessageRegistry.put(msgClass, id);
    }

    public static Class<? extends Message> getMessageClass(int id) {
        return messageRegistry.getOrDefault(id, null);
    }

    public static Message create(int id, long size, ByteBuf data) {
        if (!messageRegistry.containsKey(id)) {
            AfterlineClient.LOGGER.error("Cannot create a message with invalid id {}", id);
            return null;
        }

        try {
            Message msg = messageRegistry.get(id).getConstructor().newInstance();

            msg.readFromBuffer(size, data);
            return msg;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            AfterlineClient.LOGGER.catching(e);
            AfterlineClient.LOGGER.error("Failed to create a message with the given id {}", id);
            return null;
        }
    }

    protected static ByteBuf createBuffer(ByteBufAllocator alloc, int id, int length) {
        ByteBuf buf = alloc.buffer((int) (length + 8));
        return buf.writeInt(id).writeInt(length);
    }
}
