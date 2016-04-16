package com.fwumdesoft.blow;

public class Reflector extends DrawingActor {

	public float range;

	public Reflector() {
		this(10);
	}

	public Reflector(float range) {
		super(null); //TODO: HOPEFULLY THIS WILL MAKE SENSE LATER
		this.range = range;
	}

	@Override
	public void act(float delta) {

	}
}
