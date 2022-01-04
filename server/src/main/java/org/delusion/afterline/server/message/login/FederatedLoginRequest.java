package org.delusion.afterline.server.message.login;

import com.google.common.base.Charsets;

import org.delusion.afterline.server.AfterlineServer;
import org.delusion.afterline.server.ClientSession;
import org.delusion.afterline.server.message.Message;
import org.delusion.afterline.server.message.MessageID;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;

@MessageID(id = 2)
public class FederatedLoginRequest extends Message {
    private String link;

    public FederatedLoginRequest() {
        super();
    }

    public FederatedLoginRequest(Channel ch) {
        link = ClientSession.createAuthLink(ch);
        AfterlineServer.LOGGER.info(link);
    }

    public FederatedLoginRequest(String lnk) {
        super();
        link = lnk;
    }

    @Override
    protected void readFromBuffer(long msgSize, ByteBuf msgData) {
        link = msgData.readCharSequence((int)msgSize, Charsets.UTF_8).toString();
    }

    @Override
    public ByteBuf writeBuffer(Channel ch, ByteBufAllocator alloc) {
        ByteBuf buf = createBuffer(alloc, getID(), link.length());
        buf.writeCharSequence(link, Charsets.UTF_8);
        return buf;
    }
    
}
