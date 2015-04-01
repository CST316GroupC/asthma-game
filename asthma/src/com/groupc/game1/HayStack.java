package com.groupc.game1;

import com.groupc.game.GameObject;

public class HayStack extends GameObject
{
	public static final float HAY_WIDTH = 2;
	public static final float HAY_HEIGHT = 1.5f;
	private boolean hit;
	
	public HayStack(float x, float y) 
	{
		super(x, y, HAY_WIDTH, HAY_HEIGHT);
		hit = false;
	}
	
	public void update()
	{
		bounds.lowerLeft.set(position.sub(bounds.width / 2, bounds.height / 2));		
	}
	
	public boolean getHit()
	{
		return hit;
	}
	
	public void setHit(boolean bool)
	{
		hit = bool;
	}

}
