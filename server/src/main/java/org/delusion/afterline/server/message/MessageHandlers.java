package org.delusion.afterline.server.message;

import org.delusion.afterline.server.AfterlineServer;
import org.delusion.afterline.server.message.login.FederatedLoginRequest;

import io.netty.channel.Channel;

import static org.delusion.afterline.server.AfterlineServerHandler.send;

import java.util.Random;

public class MessageHandlers {

    static Random random = new Random();

    @SubscribeMessage
    public static void onEcho(Channel ch, EchoMessage msg) {
        AfterlineServer.LOGGER.info("Received an echo message containing data '{}'", msg.getData());
        send(ch, new EchoMessage("Test Message; " + random.nextInt(10000000)));
    }

    @SubscribeMessage
    public static void onFederatedLoginRequest(Channel ch, FederatedLoginRequest req) {
        send(ch, new FederatedLoginRequest(ch));
    }
}
