package com.groupc.game3;

import com.groupc.game.GameObject;

public class Treasure extends GameObject
{

	public static final float WIDTH = 1f;
	public static final float HEIGHT = 1f;
	public static final float speed = 0.09f;
	private boolean hit;
	
	public Treasure(float x, float y)
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
