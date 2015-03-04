package com.groupc.game1;

import com.groupc.game.MovingGameObject;

public class JoeyRooster extends MovingGameObject
{
	public final static float WIDTH = 1f;
	public final static float HEIGHT = .33f;
	
	public final static int MAX_SPEED_H = 25;
	public final static int MAX_SPEED_V = 30;
	
	public final static int STATE_MOVE = 0;
	public final static int STATE_FLYING = 1;
	public final static int STATE_FALLING = 2;
	public final static int STATE_HIT = 3;
	public final static int STATE_BOUNCE = 4;
	public final static int STATE_STOP = 5;
	
	public int state;
	public int stateTime;
	
	public JoeyRooster(float x, float y, float accX)
	{
		super(x, y, WIDTH, HEIGHT);
		state = STATE_MOVE;
		this.accel.set(accX, 0);
		stateTime = 0;
	}
	
	public void update(float deltaTime)
	{
		
		if(state != STATE_MOVE)
		{
			if(velocity.y < MAX_SPEED_V && velocity.y > -1 * MAX_SPEED_V)
			{
				velocity.add(0, World.GRAVITY * deltaTime);
			}
		}
		else
		{
			if(velocity.x < MAX_SPEED_H && velocity.x > -1 * MAX_SPEED_H)
			{
				velocity.add(accel);
			}
		}
		position.add(velocity.mult(deltaTime));
		bounds.lowerLeft.set(position.sub(bounds.width / 2, bounds.height / 2));
		
		if(velocity.y < 0)
		{
			state = STATE_FALLING;
		}
		if(velocity.y > 0)
		{
			state = STATE_FLYING;
		}
		if(position.y < 0)
		{
			position.y = 0;
			state = STATE_BOUNCE;
			velocity.set(velocity.x /1.5f, -1* velocity.y /1.5f);
		}
		
		if(velocity.x <= .1f)
		{
			velocity.set(0, 0);
			state = STATE_STOP;

			//System.out.println(position.x);
		}
		
		stateTime += deltaTime;
	}
	
	public void hitRamp()
	{
		velocity.set(velocity.x, velocity.x);
		accel.set(0, 0);
	}

}
