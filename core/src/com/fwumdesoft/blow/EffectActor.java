package com.fwumdesoft.blow;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.ReflectionPool;

public class EffectActor extends Actor {
	private Pool<PoolableVector> pools;
	private ShapeRenderer shapes;
	
	public EffectActor() {
		pools = new ReflectionPool<PoolableVector>(PoolableVector.class);
		shapes = new ShapeRenderer();
	}
	
	public void doLightingEffect(float x1, float y1, float x2, float y2, int numRecursions)
	{
		shapes.begin(ShapeType.Line);
		PoolableVector pos1 = pools.obtain(), pos2 = pools.obtain();
		pos1.set(x1, y1);
		pos2.set(x2, y2);
		lightningEffect(pos1, pos2, numRecursions);
		shapes.end();
		pools.free(pos1);
		pools.free(pos2);
	}
	
	private void lightningEffect(PoolableVector pos1, PoolableVector pos2, int numRecursions)
	{
		PoolableVector midpoint = pools.obtain();
		midpoint.set((pos1.x + pos2.x) / 2, (pos1.y + pos2.y) / 2);
		
		PoolableVector offset = pools.obtain();
		offset.set((float)Math.random()*2-1, (float)Math.random()*2-1);
		offset.setLength((float)(Math.random()*pos1.dst(pos2)/4));
		midpoint.add(offset);
		
		if(numRecursions > 0)
		{
			lightningEffect(pos1, midpoint, numRecursions-1);
			lightningEffect(midpoint, pos2, numRecursions-1);
		}
		else
		{
			shapes.line(pos1, midpoint);
			shapes.line(midpoint, pos2);
		}
		
		pools.free(midpoint);
		pools.free(offset);
	}
}
