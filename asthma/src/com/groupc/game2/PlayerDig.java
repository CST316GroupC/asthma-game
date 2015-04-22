package com.groupc.game2;

import com.groupc.game.GameObject;

public class PlayerDig extends GameObject
{
	public static final float WIDTH = 1f;
	public static final float HEIGHT = 1f;
	
	private float stateTime;
	public PlayerDig(float x, float y) 
	{
		super(x, y, WIDTH, HEIGHT);
		stateTime = 0;
	}
	
	public void update(float deltaTime)
	{
		stateTime += deltaTime;
		if(stateTime > .15f)
		{
			position.x = -1;
		}		

		bounds.lowerLeft.set(position.sub(bounds.width / 2, bounds.height / 2));
	}
	
	public void reset()
	{
		stateTime = 0;
	}
}
