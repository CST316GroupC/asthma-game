package com.groupc.game2;

import com.groupc.game.GameObject;

public class UDEnemy extends Enemy
{
	public static final float SPEED = 7;
	
	public UDEnemy(float x, float y)
	{
		super(x, y);
		velocity.set(0, SPEED);
	}

	@Override
	public void collision(GameObject wall) 
	{
		if(velocity.y > 0)
		{
			position.y = wall.position.y - 1;
		}
		else
		{
			position.y = wall.position.y + 1;
		}
		velocity.set(0,-1* velocity.y);
	}

}
