package com.fwumdesoft.blow;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class Reflector extends DrawingActor {

	public float range;
	public int lane;

	public Reflector(int lane) {
		this(400, lane);
	}

	public Reflector(float range, int lane) {
		super(BlowItUp.assets.get("reflector.png", Texture.class));
		this.range = range;
		this.lane = lane;
		setRotation(lane * 45);
	}

	public void launchMissile() {
		Missile m1 = new Missile(1, getX(), getY(), lane);
		m1.speed = 50;
		m1.setRotation(getRotation());
		getStage().addActor(m1);
	}

	public void reflectMissile(Missile m) {
		Sound sound = BlowItUp.assets.get("music/emp.wav", Sound.class);
		sound.play();
		if (!m.flipped) {
			//TODO: Change speed by quality of reflect
			m.rotateBy(180);
			m.speed *= 5;
			m.flipped = true;
			GameScreen.rotateCamera(false);
			float xSpeed = (float) (3 * Math.cos(Math.toRadians(getRotation())));
			float ySpeed = (float) (3 * Math.sin(Math.toRadians(getRotation())));
			float len = (float)Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed);
			Particle.spawnCluster(getStage(), 15, 1920 / 2 + 100 * (xSpeed / len), 1080 / 2 + 100 * (ySpeed / len), 1, xSpeed, ySpeed, 0.5f, 45, 15);
		}
	}

	public void reflectMissiles() {
		Array<Actor> actors = getStage().getActors();
		for (Actor actor : actors) {
			if (actor instanceof Missile) {
				Missile m = (Missile) actor;
				if (m.lane != lane)
					continue;
				if (Vector2.dst(getX(), getY(), m.getX(), m.getY()) <= range) {
					reflectMissile(m);
				}
			}
		}
	}

	@Override
	public void act(float delta) {
		
	}
}
