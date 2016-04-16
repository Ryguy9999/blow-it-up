package com.fwumdesoft.blow;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool.Poolable;

public class Missile extends Actor implements Poolable {
	public float damage;
	
	private Vector2 vPos;
	
	public Missile() {
		damage = 10;
		vPos = new Vector2();
	}

	@Override
	public void reset() {
		damage = 10;
		setX(0);
		setY(0);
		vPos.set(0, 0);
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
