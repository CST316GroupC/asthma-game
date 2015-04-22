package com.groupc.game2;

import com.groupc.game.GameObject;
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
	private int lastDirection; //0 = left 1 = up 2 = right 3 = down

	public Player(float x, float y) 
	{
		super(x, y, WIDTH, HEIGHT);
		state = STATE_STILL;
		lastDirection = 0;
	}
	
	public void align()
	{
		position.set(new Vector(Math.round(position.x+.5f) -.5f, Math.round(position.y+.5f) -.5f));
		bounds.lowerLeft.set(position.sub(bounds.width / 2, bounds.height / 2));
	}
	
	public void update(float deltaTime)
	{
		if(state == STATE_MOVING)
		{
			position.add(velocity.mult(deltaTime));
			bounds.lowerLeft.set(position.sub(bounds.width / 2, bounds.height / 2));
		}
	}
	
	public PlayerDig dig()
	{
		PlayerDig dig = null;
		if(lastDirection==0)//left
		{
			dig = new PlayerDig(this.position.x -1, this.position.y);
		}
		else if(lastDirection==1)//up
		{
			dig = new PlayerDig(this.position.x, this.position.y + 1);
		}
		else if(lastDirection==2)//right
		{
			dig = new PlayerDig(this.position.x + 1, this.position.y);
		}
		else if(lastDirection ==3)//down
		{
			dig = new PlayerDig(this.position.x, this.position.y - 1);
		}
		return dig;
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
		else if(velocity.y < 0)
		{
			lastDirection = 3;
		}
	}
	
	public void hitWall(GameObject other)
	{
		if(lastDirection == 0) //left
		{
			position.x = other.position.x + 1;
		}
		else if (lastDirection == 1) //up
		{
			position.y = other.position.y - 1;
		}
		else if (lastDirection == 2) //right
		{
			position.x = other.position.x - 1;
		}
		else if (lastDirection == 3)//down
		{
			position.y = other.position.y + 1;
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
	
	public int getLastDirection()
	{
		return lastDirection;
	}
}
