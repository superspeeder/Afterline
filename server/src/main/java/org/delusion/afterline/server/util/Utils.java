package org.delusion.afterline.server.util;

import io.netty.channel.Channel;

import java.net.InetSocketAddress;

public class Utils {
    // return address in a normal string form
    public static String getBasicIPString(Channel ch) {
        InetSocketAddress addr = ((InetSocketAddress)ch.remoteAddress());
        return addr.getAddress().getHostAddress() + ":" + addr.getPort();
    }
}
