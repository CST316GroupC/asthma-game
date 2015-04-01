package com.groupc.game1;

import com.groupc.game.MovingGameObject;

public class JoeyRooster extends MovingGameObject
{
	public final static float WIDTH = 1f;
	public final static float HEIGHT = .33f;
	
	
	public final static int STATE_SB = 0;
	public final static int STATE_RAMP = 1;
	public final static int STATE_FLYING = 2;
	public final static int STATE_FALLING = 3;
	public final static int STATE_FLAP = 4;
	public final static int STATE_HIT = 5;
	public final static int STATE_BOUNCE = 6;
	public final static int STATE_GLIDE = 7;
	public final static int STATE_STOP = 8;
	
	private int state;
	private float stateTime;
	
	//upgrades
	private final static float INITIAL_SPEED = 7;
	
	private float currentStatima;
	
	public JoeyRooster(float x, float y, float mul, int statima)
	{
		super(x, y, WIDTH, HEIGHT);
		state = STATE_SB;
		this.accel.set(INITIAL_SPEED * mul, 0);
		stateTime = 0;
		currentStatima = 3 + statima - 1;
	}
	
	public void update(float deltaTime)
	{
		//set acceleration
		if(state != STATE_SB && state != STATE_RAMP)
		{
			if(state == STATE_STOP)
			{
				accel.set(0, 0);
			}
		}
		
		//move Joey
		velocity.add(accel.mult(deltaTime));
		position.add(velocity.mult(deltaTime));
		bounds.lowerLeft.set(position.sub(bounds.width / 2, bounds.height / 2));
		
		//determine next state
		if(state == STATE_BOUNCE)
		{
			if(stateTime > .5f)
			{
				accel.set(0, World.GRAVITY);
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

				accel.set(0, World.GRAVITY);
				state = STATE_FLYING;
				stateTime = 0;
			}
		}
		else if(state == STATE_GLIDE)
		{
			if(position.y < 0.5f)
			{
				position.y = 0.5f;
				velocity.set(0, 0);
				state = STATE_STOP;
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
				accel.set(0, World.GRAVITY);
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
		accel.set(0, World.GRAVITY);
	}
	
	public void flap()
	{
		if(currentStatima > 0 && state != STATE_SB && state != STATE_RAMP)
		{

			velocity.set(velocity.x, 10);
			state = STATE_FLAP;
			stateTime = 0;
			currentStatima--;
		}
	}
	
	public void hitCow(Cow cow)
	{
		if(state != STATE_BOUNCE)
		{
			velocity.set(velocity.x, velocity.y * 1.2f);
			if(velocity.y < 0)
			{
				velocity.set(velocity.x, velocity.y * -1);
			}
			position.set(position.x, cow.position.y + Cow.HEIGHT);
			state = STATE_BOUNCE;
			stateTime = 0;			
		}
	}
	
	public void toggleGlide()
	{
		if(state != STATE_SB && state != STATE_RAMP && state != STATE_GLIDE)
		{
			state = STATE_GLIDE;
			velocity.set(velocity.x, 0);
			accel.set(-.1f, -2f);
		}
		else
		{
			state = STATE_FALLING;
			accel.set(0, World.GRAVITY);
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
