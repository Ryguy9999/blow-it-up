package com.fwumdesoft.blow;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.ReflectionPool;

public class EffectActor extends Actor {
	private Pool<PoolableVector> pools;

	public EffectActor() {
		pools = new ReflectionPool<PoolableVector>(PoolableVector.class);
	}

	public void draw(Batch batch, float alpha) {
		// DO YOUR SHIT
	}
	
}
