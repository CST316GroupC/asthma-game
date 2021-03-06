package com.groupc.screens;

/*
 * Author(s):		Team C
 * Course: 			CST 316 Spring
 * Instructor:		Dr. Gary
 * Date Changed:	4/1/2015
 * 
 * Description:		Appears when Game 1 button is clicked. Executes Game 1.
 */

import static org.lwjgl.opengl.GL11.*;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import com.groupc.Runner;
import com.groupc.TextDrawer;
import com.groupc.game1.Game1Assets;
import com.groupc.game1.MainMenu;
import com.groupc.game.GameScreen;
import com.groupc.math.Resize;


public class Game1Screen extends Screen
{
	//Variables
	boolean redraw     	= true;
	Resize  resize     	= new Resize(run);
	int     butPressed 	= 0;
	int testx 			= 0;	

	//Display Elements
	NavigationBar navBar = new NavigationBar(run,true,false,"Game 1");
	
	Canvas 		canvas;
	GameScreen 	scr;
	Game1Assets	assets;
	
	public Game1Screen(Runner run)
	{
		super(run);
		canvas = new Canvas();
		
		//Basic Frame Settings
		run.setTitle(run.getUserName()+" | GameScreen");
		
		//resize stuff
		run.addComponentListener(new ComponentAdapter()
		{
			public void componentResized(ComponentEvent e)
			{
				redraw = true;
			}
		});
		
		//Set colors
		this.setBackground(Color.WHITE);
		
		canvas.setFocusable(true);
        canvas.requestFocus();
        canvas.setIgnoreRepaint(true);
        canvas.setVisible(true);
        
		this.add(navBar);
		this.add(canvas);
		
		this.setLayout(null);
		run.setContentPane(this);
		run.setVisible(true);
		
		
		run.setContentPane(this);
		run.setVisible(true);
		
		try {			
			
            //Display.setDisplayMode(new DisplayMode(400, 400));
			Display.setParent(canvas);
			Display.create();
			TextDrawer.prepare();
			assets = new Game1Assets(run.getUserName());
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		
		scr = new MainMenu(assets);
		
		         
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
		
		if(redraw)
		{	
			//navBar
			navBar.redrawUpdate();
			
			//canvas
			canvas.setBounds(resize.locationX(0), resize.locationY(100), resize.width(500), resize.height(400));
			run.repaint();
			redraw = false;
		}
		
		if(navBar.backButtonPressed)
		{
			Display.destroy();
			run.setScreen(new GameHubScreen(run));
		}
		
		navBar.update();
	}

	@Override
	public void present(float deltaTime)
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  
        
		scr.present(deltaTime);
        // set the color of the quad (R,G,B,A)
        
	}

	@Override
	public void pause() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() 
	{
		// TODO Auto-generated method stub
		
	}


}
