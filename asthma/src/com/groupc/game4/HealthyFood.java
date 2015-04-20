package com.groupc.game4;

public abstract class HealthyFood extends Food
{
	@Override
	public int clicked(){
		return points;
	}
}
