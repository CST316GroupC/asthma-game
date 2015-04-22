package com.groupc.game2;

import com.groupc.game.GameObject;

public class Spike extends GameObject
{
	public static final float WIDTH = .6f;
	public static final float HEIGHT = .6f;
	public static final int STATE_DOWN = 1;
	public static final int STATE_UP = 2;
	
	private int state;
	private float stateTime;
	
	public Spike(float x, float y) 
	{
		super(x, y, WIDTH, HEIGHT);
		stateTime = 0;
		setState(STATE_DOWN);
	}
	
	public void update(float deltaTime)
	{
		stateTime += deltaTime;
		if(stateTime > 2f)
		{
			state = STATE_DOWN;
			stateTime = 0;
		}
		else if(stateTime > 1f)
		{
			state = STATE_UP;
		}
		
	}

	public int getState() 
	{
		return state;
	}

	public void setState(int state)
	{
		this.state = state;
	}

}
