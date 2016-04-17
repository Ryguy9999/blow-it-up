package com.fwumdesoft.blow;

import com.badlogic.gdx.graphics.Texture;

public class BackgroundActor extends DrawingActor{

	public BackgroundActor() {
		super(BlowItUp.assets.get("stars.png", Texture.class));
		setBounds(0, 0, 1920, 1080);
	}
	
}
