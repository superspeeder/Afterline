package org.delusion.afterline;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.google.protobuf.Message;
import io.netty.channel.Channel;
import org.delusion.afterline.net.AfterlineNetClient;
import org.delusion.afterline.ui.MainMenu;

public class AfterlineClient extends Game {
	public static final Color BACKGROUND = Color.DARK_GRAY;
	private AfterlineNetClient netClient;
	private MainMenu mainMenu;

	public static final Color ICONBG = new Color(0x387BE4FF);

	@Override
	public void create () {
		netClient = new AfterlineNetClient(this);
		netClient.start();
		mainMenu = new MainMenu(this);
		ScreenUtils.clear(0,0,0,1);
		setScreen(mainMenu);
		
	}

	@Override
	public void dispose () {
		netClient.stopServer();
		mainMenu.dispose();
	}
}
