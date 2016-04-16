package com.fwumdesoft.blow;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class Reflector extends DrawingActor {

	public float range;
	public int lane;

	public Reflector(int lane) {
		this(10, lane);
	}

	public Reflector(float range, int lane) {
		super(null); // TODO Replace with reflector asset when done
		this.range = range;
		this.lane = lane;
	}

	public void launchMissile() {
		getStage().addActor(new Missile(10, 0, 0, lane));
	}

	public void reflectMissile(Missile m) {
		if (m.getRotation() != this.getRotation())
			m.setRotation(m.getRotation() + 180);
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
