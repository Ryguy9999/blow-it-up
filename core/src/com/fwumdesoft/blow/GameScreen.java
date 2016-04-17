package com.fwumdesoft.blow;

import java.io.File;
import java.io.FileNotFoundException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fwumdesoft.music.Music;
import com.fwumdesoft.music.Song;

public class GameScreen extends ScreenAdapter {
	Stage stage;
	SpriteBatch batch;
	Song song;
	final float WORLD_WIDTH = 1920, WORLD_HEIGHT = 1080;
	int playerNumber = 0;
	boolean isAttacking = false;
	
	public static Pool<Missile> missilePool;
	
	public GameScreen() {
		batch = new SpriteBatch();
		Camera camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Viewport viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
		stage = new Stage(viewport, batch);
		
		stage.addActor(new BackgroundActor());
		
		missilePool = Pools.get(Missile.class); //max missiles in pool = 100
		
		Reflector[] reflectors = new Reflector[8];
		for(int i = 0; i < reflectors.length; i++) {
			reflectors[i] = new Reflector(400, i); 
			reflectors[i].setPosition(1920 / 2, 1080 / 2);
			float angle = 45 * i; 
			reflectors[i].setOrigin(0, 64);
			reflectors[i].setX(reflectors[i].getX() + 100 * MathUtils.cosDeg(angle));
			reflectors[i].setY(reflectors[i].getY() + 100 * MathUtils.sinDeg(angle) - 40);
			stage.addActor(reflectors[i]);
		}
		stage.addActor(new InputManager(this, reflectors));
		
		Player p1 = new Player(0, 10); //Debug player :(
		stage.addActor(p1);
		
		Missile m1 = new Missile(10, 0); //Debug Missile :)
		m1.speed = 200;
		m1.setRotation(180);
		m1.setY(1080 / 2);
		m1.setX(1920);
		stage.addActor(m1);
		
		//Randomly select a song
		Runnable musicRunnable = () -> {
			try {
				File songAssets = Gdx.files.internal("music").file();
				song = new Song(songAssets.listFiles()[(int)(Math.random() * songAssets.listFiles().length)]);
				Music piano = new Music(song.bpm, 0);
				piano.playSong(song);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		};
		
		new Thread(musicRunnable).start();
		
	}
	
	private void spawnMissile(int lane) {
		Missile m = missilePool.obtain();
		m.lane = lane;
		m.setRotation(lane * 45 + 180);
		m.speed = Missile.DEFAULT_SPEED;
		//TODO give the missile a proper position
	}
	
	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}
}
