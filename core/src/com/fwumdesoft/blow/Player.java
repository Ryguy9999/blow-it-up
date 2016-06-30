package com.fwumdesoft.blow;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

public class Player extends DrawingActor {
	public final int id;
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
		setSize(texture.getRegionWidth(), texture.getRegionHeight());
		setOrigin(Align.center);
		setPosition(1920f / 2, 1080f / 2, Align.center);
		bounds = new Circle(getX(Align.center), getY(Align.center), getWidth() / 2);
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
		if(health > maxHealth)
			health = maxHealth;
	}
	
	@Override
	public void drawDebug(ShapeRenderer shapes) {
		super.drawDebug(shapes);
		shapes.set(ShapeType.Line);
		shapes.setColor(Color.GOLD);
		shapes.circle(bounds.x, bounds.y, bounds.radius);
	}
	
	/**
	 * Reduces the player's health
	 * @param dmg The amount to reduce health by
	 * @return true if the player is dead (0 or less health), false otherwise
	 */
	public boolean doDamage(int dmg) {
		Sound sound = BlowItUp.assets.get("music/explosion.mp3", Sound.class);
		sound.play();
		Particle.spawnCluster(getStage(), 100, getX() + getOriginX(), getY() + getOriginY(), 50, 0, 0, 10, 45, 30, Color.RED, false);
		health -= dmg;
		GameScreen.slowdownRemaining = 60;
		GameScreen.rotateCamera(true);
		Particle.spawnCluster(getStage(), 30, 20 + health * 32 + 16, 26, 5, 0, 0, 7.5f, 50, 25, Color.RED, false);
		return health <= 0;
	}
	
	@Override
	public void act(float delta) {
		//check if the player was hit
		for(Actor a : getStage().getActors()) {
			if(a instanceof Missile) {
				Missile m = (Missile)a;
				if(Intersector.overlaps(bounds, m.bounds.getBoundingRectangle())) {
					boolean dead = doDamage(m.damage);
					if(dead) {
						GameScreen.up.setScreen(new GameOverScreen());
					}
					GameScreen.missilePool.free(m);
					m.remove();
				}
			}
		}
	}
}
