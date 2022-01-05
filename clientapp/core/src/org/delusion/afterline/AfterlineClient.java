package org.delusion.afterline;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.delusion.afterline.net.AfterlineNetClient;
import org.delusion.afterline.net.message.login.FederatedLoginRequest;
import org.delusion.afterline.ui.SplashScreen;
import org.delusion.afterline.ui.util.Skins;

public class AfterlineClient extends Game {
	public static final Color BACKGROUND = Color.DARK_GRAY;
    public static final Logger LOGGER = LogManager.getLogger("Main");
    private AfterlineNetClient netClient;
	private SplashScreen splashScreen;

	public static final Color ICONBG = new Color(0x387BE4FF);

	public static AfterlineClient INSTANCE;

	public static SplashScreen getSplashScreen() {
		return INSTANCE.splashScreen;
	}

	public AfterlineClient() {
		super();
		INSTANCE = this;
	}

	@Override
	public void create () {
		Skins.init();

		netClient = new AfterlineNetClient(this);
		netClient.start();
		splashScreen = new SplashScreen(this);
		ScreenUtils.clear(0,0,0,1);
		setScreen(splashScreen);

	}

	@Override
	public void dispose () {
		netClient.stopClient();
		splashScreen.dispose();
	}

	public AfterlineNetClient getNetClient() {
		return netClient;
	}

	public void onConnectToServer() {
//		netClient.postMessage(new FederatedLoginRequest());
	}
}
