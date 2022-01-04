package org.delusion.afterline.net.message;

import io.netty.channel.Channel;

@FunctionalInterface
public interface MessageHandler {
    
    void handle(Channel netChannel, Message message);
}
