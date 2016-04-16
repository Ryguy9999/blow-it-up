package com.fwumdesoft.blow;

import com.badlogic.gdx.graphics.g2d.Batch;

public class Reflector extends DrawingActor {


	public float range;
	public int lane;
	
	public Reflector(int lane) {
		this(10, lane);
	}
	
	public Reflector(float range, int lane) {
		this.range = range;
		this.lane = lane;
	}
	
	public void launchMissile() {
		getStage().addActor(new Missile(10, 0, 0, lane));
	}
	
	public Missile reflectMissile(Missile m) {
		
	}
	
	@Override
	public void act(float delta) {
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		
	}
}
