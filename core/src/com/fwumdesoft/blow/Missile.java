package com.fwumdesoft.blow;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 * Represents a Missile that each player must reflect in order to survive.
 */
public class Missile extends Actor implements Poolable {
	//TODO needs graphic component
	public float damage;

	private Vector2 vPos;
	
	/**
	 * Instantiates a new Missile with a damage of 10.
	 */
	public Missile() {
		damage = 10;
		vPos = new Vector2();
	}

	@Override
	public void reset() {
		damage = 10;
		setX(0);
		setY(0);
		vPos.set(getX(), getY());
	}
	
	@Override
	public void act(float delta) {
		
	}
	
	/**
	 * @return the vector representation of the bottom-left corner of this Missile object.
	 */
	public Vector2 getVectorPos() {
		return vPos.set(getX(), getY());
	}
	
	@Override
	public String toString() {
		return "{name: Missle}";
	}
}
