package com.groupc.game;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class GameObject
{
	public Texture texture;
	public float x;
	public float y;
	public float width;
	public float height;
	
	public GameObject(float x, float y, int width, int height, Texture text)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.texture = text;
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

