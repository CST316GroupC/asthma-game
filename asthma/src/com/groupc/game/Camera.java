package com.groupc.game;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.groupc.math.Vector;

public class Camera 
{
	public final Vector position;
	public float zoom;
	public final float frustumWidth;
	public final float frustumHeight;
	
	public Camera(float frustumWidth, float frustumHeight)
	{
		this.frustumWidth = frustumWidth;
		this.frustumHeight = frustumHeight;
		this.position = new Vector(frustumWidth /2, frustumHeight /2);
		this.zoom = 1.0f;
	}
	
	public void setCamera()
	{
		//OpenGL INIT                    
	    GL11.glClearColor(0.0f,0.0f,0.0f,0.0f); //black backround           
	     
		GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    	GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(position.x - frustumWidth * zoom / 2, 
                position.x + frustumWidth * zoom/ 2, 
                position.y - frustumHeight * zoom / 2, 
                position.y + frustumHeight * zoom/ 2, 
                1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
	}
	
	public void click(Vector clk)
	{
		clk.set(clk.x * (frustumWidth / Display.getWidth()), clk.y * (frustumHeight / Display.getHeight()));
	}
}

