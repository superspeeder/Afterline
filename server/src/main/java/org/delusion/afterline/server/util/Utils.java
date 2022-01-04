package org.delusion.afterline.server.util;

import io.netty.channel.Channel;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.delusion.afterline.server.http.SimpleHTTPServer;

public class Utils {
    // return address in a normal string form
    public static String getBasicIPString(Channel ch) {
        InetSocketAddress addr = ((InetSocketAddress)ch.remoteAddress());
        return addr.getAddress().getHostAddress() + ":" + addr.getPort();
    }

    public static Map<String,String> parseHTTPQuery(String query) {
        String[] strs = query.split("&");

        Map<String,String> qMap = new HashMap<>();
        for (String str : strs) {
            String[] kv = str.split("=");
            try {
                qMap.put(URLDecoder.decode(kv[0], StandardCharsets.UTF_8.name()), URLDecoder.decode(kv[1], StandardCharsets.UTF_8.name()));
            } catch (UnsupportedEncodingException e) {
                SimpleHTTPServer.LOGGER.catching(e);
            }
        }
        
        return qMap;
    } 
}
