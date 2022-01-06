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
    private String username;
    private String link;

    public FederatedLoginRequest() {
        super();
    }

    public FederatedLoginRequest(String uname) {
        super();
        username = uname;
    }

    public FederatedLoginRequest(String uname, String lnk) {
        this(uname);
        link = lnk;
    }

    @Override
    protected void readFromBuffer(long msgSize, ByteBuf msgData) {
        int ll = msgData.readInt();
        link = msgData.readCharSequence(ll, StandardCharsets.UTF_8).toString();
        int unl = msgData.readInt();
        username = msgData.readCharSequence(unl, StandardCharsets.UTF_8).toString();
    }

    @Override
    public ByteBuf writeBuffer(Channel ch, ByteBufAllocator alloc) {
        if (link == null) {
            link = "";
        }

        if (username == null) {
            throw new RuntimeException("Username is required");
        }

        ByteBuf buf = createBuffer(alloc, getID(), link.length() + Integer.BYTES * 2 + username.length());
        buf.writeInt(link.length());
        buf.writeCharSequence(link, StandardCharsets.UTF_8);
        buf.writeInt(username.length());
        buf.writeCharSequence(username, StandardCharsets.UTF_8);
        return buf;
    }

    public String getLink() {
        return link;
    }

    public String getUsername() {
        return username;
    }
}
