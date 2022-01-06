package org.delusion.afterline.util;

import io.netty.buffer.ByteBuf;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class Utils {

    public static void openWebpage(String uriStr) throws URISyntaxException, IOException {
        openWebpage(new URI(uriStr));
    }

    public static void openWebpage(URI uri) throws IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(uri);
        }
    }

    public static String readNextString(ByteBuf msgData) {
        int len = msgData.readInt();
        return msgData.readCharSequence(len, StandardCharsets.UTF_8).toString();
    }

    public static void writeString(ByteBuf buf, CharSequence str) {
        buf.writeInt(str.length());
        buf.writeCharSequence(str, StandardCharsets.UTF_8);
    }


    /**
     * Currently supported types:
     *  - CharSequence
     *  - Integer
     *  - Float
     *  - Long
     *  - Double
     *  - Byte
     *  - Character
     *
     *
     * @param objects
     * @return
     */
    public static int calculateMessageLength(Object... objects) {
        int len = 0;

        for (Object obj : objects) {
            if (obj instanceof CharSequence) {
                len += Integer.BYTES + ((CharSequence)obj).length();
            } else if (obj instanceof Integer) {
                len += Integer.BYTES;
            } else if (obj instanceof Float) {
                len += Float.BYTES;
            } else if (obj instanceof Long) {
                len += Long.BYTES;
            } else if (obj instanceof Double) {
                len += Double.BYTES;
            } else if (obj instanceof Byte) {
                len += Byte.BYTES;
            } else if (obj instanceof Character) {
                len += Character.BYTES;
            }
        }

        return len;
    }
}
