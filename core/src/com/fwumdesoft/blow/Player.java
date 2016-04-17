package com.fwumdesoft.blow;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player extends DrawingActor {
	public final  int id;
	private int health, maxHealth;
	public float powerLevel;
	public Circle bounds;

	/**
	 * Constructs the player
	 * @param id The ID of the player (either 0 or 1 for Player 1 or Player 2)
	 * @param health The maximumHealth of the player and the initial health of the player
	 */
	public Player(int id, int health) {
		super(BlowItUp.assets.get("player.png", Texture.class));
		this.id = id;
		maxHealth = this.health = health;
		powerLevel = 0;
		setX(1920 / 2 - texture.getRegionWidth() / 2);
		setY(1080 / 2 - texture.getRegionHeight() / 2);
		setOrigin(texture.getRegionWidth() / 2, texture.getRegionHeight() / 2);
		bounds = new Circle(getX(), getY(), texture.getRegionWidth()/2);
	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	/**
	 * Restores the player's health up to maxHealth
	 * @param heal The amount to heal by
	 */
	public void heal(short heal) {
		health += heal;
		if (health > maxHealth)
			health = maxHealth;
	}

	/**
	 * Reduces the player's health
	 * @param dmg The amount to reduce health by
	 * @return true if the player is dead (0 or less health), false otherwise
	 */
	public boolean doDamage(int dmg) {
		health -= dmg;
		GameScreen.rotateCamera(true);
		return health <= 0;
	}
	
	@Override
	public void act(float delta) {
		//check if the player was lit (ayyy lmao)
		for(Actor a : getStage().getActors()) {
			if(a instanceof Missile) {
				Missile m = (Missile)a;
				if(Intersector.overlaps(bounds, m.bounds.getBoundingRectangle())) {
					boolean dead = doDamage(m.damage);
					if(dead) {
//						System.out.println("DEAD");
					}
					GameScreen.missilePool.free(m);
					m.remove();
				}
			}
		}
	}
}
