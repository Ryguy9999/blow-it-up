package com.fwumdesoft.blow;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class BlowItUp extends Game {
	
	public static AssetManager assets;
	public static Game game;
	
	@Override
	public void create () {
		//load all assets
		assets = new AssetManager();
		assets.load("missile.png", Texture.class);
		assets.load("reflector.png", Texture.class);
		assets.load("player.png", Texture.class);
		assets.load("stars.png", Texture.class);
		assets.load("particle.png", Texture.class);
		assets.load("star_particle.png", Texture.class);
		assets.load("music/emp.wav", Sound.class);
		assets.load("heart.png", Texture.class);
		assets.load("game_over.png", Texture.class);
		assets.load("music/explosion.mp3", Sound.class);
		assets.finishLoading();
		
		setScreen(new GameScreen(this));
	}
	
	@Override
	public void dispose() {
		assets.dispose();
	}
}
