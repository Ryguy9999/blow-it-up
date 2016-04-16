package com.fwumdesoft.blow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Renderer {
	private SpriteBatch batch;

	public Renderer(SpriteBatch batch) {
		System.out.println(batch);
		this.batch = batch;
	}

	public void render(GameScreen currentScreen) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 1, 0, 0);
		
		batch.begin();
		//TODO: DRAW HERE
		batch.end();
	}
}
