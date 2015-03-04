package com.groupc.game1;

import com.groupc.game.GameObject;

public class Seed extends GameObject
{
	public final static float WIDTH = .2f;
	public final static float HEIGHT = .4f;
	
	public Seed(float x, float y) {
		super(x, y, WIDTH, HEIGHT);
	}
	
	public void update()
	{
		bounds.lowerLeft.set(position.sub(bounds.width / 2, bounds.height / 2));
		
	}

}
