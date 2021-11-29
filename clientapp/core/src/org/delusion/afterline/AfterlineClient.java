package org.delusion.afterline;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

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
//		try (Socket sock = new Socket("localhost", 9000)) {
//			DataInputStream in = new DataInputStream(sock.getInputStream());
//			color = new Color(in.readInt());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}
}
