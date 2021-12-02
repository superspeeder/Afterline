package org.delusion.afterline;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.google.protobuf.Message;
import io.netty.channel.Channel;
import org.delusion.afterline.net.AfterlineNetClient;
import org.delusion.afterline.proto.GetColorRequest;
import org.delusion.afterline.proto.GetColorResponse;

public class AfterlineClient extends ApplicationAdapter {
	private AfterlineNetClient netClient;
	private Color color = Color.RED;

	@Override
	public void create () {
		Gdx.input.setInputProcessor(new AfterlineInput(this));

		netClient = new AfterlineNetClient(this);
		netClient.start();

	}


	@Override
	public void render () {
		ScreenUtils.clear(color);
	}
	
	@Override
	public void dispose () {
		netClient.stopServer();
	}

	public void requestColor() {
		GetColorRequest colorRequest = GetColorRequest.newBuilder().build();

		netClient.post(colorRequest);
	}

    public void bg(Color color) {
		this.color = color;
    }

    public void onRecvColor(GetColorResponse colorResp, Channel channel) {
		bg(new Color(colorResp.getColor()));
    }
}
