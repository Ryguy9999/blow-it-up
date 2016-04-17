package com.fwumdesoft.blow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
	public static int slowdownRemaining = 0;
	
	public static Pool<Missile> missilePool;
	
	static BlowItUp up;
	
	public GameScreen(BlowItUp upVar) {
		up = upVar;
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Viewport viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
		stage = new Stage(viewport, batch);
		
		stage.addActor(new BackgroundActor());
		
		missilePool = Pools.get(Missile.class); //max missiles in pool = 100
		
		Reflector[] reflectors = new Reflector[8];
		for(int i = 0; i < reflectors.length; i++) {
			reflectors[i] = new Reflector(300, i); 
			reflectors[i].setPosition(1920 / 2, 1080 / 2);
			float angle = 45 * i; 
			reflectors[i].setOrigin(0, reflectors[i].texture.getRegionHeight() / 2);
			reflectors[i].setX(reflectors[i].getX() + 150 * MathUtils.cosDeg(angle));
			reflectors[i].setY(reflectors[i].getY() + 150 * MathUtils.sinDeg(angle) - 40);
			stage.addActor(reflectors[i]);
		}
		new InputManager(this, reflectors);
		
		p1 = new Player(0, 10);
		stage.addActor(p1);
		
		Music music = Gdx.audio.newMusic(Gdx.files.internal("music/song1.wav"));
		music.setVolume(1);
		music.setLooping(true); 
		music.play();
	}
	
	
	private void spawnMissile(int lane) {
		final int SPAWN_DISTANCE = 970;
		Missile m = new Missile();
		m.lane = lane;
		m.setX(1920 / 2 - 64 + SPAWN_DISTANCE * MathUtils.cosDeg(lane * 45));
		m.setY(1080 / 2 - 32 + SPAWN_DISTANCE * MathUtils.sinDeg(lane * 45));
		m.setRotation((lane * 45 + 180) % 360);
		m.speed = Missile.DEFAULT_SPEED;
		stage.addActor(m);
	}
	
	private float time = 0;
	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(slowdownRemaining % 5 == 0) {
			stage.act(delta);
			if(shaking)
			{
				totalShakeTime -= delta;
				timeOnShake -= delta;
				if(totalShakeTime < 0)
					shaking = false;
				if(timeOnShake < 0)
				{
					shakeX += (Math.random() - 0.5f) * (Math.random() * 3);
					shakeY += (Math.random() - 0.5f) * (Math.random() * 3);
				}
	//			shakeDistance.add(shakeDirection);
				camera.position.set(1920 / 2 + shakeX, 1080 / 2 + shakeY, 0);
				camera.position.set(MathUtils.lerp(camera.position.x, 1920 / 2, (float) Math.random()), MathUtils.lerp(camera.position.y, 1080 / 2, (float) Math.random()), 0);
			}
			else 
			{
				camera.position.set(MathUtils.lerp(camera.position.x, 1920 / 2, 0.5f), MathUtils.lerp(camera.position.y, 1080 / 2, 0.5f), 0);
			}
			time += delta;
			//spawn missile logic
			if(time >= 1 && Math.random() < 1.0/3.0)
			{
				time -= 1.0;
				int lane = (int)(Math.random()*8);
				spawnMissile(lane);
			}
		}
		if(slowdownRemaining > 0)
			slowdownRemaining--;
		stage.draw();
		
		stage.getBatch().begin();
		for(int i = 0; i < p1.getHealth(); i++)
			stage.getBatch().draw(BlowItUp.assets.get("heart.png", Texture.class), 20 + i * 32, 20);
		stage.getBatch().end();
	}
	
	public static void rotateCamera(boolean strong)
	{
		shaking = true;
		if(strong)
			totalShakeTime = 0.75f;
		else
			totalShakeTime = 0;
				
	}
}
