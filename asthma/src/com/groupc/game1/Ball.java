package com.groupc.game1;

import com.groupc.game.MovingGameObject;

public class Ball extends MovingGameObject
{

	public Ball(float x, float y, int width, int height) 
	{
		super(x, y, width, height, 50 ,50);
	}
	
	public void update(float deltaTime)
	{
		updateVectors(deltaTime);
		rect.lowerLeft.add(velocity.mult(deltaTime));
	}

}
