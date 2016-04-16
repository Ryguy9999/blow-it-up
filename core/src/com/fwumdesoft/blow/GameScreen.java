package com.fwumdesoft.blow;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen extends ScreenAdapter {
	Stage stage;
	SpriteBatch batch;
	final float WORLD_WIDTH = 1920, WORLD_HEIGHT = 1080;
	
	public GameScreen() {
		batch = new SpriteBatch();
		Camera camera = new OrthographicCamera();
		Viewport viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
		stage = new Stage(viewport, batch);
	}
	
	@Override
	public void render (float delta) {
		stage.act();
		stage.draw();
	}
}
