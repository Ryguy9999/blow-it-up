package com.fwumdesoft.blow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
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

public class GameScreen extends ScreenAdapter {
	Stage stage;
	SpriteBatch batch;
	final float WORLD_WIDTH = 1920, WORLD_HEIGHT = 1080;
	int playerNumber = 0;
	boolean isAttacking = false;
	Player p1;
	private Camera camera;
	private static boolean shaking;
	private static float timeOnShake, totalShakeTime, shakeX, shakeY;
	
	public static Pool<Missile> missilePool;
	
	public GameScreen() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Viewport viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
		stage = new Stage(viewport, batch);
		
		stage.addActor(new BackgroundActor());
		
		missilePool = Pools.get(Missile.class); //max missiles in pool = 100
		
		Reflector[] reflectors = new Reflector[8];
		for(int i = 0; i < reflectors.length; i++) {
			reflectors[i] = new Reflector(400, i); 
			reflectors[i].setPosition(1920 / 2, 1080 / 2);
			float angle = 45 * i; 
			reflectors[i].setOrigin(0, reflectors[i].texture.getRegionHeight() / 2);
			reflectors[i].setX(reflectors[i].getX() + 150 * MathUtils.cosDeg(angle));
			reflectors[i].setY(reflectors[i].getY() + 150 * MathUtils.sinDeg(angle) - 40);
			stage.addActor(reflectors[i]);
		}
		stage.addActor(new InputManager(this, reflectors));
		
		p1 = new Player(0, 10); //Debug player :(
		stage.addActor(p1);
		
		for(int i = 0; i < 8; i++)
			spawnMissile(i);
		
		Music music = Gdx.audio.newMusic(Gdx.files.internal("music/song1.wav"));
		music.play();
		music.setVolume(1);
		music.setLooping(true); 
	}
	
	/**
	 * Spawns a missile in <tt>lane</tt>.
	 * @param lane
	 */
	private void spawnMissile(int lane) {
		final int SPAWN_DISTANCE = 600;
		Missile m = missilePool.obtain();
		m.lane = lane;
		m.setX(p1.getX() + SPAWN_DISTANCE * MathUtils.cosDeg(lane * 45));
		m.setY(p1.getY() + 32 + SPAWN_DISTANCE * MathUtils.sinDeg(lane * 45));
		m.setRotation(lane * 45 + 180);
		m.speed = Missile.DEFAULT_SPEED;
		stage.addActor(m);
	}
	
	private float time = 0;
	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		
		//shake the screen when player is hit
		if(shaking) {
			totalShakeTime -= delta;
			timeOnShake -= delta;
			if(totalShakeTime < 0)
				shaking = false;
			if(timeOnShake < 0) {
				shakeX += (Math.random() - 0.5f) * 3;
				shakeY += (Math.random() - 0.5f) * 3;
			}
//			shakeDistance.add(shakeDirection);
			camera.position.set(1920 / 2 + shakeX, 1080 / 2 + shakeY, 0);
		}
		else {
			camera.position.set(1920 / 2, 1080 / 2, 0);
		}
		time += delta;
		
		//spawn missile logic
		if(time >= 1 && Math.random() < 1.0/3.0) {
			time -= 1.0;
			int lane = (int)(Math.random()*8);
			spawnMissile(lane);
		}
		stage.draw();
	}
	
	public static void rotateCamera(boolean strong) {
		shaking = true;
		if(strong)
			totalShakeTime = 1;
		else
			totalShakeTime = 0.5f;
				
	}
}
