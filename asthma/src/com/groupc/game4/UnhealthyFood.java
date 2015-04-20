package com.groupc.game4;

public abstract class UnhealthyFood extends Food 
{
	
	public UnhealthyFood(float x, float y, float width2, float height2)
	{
		super(x, y, width2, height2);
	}
	@Override
	public int clicked(){
		return -points;
	}
}
