package com.groupc.game2;

import com.groupc.game.MovingGameObject;

public class LREnemy extends Enemy
{
	public static final float WIDTH = .9f;
	public static final float HEIGHT = .9f;
	public static final float SPEED = 20;
	public LREnemy(float x, float y)
	{
		super(x, y, WIDTH, HEIGHT);
		velocity.set(SPEED, 0);
	}
	
	@Override
	public void update(float deltaTime) 
	{
		position.add(velocity.mult(deltaTime));
		bounds.lowerLeft.set(position.sub(bounds.width / 2, bounds.height / 2));
	}
	
	@Override
	public void collision()
	{
		velocity.set(-1* velocity.x, 0);
	}



	


}
