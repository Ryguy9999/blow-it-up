package com.fwumdesoft.blow;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player extends Actor {
	public byte id;
	private short health, maxHealth;
	public float powerLevel;
	public Texture texture;

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

	public void heal(short heal) {
		health += heal;
		if (health > maxHealth)
			health = maxHealth;
	}

	public boolean doDamage(short dmg) {
		health -= dmg;
		return health <= 0;
	}
}
