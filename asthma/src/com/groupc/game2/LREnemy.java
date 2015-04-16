package com.groupc.game2;


public class LREnemy extends Enemy
{
	public static final float SPEED = 15;
	public LREnemy(float x, float y)
	{
		super(x, y);
		velocity.set(SPEED, 0);
	}
	
	@Override
	public void collision()
	{
		velocity.set(-1* velocity.x, 0);
	}



	


}
