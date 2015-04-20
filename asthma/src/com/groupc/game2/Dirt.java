package com.groupc.game2;

import java.util.Random;

import com.groupc.game.GameObject;

public class Dirt extends GameObject
{
	public static final float WIDTH = 1f;
	public static final float HEIGHT = 1f;
	
	private boolean hasGem;
	private boolean active;
	public Dirt(float x, float y) 
	{
		super(x, y, WIDTH, HEIGHT);
		Random rand = new Random();
		hasGem = rand.nextBoolean();
		setActive(true);
	}
	
	public boolean getHasGem()
	{
		return hasGem;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
