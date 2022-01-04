package org.delusion.afterline.util;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Utils {

    public static void openWebpage(String uriStr) throws URISyntaxException, IOException {
        openWebpage(new URI(uriStr));
    }

    public static void openWebpage(URI uri) throws IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(uri);
        }
    }
}
