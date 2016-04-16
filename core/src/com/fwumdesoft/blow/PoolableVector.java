package com.fwumdesoft.blow;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

public class PoolableVector extends Vector2 implements Poolable {
	private static final long serialVersionUID = 1L;

	@Override
	public void reset() {
		set(0, 0);
	}

}
