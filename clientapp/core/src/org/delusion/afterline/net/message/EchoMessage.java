package org.delusion.afterline.net.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.nio.charset.StandardCharsets;

@MessageID(id = 1)
public class EchoMessage extends Message {

    private String data;

    public EchoMessage() {
        super();
    }

    public EchoMessage(String msg) {
        this();
        data = msg;
    }

    @Override
    protected void readFromBuffer(long msgSize, ByteBuf msgData) {
        data = msgData.readCharSequence((int) msgSize, StandardCharsets.UTF_8).toString();
    }

    @Override
    public ByteBuf writeBuffer(Channel ch, ByteBufAllocator alloc) {
        ByteBuf buffer = createBuffer(alloc, getID(), data.length());
        buffer.writeCharSequence(data, StandardCharsets.UTF_8);
        return buffer;
    }

    public String getData() {
        return data;
    }
}
