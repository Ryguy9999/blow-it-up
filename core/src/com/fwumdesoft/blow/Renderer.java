package com.fwumdesoft.blow;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Renderer {
	private SpriteBatch batch;

	public Renderer(SpriteBatch batch) {
		this.batch = batch;
	}

	public void render(GameScreen currentScreen) {
		batch.begin();
		//TODO: DRAW HERE
		batch.end();
	}
}
