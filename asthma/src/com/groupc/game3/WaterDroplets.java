package com.groupc.game3;

import com.groupc.game.MovingGameObject;

public class WaterDroplets extends MovingGameObject
{
	public final static float WIDTH = 1.0f;
	public final static float HEIGHT = 1.0f;	
	
	public final static int STATE_FALLING = 0;
	
	
	private int state;
	private int stateTime;
	private int currentStatima;
	
	private final static float INITIAL_SPEED = 1;
	
	// Requires more work (Vincent)
	public WaterDroplets(float x, float y, float mul, int statima) 
	{
		super(x, y, WIDTH, HEIGHT);
		state = STATE_FALLING;
		this.accel.set(INITIAL_SPEED * mul, 0);
		stateTime = 0;
		currentStatima = 3 + statima - 1;
	}

	public void update(float deltaTime)
	{
		//move droplet
		velocity.add(accel.mult(deltaTime));
		position.add(velocity.mult(deltaTime));
		bounds.lowerLeft.set(position.sub(bounds.width / 2, bounds.height / 2));
		
		if(state == STATE_FALLING)
		{
			velocity.set(velocity.y, velocity.y);
		}
	}
	
	//getters
	
	public int getState()
	{
		return state;
	}
	
	public float getStateTime()
	{
		return stateTime;
	}
	
	public float getCurrentStatima()
	{
		return currentStatima;
	}
	
	//setters
	public void setState(int state)
	{
		this.state = state;
	}
}
