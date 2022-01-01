package org.delusion.afterline.server.message;

import io.netty.channel.Channel;

@FunctionalInterface
public interface CastingMessageHandler<T extends Message> {
    public void handle(Channel netChannel, T message);
}
