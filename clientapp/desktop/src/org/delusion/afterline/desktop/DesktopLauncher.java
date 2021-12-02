package org.delusion.afterline.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.delusion.afterline.AfterlineClient;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Afterline Engine";

		config.addIcon("textures/afterline_icon_128.png", Files.FileType.Internal);
		config.addIcon("textures/afterline_icon_32.png", Files.FileType.Internal);
		config.addIcon("textures/afterline_icon_16.png", Files.FileType.Internal);
		new LwjglApplication(new AfterlineClient(), config);
	}
}
