package com.fwumdesoft.blow;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool;

public class MissileSpawningActor extends Actor {
	Pool<Missile> pool;
	public MissileSpawningActor(Pool<Missile> missilePool) {
		pool = missilePool;
	}
	
	public void act(float delta) {
		if(Math.random() < 0.05)
		{
			int lane = (int)(Math.random()*8);

			final int SPAWN_DISTANCE = 600;
			Missile m = pool.obtain();
			m.lane = lane;
			m.setRotation(lane * 45 + 180);
			m.setX(1920 / 2 - 48 + SPAWN_DISTANCE * MathUtils.cosDeg(lane * 45));
			m.setY(1080 / 2 - 48 + SPAWN_DISTANCE * MathUtils.sinDeg(lane * 45));
			m.speed = Missile.DEFAULT_SPEED;
			getStage().addActor(m);
		}
	}
}
