package com.groupc.game4;

import com.groupc.game.GameObject;

public abstract class Food extends GameObject
{

	public Food(float x, float y, float width2, float height2)
	{
		super(x, y, width2, height2);
	}

	protected int points;
	private float stateTime;
	protected float maxTime = 3;
	
	//return the point value (positive or negative) for the object when clicked.
	public int clicked(){
		return points;
	}
	
	//Checks to see if the object has existed longer than 3 seconds. If so, the world will remove it.
	public boolean timedOut(float deltaTime){
		if(stateTime > maxTime){
			return true;
		}
		stateTime += deltaTime;
		return false;
	}

	public void update()
	{
		bounds.lowerLeft.set(position.sub(bounds.width / 2, bounds.height / 2));
	}
}
