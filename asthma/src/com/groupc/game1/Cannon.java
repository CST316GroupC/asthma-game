package com.groupc.game1;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import com.groupc.game.GameObject;

public class Cannon extends GameObject
{
	public static float TO_RADIANS = (1 / 180.0f) * (float) Math.PI;
	public static float TO_DEGREES = (1 / (float) Math.PI) * 180;
	public float angle;
	float rotX;
	float rotY;

	public Cannon(float x, float y, int width, int height, Texture text) {
		super(x, y, width, height, text);
		rotX = x;
		rotY = y;
	}
	
	public void rotate(float angle) {
		

		this.rotX = (float) (width * Math.cos(angle));
		this.rotY = (float) (width * Math.sin(angle));
	}

	public void update(float deltaTime)
	{
		if (angle < 0)
		{
			angle = 0;
		}
		if(angle > 180)
		{
			angle = 180;
		}
		rotate(angle);
	}
	@Override
	public void draw()
	{
		texture.bind(); // or GL11.glBind(texture.getTextureID());
        
        // draw cannon base
		 GL11.glBegin(GL11.GL_QUADS);
         GL11.glTexCoord2f(0,0.5f);
         GL11.glVertex2f(x, y);
         GL11.glTexCoord2f(0.5f, 0.5f);
         GL11.glVertex2f(x, height/2 + y);
         GL11.glTexCoord2f(0.5f, 1);
         GL11.glVertex2f(width/2 + x, height/2 + y);
         GL11.glTexCoord2f(0, 1);
         GL11.glVertex2f(width/2 + x, y);
         GL11.glEnd();
        
        //draw cannon 
         GL11.glBegin(GL11.GL_QUADS);
         GL11.glTexCoord2f(0,0.5f);
         GL11.glVertex2f(x, height/2 + y);
         GL11.glTexCoord2f(0, 0);
         GL11.glVertex2f(x, height + y);
         GL11.glTexCoord2f(1, 0);
         GL11.glVertex2f(rotX, height + rotY);
         GL11.glTexCoord2f(1, 0.5f);
         GL11.glVertex2f(rotX, height/2 + rotY);
         GL11.glEnd();
	}
}
