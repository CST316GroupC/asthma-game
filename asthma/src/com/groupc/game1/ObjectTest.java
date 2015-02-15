package com.groupc.game1;

import org.newdawn.slick.opengl.Texture;

import com.groupc.game.MovingGameObject;

public class ObjectTest extends MovingGameObject
{

	public ObjectTest(float x, float y, int width, int height, Texture text, float velX, float velY, float accX, float accY, int maxVX, int maxVY) {
		super(x, y, width, height, text, velX, accX, velY, accY, maxVX, maxVY);
	}
	
	public void update(float deltaTime)
	{
		x += velX * deltaTime;
		y += velY * deltaTime;
		if(velX <= maxVX || velX >= (-1*maxVX))
		{
			velX += accelX * deltaTime;
		}
		else
		{
			velX = maxVX;
		}
		
		if(velY <= maxVY  || velY >= (-1*maxVY))
		{
			velY += accelY * deltaTime;
		}
		else
		{
			velY = maxVY;
		}
	}
	
	public void draw()
	{
	}

}
