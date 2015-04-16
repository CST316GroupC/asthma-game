package com.groupc.game2;

public class UDEnemy extends Enemy
{
	public static final float SPEED = 20;
	
	public UDEnemy(float x, float y)
	{
		super(x, y);
		velocity.set(0, SPEED);
	}

	@Override
	public void collision() 
	{
		velocity.set(0,-1* velocity.y);
	}

}
