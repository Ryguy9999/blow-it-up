package com.fwumdesoft.blow;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class BlowItUp extends Game {
	
	public static AssetManager assets;
	
	@Override
	public void create () {
		//load all assets
		assets = new AssetManager();
		assets.load("missile.png", Texture.class);
		assets.load("reflector.png", Texture.class);
		assets.load("player.png", Texture.class);
		assets.load("stars.png", Texture.class);
		assets.load("particle.png", Texture.class);
		assets.load("music/emp.wav", Sound.class);
		assets.finishLoading();
		
		setScreen(new GameScreen());
	}
	
	@Override
	public void dispose() {
		assets.dispose();
	}
}
