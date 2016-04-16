package com.fwumdesoft.blow;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 * Represents a Missile that each player must reflect in order to survive.
 */
public class Missile extends Actor implements Poolable {
	//TODO needs graphic component
	public float damage, speed;

	private Vector2 vPos;
	
	/**
	 * Instantiates a new Missile with a damage of 10 @ position (0, 0).
	 */
	public Missile() {
		this(10, 0, 0);
	}
	
	/**
	 * Instantiates a new Missile with a damage of 10 @ position (<tt>x</tt>, <tt>y</tt>).
	 * @param x
	 * @param y
	 */
	public Missile(float x, float y) {
		this(10, x, y);
	}
	
	/**
	 * Instantiates a new Missile with a damage of <tt>damage</tt> @ position (<tt>x</tt>, <tt>y</tt>).
	 * @param damage
	 * @param x
	 * @param y
	 */
	public Missile(float damage, float x, float y) {
		this.damage = damage;
		vPos = new Vector2(x, y);
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
		setX(getX() + speed * MathUtils.cosDeg(getRotation()) * delta);
		setY(getY() + speed * MathUtils.sinDeg(getRotation()) * delta);
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
