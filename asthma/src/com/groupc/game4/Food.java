package com.groupc.game4;

public abstract class Food
{
	protected int points;
	private float stateTime;
	protected float maxTime = 3;
	
	//return the point value (positive or negative) for the object when clicked.
	public int clicked(){
		return points;
	}
	
	//Checks to see if the object has existed longer than 3 seconds. If so, the world will remove it.
	public boolean timedOut(int deltaTime){
		if(stateTime > maxTime){
			return true;
		}
		stateTime += deltaTime;
		return false;
	}
}
