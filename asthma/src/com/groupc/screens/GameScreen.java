package com.groupc.screens;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.groupc.Runner;


public class GameScreen extends Screen
{
	//testing
	int testx = 0;
	Canvas canvas;
	public GameScreen(Runner run)
	{
		super(run);
		canvas = new Canvas();
		
		//Basic Frame Settings
		run.setTitle("GameScreen");
		run.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		run.setMinimumSize(new Dimension(run.SCR_WIDTH, run.SCR_HEIGHT));
		
		canvas.setFocusable(true);
        canvas.requestFocus();
		canvas.setSize(400, 400);
        canvas.setLocation(64, 64);
        canvas.setIgnoreRepaint(true);
        canvas.setVisible(true);
		this.add(canvas);
		run.setContentPane(this);
		run.setVisible(true);
		
		try {			
			
            //Display.setDisplayMode(new DisplayMode(400, 400));
			
			Display.create();
			Display.setParent(canvas);
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		
		
		//OpenGL INIT                    
	      GL11.glClearColor(0.0f,0.0f,0.0f,0.0f); //black backround           

	      GL11.glMatrixMode(GL_PROJECTION);                                 
	      GL11.glLoadIdentity();                                            
	      GL11.glOrtho(0, 400, 0, 400, 1, -1);      
	      GL11.glViewport(0, 0, 400, 400 );
	      GL11.glMatrixMode(GL_MODELVIEW);
		
	}

	@Override
	public void update(float deltaTime) 
	{
		Display.update();
		Display.sync(60);  
		testx++;
		if(Display.isCloseRequested())
		{
			Display.destroy();
		}
	}

	@Override
	public void present(float deltaTime)
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  
        
        // set the color of the quad (R,G,B,A)
        GL11.glColor3f(0.5f,0.5f,1.0f);
             
        // draw quad
        GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex2f(100 + testx,100);
        GL11.glVertex2f(100+200 + testx,100);
        GL11.glVertex2f(100+200 + testx,100+200);
        GL11.glVertex2f(100 + testx,100+200);
        GL11.glEnd();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}


}
