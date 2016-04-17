package com.fwumdesoft.blow;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 * Represents a Missile that each player must reflect in order to survive.
 */
public class Missile extends DrawingActor implements Poolable {
	public float speed;
	public int damage;
	public int lane;
	public Polygon bounds;
	public boolean flipped;

	private Vector2 vPos;

	/**
	 * Instantiates a new Missile with a damage of 10 @ position (0, 0).
	 */
	public Missile() {
		this(10, 0, 0, 0);
	}

	/**
	 * Instantiates a new Missile with a damage of 10 @ position (<tt>x</tt>,
	 * <tt>y</tt>).
	 * 
	 * @param x
	 * @param y
	 */
	public Missile(float x, float y) {
		this(10, x, y, 0);
	}

	/**
	 * Instantiates a new Missile with a damage of <tt>damage</tt> @ position (
	 * <tt>x</tt>, <tt>y</tt>).
	 * 
	 * @param damage
	 * @param x
	 * @param y
	 */
	public Missile(int damage, float x, float y) {
		this(damage, x, y, 0);
	}

	/**
	 * Instantiates a new Missile with a damage of <tt>damage</tt> @ position (
	 * <tt>x</tt>, <tt>y</tt>) on lane <tt>lane</tt>.
	 * 
	 * @param damage
	 * @param x
	 * @param y
	 * @param lane
	 */
	public Missile(int damage, float x, float y, int lane) {
		super(BlowItUp.assets.get("missile.png", Texture.class));
		this.damage = damage;
		setX(x);
		setY(y);
		setOrigin(texture.getRegionWidth() / 2, texture.getRegionHeight() / 2);
		vPos = new Vector2(x, y);
		this.lane = lane;
		bounds = new Polygon(new float[] { 0, 0, getWidth(), 0, 0, getHeight(), getWidth(), getHeight() });
		flipped = false;
	}

	@Override
	public void reset() {
		damage = 10;
		setX(0);
		setY(0);
		vPos.set(getX(), getY());
		speed = 0;
		lane = 0;
	}

	@Override
	public void act(float delta) {
		setX(getX() + speed * MathUtils.cosDeg(getRotation()) * delta);
		setY(getY() + speed * MathUtils.sinDeg(getRotation()) * delta);
		bounds.setPosition(getX(), getY());
		bounds.setRotation(getRotation());
	}

	/**
	 * @return the vector representation of the bottom-left corner of this
	 *         Missile object.
	 */
	public Vector2 getVectorPos() {
		return vPos.set(getX(), getY());
	}

	@Override
	public String toString() {
		return "{name: Missle}";
	}
}
