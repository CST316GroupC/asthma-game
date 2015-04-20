package com.groupc.game3;

import com.groupc.game.GameObject;

public class HealthGlobe extends GameObject
{
	public static final float WIDTH = 1f;
	public static final float HEIGHT = 1f;
	public static final float speed = 0.07f;
	private boolean hit;
	
	public HealthGlobe(float x, float y)
	{
		super(x, y, WIDTH, HEIGHT);
		hit = false;	
	}
	
	public void update()
	{
		bounds.lowerLeft.set(position.sub(bounds.width / 2, bounds.height / 2));
		//rain falls
		this.position.y -= speed;
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
