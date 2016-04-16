package com.fwumdesoft.blow;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class DrawingActor extends Actor {
	public TextureRegion texture;
	
	public DrawingActor(Texture textureData) {
		texture = new TextureRegion(textureData);
		setSize(textureData.getWidth(), textureData.getHeight());
	}
	
	@Override
	public void draw(Batch batch, float parentLevel) {
		batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
}
