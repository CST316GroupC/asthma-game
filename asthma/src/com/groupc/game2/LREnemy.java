package com.groupc.game2;

import com.groupc.game.GameObject;


public class LREnemy extends Enemy
{
	public static final float SPEED = 10;
	public LREnemy(float x, float y)
	{
		super(x, y);
		velocity.set(SPEED, 0);
	}
	
	@Override
	public void collision(GameObject wall)
	{
		if(velocity.x > 0)
		{
			position.x = wall.position.x - 1;
		}
		else
		{
			position.x = wall.position.x + 1;
		}
		velocity.set(-1* velocity.x, 0);
	}



	


}
