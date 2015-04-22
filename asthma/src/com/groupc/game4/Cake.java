package com.groupc.game4;

public class Cake extends UnhealthyFood
{
	public static final float WIDTH = 1f;
	public static final float HEIGHT = 1f;
	public Cake(float x, float y){
		super(x, y, WIDTH, HEIGHT);
		//note, the points will be subtracted since this extends unhealthyfood
		points = 4;
		maxTime = 5;
	}
}
