package com.groupc.game2;

import com.groupc.game.GameObject;

public class Gem extends GameObject
{
	public static final float WIDTH = .7f;
	public static final float HEIGHT = .7f;
	
	private boolean active;
	
	public Gem(float x, float y)
	{
		super(x, y, WIDTH, HEIGHT);
		setActive(true);
	}

	public boolean isActive() 
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

}
