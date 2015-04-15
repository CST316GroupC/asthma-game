package com.groupc.game3;

import com.groupc.game.GameObject;

public class Rain extends GameObject
{
	public static final float WIDTH = 2f;
	public static final float HEIGHT = 1f;
	public static final float speed = 0.1f;
	private boolean hit;
	public Rain(float x, float y)
	{
		super(x, y, WIDTH, HEIGHT);
		hit = false;
		// TODO Auto-generated constructor stub
	}
	public void update()
	{
		bounds.lowerLeft.set(position.sub(bounds.width / 2, bounds.height / 2));
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
