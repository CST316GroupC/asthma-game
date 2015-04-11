package com.groupc.game2;

import com.groupc.game.MovingGameObject;
import com.groupc.math.Vector;

public class Player extends MovingGameObject
{
	public final static float WIDTH = 1f;
	public final static float HEIGHT = 1f;	
	
	public final static int STATE_STILL = 0;
	public final static int STATE_MOVING = 1;
	public final static int STATE_HIT = 2;
	public final static int STATE_SLIPPING = 3;
	
	private int state;

	private float stateTime;

	public Player(float x, float y) 
	{
		super(x, y, WIDTH, HEIGHT);
		state = STATE_STILL;
		stateTime = 0;
	}
	
	public void update(float deltaTime)
	{
		if(state == STATE_MOVING)
		{
			position.add(velocity.mult(deltaTime));
			bounds.lowerLeft.set(position.sub(bounds.width / 2, bounds.height / 2));
		}
	}
	
	public void setDirection(Vector dir)
	{
		velocity = dir;
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
