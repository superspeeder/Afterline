package org.delusion.afterline.net.message.login;

import org.delusion.afterline.net.message.Message;
import org.delusion.afterline.net.message.MessageID;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;

import java.net.URL;
import java.nio.charset.StandardCharsets;

@MessageID(id = 2)
public class FederatedLoginRequest extends Message {
    private String link;

    public FederatedLoginRequest() {
        super();
    }

    public FederatedLoginRequest(String lnk) {
        super();
        link = lnk;
    }

    @Override
    protected void readFromBuffer(long msgSize, ByteBuf msgData) {
        link = msgData.readCharSequence((int)msgSize, StandardCharsets.UTF_8).toString();
    }

    @Override
    public ByteBuf writeBuffer(Channel ch, ByteBufAllocator alloc) {
        if (link == null) {
            return createBuffer(alloc, getID(), 0);
        }
        ByteBuf buf = createBuffer(alloc, getID(), link.length());
        buf.writeCharSequence(link, StandardCharsets.UTF_8);
        return buf;
    }

    public String getLink() {
        return link;
    }
}
