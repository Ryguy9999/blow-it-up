package com.fwumdesoft.blow;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class Particle extends DrawingActor implements Poolable {

	private float xSpeed, ySpeed;
	private int lifetime;
	static Pool<Particle> pool;
	
	static {
		pool = Pools.get(Particle.class);
	}
	
	public Particle() {
		super(BlowItUp.assets.get("particle.png", Texture.class));
		set(xSpeed, ySpeed, lifetime, Color.WHITE);
		setOrigin(texture.getRegionWidth() / 2, texture.getRegionHeight() / 2);
	}
	
	public void set(float xSpeed, float ySpeed, int lifetime, Color color) {
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.lifetime = lifetime;
		setColor(color);
	}

	
	public static void spawnCluster(Stage stage, int particles, float x, float y, float positionVariance, float xSpeed, float ySpeed, float speedVariance, int lifetime, int lifetimeVariance, Color color) {
		for(int i = 0; i < particles; i++) {
			Particle particle = pool.obtain();
			particle.set(xSpeed + ((float)Math.random() - 0.5f) * speedVariance, ySpeed + ((float)Math.random() - 0.5f) * speedVariance, lifetime, color);
			particle.setPosition(x + ((float)Math.random() - 0.5f) * positionVariance, y + ((float)Math.random() - 0.5f) * positionVariance);
			stage.addActor(particle);
		}
	}
	
	@Override
	public void act(float delta) {
		lifetime -= 1; 
		setPosition(getX() + xSpeed, getY() + ySpeed);
		if(lifetime <= 0) {
			remove();
			pool.free(this);
		}
	}
	
	
	@Override
	public void draw(Batch batch, float alpha) {
		super.draw(batch, alpha);
	}


	@Override
	public void reset() {
		xSpeed = 0;
		ySpeed = 0;
	}
}
