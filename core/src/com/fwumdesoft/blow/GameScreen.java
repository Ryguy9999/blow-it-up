package com.fwumdesoft.blow;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen extends ScreenAdapter {
	Stage stage;
	Renderer render;
	
	public void update() {
		stage.act();
	}
	
	@Override
	public void render (float delta) {
		render.render(this);
	}
}
