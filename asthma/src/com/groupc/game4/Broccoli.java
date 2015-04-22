package com.groupc.game4;

public class Broccoli extends HealthyFood
{
	public static final float WIDTH = 1f;
	public static final float HEIGHT = 1f;
	public Broccoli(float x, float y, int foodTimeBonus, int foodScoreBonus)
	{
		super(x, y, WIDTH, HEIGHT);
		points = 2 + foodScoreBonus * 3;
		maxTime += foodTimeBonus;
	}
}
