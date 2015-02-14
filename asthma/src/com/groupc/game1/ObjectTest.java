package com.groupc.game1;

import org.lwjgl.opengl.GL11;

import com.groupc.game.MovingGameObject;

public class ObjectTest extends MovingGameObject
{

	public ObjectTest(float x, float y, int width, int height, float velX, float velY, float accX, float accY, int maxVX, int maxVY) {
		super(x, y, width, height, velX, accX, velY, accY, maxVX, maxVY);
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
		GL11.glColor3f(0.5f,0.5f,1.0f);
        
        // draw quad
        GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex2f(x,y);
            GL11.glVertex2f(x+width, y);
            GL11.glVertex2f(x+width, y+height);
            GL11.glVertex2f(x,y+height);
        GL11.glEnd();
	}

}
