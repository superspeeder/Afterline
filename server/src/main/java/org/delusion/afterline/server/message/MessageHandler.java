package org.delusion.afterline.server.message;

import io.netty.channel.Channel;

@FunctionalInterface
public interface MessageHandler {
    
    public void handle(Channel netChannel, Message message);

    public static <T extends Message> MessageHandler fromCasting(CastingMessageHandler<T> hndlr) {
        return new MessageHandler() {
            @Override
            public void handle(Channel netChannel, Message message) {
                hndlr.handle(netChannel, (T)message);
            }
        };
    }
}
