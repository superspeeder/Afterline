package org.delusion.afterline;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import org.delusion.afterline.net.AfterlineNetClient;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class AfterlineClient extends ApplicationAdapter {
	@Override
	public void create () {

		Gdx.input.setInputProcessor(new AfterlineInput(this));
	}

	Color color = Color.RED;

	@Override
	public void render () {
		ScreenUtils.clear(color);
	}
	
	@Override
	public void dispose () {
	}

	public void conn() {
		new AfterlineNetClient(this).start();
	}

    public void bg(Color color) {
		this.color = color;
    }
}
