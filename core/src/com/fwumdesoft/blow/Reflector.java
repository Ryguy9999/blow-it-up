package com.fwumdesoft.blow;

public class Reflector extends DrawingActor {

	public float range;

	public Reflector() {
		this(10);
	}

	public Reflector(float range) {
		super(null); //TODO Replace with reflector asset when done
		this.range = range;
	}

	@Override
	public void act(float delta) {

	}
}
