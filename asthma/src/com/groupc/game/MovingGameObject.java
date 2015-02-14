package com.groupc.game;


public class MovingGameObject extends GameObject
{
	public float velX;
	public float velY;
	public float accelX;
	public float accelY;
	public float maxVX;
	public float maxVY;
	
	public MovingGameObject(float x, float y, int width, int height, float velX, float velY, float accX, float accY, int maxVX, int maxVY)
	{
		super(x, y, width, height);
		this.velX = velX;
		this.accelX = accX;
		this.velY = velY;
		this.accelY = accY;
		this.maxVX = maxVX;
		this.maxVY = maxVY;
	}
}
