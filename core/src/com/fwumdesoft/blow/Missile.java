package com.fwumdesoft.blow;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
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
	
	public static final float DEFAULT_SPEED = 350;

	private Vector2 vPos;

	/**
	 * Instantiates a new Missile with a damage of 10 @ position (0, 0).
	 */
	public Missile() {
		this(1, 0, 0, 0);
	}

	/**
	 * Instantiates a new Missile with a damage of 10 @ position (<tt>x</tt>,
	 * <tt>y</tt>).
	 * 
	 * @param x
	 * @param y
	 */
	public Missile(float x, float y) {
		this(1, x, y, 0);
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
		setSize(texture.getRegionWidth(), texture.getRegionHeight());
		setOrigin(Align.center);
		setPosition(x, y);
		vPos = new Vector2(x, y);
		this.lane = lane;
		bounds = new Polygon(new float[] {0, 0, getWidth(), 0, getWidth(), getHeight(), 0, getHeight()});
		bounds.setPosition(x, y);
		bounds.setOrigin(getOriginX(), getOriginY());
		flipped = false;
	}
	
	@Override
	public void drawDebug(ShapeRenderer shapes) {
		super.drawDebug(shapes);
		shapes.set(ShapeType.Line);
		shapes.setColor(Color.GOLD);
		shapes.polygon(bounds.getTransformedVertices());
	}

	@Override
	public void reset() {
		damage = 1;
		setPosition(0, 0);
		setRotation(0);
		vPos.set(getX(), getY());
		speed = 0;
		lane = 0;
		bounds.setPosition(getX(), getY());
		bounds.setRotation(getRotation());
		bounds.setOrigin(getOriginX(), bounds.getOriginY());
	}

	@Override
	public void act(float delta) {
		if(getStage() == null) return;
		setX(getX() + speed * MathUtils.cosDeg(getRotation()) * delta);
		setY(getY() + speed * MathUtils.sinDeg(getRotation()) * delta);
		bounds.setPosition(getX(), getY());
		bounds.setRotation(getRotation());
		if(getStage() != null && Math.random() <= 0.5f) {
			float x = getX() + getOriginX();
			float y = getY() + getOriginY();
			Particle.spawnCluster(getStage(), 3, x, y, 0, -speed * MathUtils.cosDeg(getRotation()) * delta, -speed * MathUtils.sinDeg(getRotation()) * delta, 0.5f, 20, 5, Color.ORANGE, false);
		}
		if(flipped && ((getX() < 0 || getX() > getStage().getWidth()) ||  (getY() < 0 || getY() > getStage().getHeight()))) {
			GameScreen.missilePool.free(this);
			remove();
		}
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
