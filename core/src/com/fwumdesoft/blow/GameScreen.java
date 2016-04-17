package com.fwumdesoft.blow;

import java.io.FileNotFoundException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	final float WORLD_WIDTH = 1920, WORLD_HEIGHT = 1080;
	int playerNumber = 0;
	boolean isAttacking = false;
	
	public static Pool<Missile> missilePool;
	
	public GameScreen() {
		batch = new SpriteBatch();
		Camera camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Viewport viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
		stage = new Stage(viewport, batch);
		
		missilePool = Pools.get(Missile.class); //max missiles in pool = 100
		
		Reflector[] reflectors = new Reflector[8];
		for(int i = 0; i < reflectors.length; i++) {
			reflectors[i] = new Reflector(400, i); 
			reflectors[i].setPosition(1920 / 2, 1080 / 2);
			stage.addActor(reflectors[i]);
		}
		stage.addActor(new InputManager(this, reflectors));
		
		Player p1 = new Player(0, 10);
		stage.addActor(p1);
		
		Missile m1 = new Missile(10, 0); //Debug Missile :)
		m1.speed = 50;
		m1.setRotation(180);
		m1.setY(1080 / 2);
		m1.setX(1920);
		stage.addActor(m1);
		
		try 
		{
			Song s = new Song(Gdx.files.internal("music/FwumDeOne.fmf").file());
			Music piano = new Music(s.bpm, 0);
			piano.playSong(s);
		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}
}
