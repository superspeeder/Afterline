package org.delusion.afterline.net;

import io.netty.channel.Channel;
import org.delusion.afterline.AfterlineClient;
import org.delusion.afterline.net.message.EchoMessage;
import org.delusion.afterline.net.message.SubscribeMessage;
import org.delusion.afterline.net.message.login.FederatedLoginRequest;
import org.delusion.afterline.net.message.login.LoginComplete;
import org.delusion.afterline.util.Utils;

import java.io.IOException;
import java.net.URISyntaxException;

public class Handlers {

    @SubscribeMessage
    public static void onEchoReceive(Channel ch, EchoMessage msg) {
        AfterlineClient.LOGGER.info(msg.getData());
        AfterlineClient.getSplashScreen().setText(msg.getData());
    }

    @SubscribeMessage
    public static void onRequestFederatedLoginRespond(Channel ch, FederatedLoginRequest resp) {
        try {
            AfterlineClient.LOGGER.info(resp.getLink());
            Utils.openWebpage(resp.getLink());
        } catch (URISyntaxException | IOException e) {
            AfterlineClient.LOGGER.catching(e);
        }
    }

    @SubscribeMessage
    public static void onLoginComplete(Channel ch, LoginComplete resp) {
        AfterlineClient.INSTANCE.setCurrentUser(resp.getUsername());
    }
}
