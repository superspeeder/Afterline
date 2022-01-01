package org.delusion.afterline.server.message;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.delusion.afterline.server.AfterlineServer;

import io.netty.buffer.ByteBuf;

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

    private Message() {
        messageID = reverseMessageRegistry.get(getClass());
    }

    protected abstract void readFromBuffer(long msgSize, ByteBuf msgData);

    public int getID() {
        return messageID;
    }

    public static void register(Class<? extends Message> msgClass) {
        MessageID annotation = msgClass.getAnnotation(MessageID.class);
        if (annotation == null) {
            AfterlineServer.LOGGER.error("Cannot register a message class without an explicitly provided id through Message#register or the @MessageID annotation");
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
            AfterlineServer.LOGGER.error("Cannot create a message with invalid id {}", id);
            return null;
        }

        try {
            Message msg = messageRegistry.get(id).getConstructor().newInstance();

            msg.readFromBuffer(size, data);
            return msg;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            AfterlineServer.LOGGER.catching(e);
            AfterlineServer.LOGGER.error("Failed to create a message with the given id {}", id);
            return null;
        }
    }
}
