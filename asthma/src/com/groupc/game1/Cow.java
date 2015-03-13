package com.groupc.game1;

import com.groupc.game.GameObject;

public class Cow extends GameObject
{
	public static final float WIDTH = 2f;
	public static final float HEIGHT = 1f;
	
	public static final int STATE_COW = 0;
	public static final int STATE_HIT = 1;
	
	public int state;
	
	public Cow(float x, float y) {
		super(x, y, WIDTH, HEIGHT);
	}
	
	public void update()
	{
		bounds.lowerLeft.set(position.sub(bounds.width / 2, bounds.height / 2));
		state = STATE_COW;
	}
	
	public void hit()
	{
		state = STATE_HIT;
	}

}
