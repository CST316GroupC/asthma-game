package com.groupc.game4;

public class Orange extends HealthyFood
{
	
	public static final float WIDTH = 1f;
	public static final float HEIGHT = 1f;
	public Orange(float x, float y)
	{
		super(x, y, WIDTH, HEIGHT);
		points = 4;
	}

}
