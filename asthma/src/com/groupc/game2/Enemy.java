package com.groupc.game2;

import com.groupc.game.MovingGameObject;

public abstract class Enemy extends MovingGameObject
{
	public static final float WIDTH = .9f;
	public static final float HEIGHT = .9f;
	
	public Enemy(float x, float y) 
	{
		super(x, y, WIDTH, HEIGHT);
	}
	
	public void update(float deltaTime) 
	{
		position.add(velocity.mult(deltaTime));
		bounds.lowerLeft.set(position.sub(bounds.width / 2, bounds.height / 2));
	}
	
	public abstract void collision();

}
