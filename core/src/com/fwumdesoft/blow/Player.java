package com.fwumdesoft.blow;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player extends Actor {
	public byte id;
	private short health, maxHealth;
	public float powerLevel;
	public Texture texture;

	/**
	 * Constructs the player
	 * @param id The ID of the player (either 0 or 1 for Player 1 or Player 2)
	 * @param health The maximumHealth of the player and the initial health of the player
	 */
	public Player(byte id, short health) {
		this.id = id;
		maxHealth = this.health = health;
		powerLevel = 0;
		texture = null;
	}

	public short getHealth() {
		return health;
	}

	public short getMaxHealth() {
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
	public boolean doDamage(short dmg) {
		health -= dmg;
		return health <= 0;
	}
}
