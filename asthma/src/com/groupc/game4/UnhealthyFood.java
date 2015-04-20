package com.groupc.game4;

public abstract class UnhealthyFood extends Food
{
	@Override
	public int clicked(){
		return -points;
	}
}
