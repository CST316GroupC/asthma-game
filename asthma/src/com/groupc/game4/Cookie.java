package com.groupc.game4;

public class Cookie extends UnhealthyFood
{
	public Cookie(){
		//note, the points will be subtracted since this extends unhealthyfood
		points = 2;
		maxTime = 5;
	}

}
