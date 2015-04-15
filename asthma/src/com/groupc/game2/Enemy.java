package com.groupc.game2;

import com.groupc.game.MovingGameObject;

public abstract class Enemy extends MovingGameObject
{
	public Enemy(float x, float y, float width, float height) 
	{
		super(x, y, width, height);
	}
	
	public abstract void update(float deltaTime);
	
	public abstract void collision();

}
