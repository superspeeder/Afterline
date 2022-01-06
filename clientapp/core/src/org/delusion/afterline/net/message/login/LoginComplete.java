package org.delusion.afterline.net.message.login;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import org.delusion.afterline.net.message.Message;
import org.delusion.afterline.net.message.MessageID;
import org.delusion.afterline.util.Utils;

@MessageID(id = 3)
public class LoginComplete extends Message {

    private String realUsername;

    public LoginComplete() {

    }

    @Override
    protected void readFromBuffer(long msgSize, ByteBuf msgData) {
        realUsername = Utils.readNextString(msgData);
    }

    @Override
    public ByteBuf writeBuffer(Channel ch, ByteBufAllocator alloc) {
        ByteBuf buf = createBuffer(alloc, getID(), Utils.calculateMessageLength(realUsername));
        Utils.writeString(buf, realUsername);
        return buf;
    }

    public String getUsername() {
        return realUsername;
    }
}
