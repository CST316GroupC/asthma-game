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
	private int lastDirection; //0 = left 1 = up 2 = right 3 = left

	private float stateTime;

	public Player(float x, float y) 
	{
		super(x, y, WIDTH, HEIGHT);
		state = STATE_STILL;
		stateTime = 0;
		lastDirection = 0;
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
		if(velocity.x > 0)
		{
			lastDirection = 2;
		}
		else if(velocity.x < 0)
		{
			lastDirection = 0;
		}
		else if(velocity.y > 0)
		{
			lastDirection = 1;
		}
		else
		{
			lastDirection = 3;
		}
	}
	
	public void hitWall(Wall wall)
	{
		if(lastDirection == 0) //left
		{
			position.x = wall.position.x + 1;
		}
		else if (lastDirection == 1) //up
		{
			position.y = wall.position.y - 1;
		}
		else if (lastDirection == 2) //right
		{
			position.x = wall.position.x - 1;
		}
		else//down
		{
			position.y = wall.position.y + 1;
		}

		bounds.lowerLeft.set(position.sub(bounds.width / 2, bounds.height / 2));
	}
	
	public void hitPit(Pit pit)
	{
		System.out.println("dead");
		if(lastDirection == 0) //left
		{
			position.x = pit.position.x + 1;
		}
		else if (lastDirection == 1) //up
		{
			position.y = pit.position.y - 1;
		}
		else if (lastDirection == 2) //right
		{
			position.x = pit.position.x - 1;
		}
		else//down
		{
			position.y = pit.position.y + 1;
		}

		bounds.lowerLeft.set(position.sub(bounds.width / 2, bounds.height / 2));
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
