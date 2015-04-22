package com.groupc.game4;

public class Cookie extends UnhealthyFood
{
	public static final float WIDTH = 1f;
	public static final float HEIGHT = 1f;
	public Cookie(float x, float y){
		super(x, y, WIDTH, HEIGHT);
		//note, the points will be subtracted since this extends unhealthyfood
		points = 2;
		maxTime = 5;
	}

}
