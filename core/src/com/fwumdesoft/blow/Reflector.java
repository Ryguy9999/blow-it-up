package com.fwumdesoft.blow;

public class Reflector extends DrawingActor {

	public float range;
	public int lane;
	
	public Reflector(int lane) {
		this(10, lane);
	}
	public Reflector(float range, int lane) {
		super(null); //TODO Replace with reflector asset when done
		this.range = range;
		this.lane = lane;
	}
	
	public void launchMissile() {
		getStage().addActor(new Missile(10, 0, 0, lane));
	}
	
	public void reflectMissile(Missile m) {
		if(m.getRotation() != this.getRotation())
			m.setRotation(m.getRotation() + 180);
	}

	@Override
	public void act(float delta) {

	}
}
