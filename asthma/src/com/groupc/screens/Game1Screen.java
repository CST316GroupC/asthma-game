package com.groupc.screens;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import com.groupc.Runner;
import com.groupc.game1.MainMenu;
import com.groupc.game.GameScreen;


public class Game1Screen extends Screen
{
	//testing
	int testx = 0;
	Canvas canvas;
	GameScreen scr;
	public Game1Screen(Runner run)
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
		
		scr = new MainMenu();
		
		         
	}

	@Override
	public void update(float deltaTime) 
	{
		Display.update();
		scr.update(deltaTime);
		Display.sync(60); 
		if(Display.isCloseRequested())
		{
			Display.destroy();
		}
		if(scr.isClosing)
		{
			scr = scr.getNext();
		}
	}

	@Override
	public void present(float deltaTime)
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  
        
		scr.present(deltaTime);
        // set the color of the quad (R,G,B,A)
        
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
