package com.fwumdesoft.blow;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Reflector extends Actor {
	public float range;
	
	public Reflector() {
		this(10);
	}
	
	public Reflector(float range) {
		this.range = range;
	}
}
