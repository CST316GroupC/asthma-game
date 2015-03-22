package com.groupc.game1;

import com.groupc.game.MovingGameObject;

public class JoeyRooster extends MovingGameObject
{
	public final static float WIDTH = 1f;
	public final static float HEIGHT = .33f;
	
	public final static int MAX_SPEED_H = 25;
	public final static int MAX_SPEED_V = 30;
	
	public final static int STATE_SB = 0;
	public final static int STATE_RAMP = 1;
	public final static int STATE_FLYING = 2;
	public final static int STATE_FALLING = 3;
	public final static int STATE_FLAP = 4;
	public final static int STATE_HIT = 5;
	public final static int STATE_BOUNCE = 6;
	public final static int STATE_STOP = 7;
	
	private int state;
	private float stateTime;
	
	//upgrades
	private final float INITIAL_SPEED = 5;
	
	private float currentStatima;
	
	public JoeyRooster(float x, float y, float mul, int statima)
	{
		super(x, y, WIDTH, HEIGHT);
		state = STATE_SB;
		this.accel.set(INITIAL_SPEED * mul, 0);
		stateTime = 0;
		currentStatima = statima;
	}
	
	public void update(float deltaTime)
	{
		if(state != STATE_SB && state != STATE_RAMP)
		{
			if(state == STATE_FLAP)
			{
				velocity.set(velocity.x, 3);
			}
			else if(velocity.y < MAX_SPEED_V && velocity.y > -1 * MAX_SPEED_V)
			{
				velocity.add(0, World.GRAVITY * deltaTime);
			}
			else
			{
				velocity.set(velocity.x, MAX_SPEED_V);
				if(velocity.y < 0)
				{
					velocity.set(velocity.x, velocity.y * -1);
				}
			}
		}
		else
		{
			if(velocity.x < MAX_SPEED_H && velocity.x > -1 * MAX_SPEED_H)
			{
				System.out.println(accel.x * deltaTime);
				velocity.add(accel.x * deltaTime, accel.y);
			}
			else
			{
				accel.set(0, 0);
			}
		}
		position.add(velocity.mult(deltaTime));
		bounds.lowerLeft.set(position.sub(bounds.width / 2, bounds.height / 2));
		
		if(state == STATE_BOUNCE)
		{
			if(stateTime > .5f)
			{
				state = STATE_FLYING;
				stateTime = 0;
			}
			if(velocity.y < 0)
			{
				state = STATE_FALLING;
			}
		}
		else if(state == STATE_FLAP)
		{
			if(stateTime > .5f)
			{
				state = STATE_FLYING;
				stateTime = 0;
			}
		}
		else
		{
			if(velocity.y < 0)
			{
				state = STATE_FALLING;
			}
		
			if(velocity.y > 0)
			{
				state = STATE_FLYING;
			}
		
			if(position.y < 0.5f)
			{
				position.y = 0.5f;
				state = STATE_BOUNCE;
				velocity.set(velocity.x /1.5f, -1* velocity.y /1.5f);
				stateTime = 0;
			}
		}
		if(velocity.x <= .1f && state != STATE_SB)
		{
			if(position.y > 0)
			{
				state = STATE_FALLING;
			}
			velocity.set(0, 0);
			state = STATE_STOP;

			//System.out.println(position.x);
		}
		
		stateTime += deltaTime;
	}
	
	public void hitRamp()
	{
		state = STATE_RAMP;
		velocity.set(velocity.x, velocity.x);
		accel.set(0, 0);
	}
	
	public void flap()
	{
		if(currentStatima > 0 && state != STATE_SB && state != STATE_RAMP)
		{
			state = STATE_FLAP;
			stateTime = 0;
			currentStatima--;
		}
	}
	
	public void hitCow()
	{
		if(state != STATE_BOUNCE)
		{
			velocity.set(velocity.x, velocity.y * 1.2f);
			if(velocity.y < 0)
			{
				velocity.set(velocity.x, velocity.y * -1);
			}
			state = STATE_BOUNCE;
			stateTime = 0;
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
