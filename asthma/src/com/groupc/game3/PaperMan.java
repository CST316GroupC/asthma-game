package com.groupc.game3;

import com.groupc.game.Camera;
import com.groupc.game.MovingGameObject;

public class PaperMan extends MovingGameObject
{
	public final static float WIDTH = 0.1f;
	public final static float HEIGHT = 1f;
	
	
	public final static int STATE_NORMAL = 0;
	public final static int STATE_HIT = 1;
	public final static int STATE_GONE = 2;
	
	private int state;
	private float speed = 0.8f;
	private float stateTime;
	
	private int currentHealth;
	
	public PaperMan(float x, float y, int health)
	{
		super(x, y, WIDTH, HEIGHT);
		state = STATE_NORMAL;
		currentHealth = health;
		// TODO Auto-generated constructor stub
	}
	
	public void update(float deltaTime)
	{
		bounds.lowerLeft.set(position.sub(bounds.width / 2 + 0.5f , bounds.height / 2));
		
		if(stateTime > 1f && state == STATE_HIT)
		{
			state = STATE_NORMAL;
		}
		stateTime += deltaTime;
	}
	
	public void moveLeft(Camera cam)
	{
		if(this.position.x - speed > cam.position.x - cam.frustumWidth/2)
		{
			this.position.x -= speed;
		}
	}
	
	public void moveRight(Camera cam)
	{
		if(this.position.x + speed < cam.position.x + cam.frustumWidth/2)
		{
			this.position.x += speed;
		}
	}
	
	public void hit()
	{
		currentHealth -= 1;
		
		if(currentHealth <= 0)
		{
			state = STATE_GONE;
		}
		else
		{
			state = STATE_HIT;
		}
		
		stateTime = 0;
	}
	
	public void hitHealthGlobe()
	{
		if( currentHealth < 12 )
		{
			currentHealth += 1;
			
			if(currentHealth <= 0)
			{
				state = STATE_GONE;
			}
			else
			{
				state = STATE_HIT;
			}
			
			stateTime = 0;
		}
	}
	
	
	public void hitTreasure()
	{
		//TODO: add functionality or decide to remove treasure powerups
	}
	
	public int getState()
	{
		return state;
	}
	
	public float getStateTime()
	{
		return stateTime;
	}
	
	public int getCurrentHealth()
	{
		return currentHealth;
	}
	
	//setters
	public void setState(int state)
	{
		this.state = state;
	}
	

}
