package com.groupc.game4;

import org.lwjgl.input.Mouse;

import com.groupc.TextDrawer;
import com.groupc.game.Asset;
import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
import com.groupc.math.CollisionChecker;
import com.groupc.math.Rectangle;
import com.groupc.math.Vector;

public class MainMenu extends GameScreen
{	
	private final Camera cam;
	
	//Variables
	private int 	next;
	
	//Display regions
	private final 	Rectangle	title;
	private final 	Rectangle	play;
	private final 	Rectangle	instruct;
	private final 	Vector 		mouseClick;
	
	
	public MainMenu(Asset assets)
	{
		super(assets);
		cam = new Camera(400, 400);
		cam.setCamera();
		
		title 		= new Rectangle(50, 300, 300, 100);
		play 		= new Rectangle(125, 200, 150, 75);
		instruct 	= new Rectangle(125, 100, 150, 75);
		
		mouseClick 	= new Vector();
	}

	@Override
	public void update(float deltaTime) 
	{	
		//Mouse Check
		if(Mouse.isButtonDown(0))
		{
			mouseClick.set(Mouse.getX(), Mouse.getY());
			cam.click(mouseClick);
			if(CollisionChecker.PointToRect(mouseClick, play))
			{
				next = 1; //play display
				this.dispose();
				
			}
			if(CollisionChecker.PointToRect(mouseClick, instruct))
			{
				next = 2; //instruct display
				this.dispose();
			}
		}
		
	}

	@Override
	public void present(float deltaTime)
	{
		cam.setCamera();
		//assets.getTexture("sheet").bind();
		assets.getImage("grass").draw(new Rectangle(0, 0, cam.frustumWidth, cam.frustumHeight));
		//assets.getImage("title").draw(title);
		TextDrawer.drawStringinRect("Game 4", title);
		TextDrawer.drawStringinRect("Start", play);
		TextDrawer.drawStringinRect("Instructions", instruct);
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
	public void dispose() 
	{
		isClosing = true;
	}

	@Override
	public GameScreen getNext() 
	{
		
		if(next == 1)
		{
			return new GameWorld(assets);
		}
		else if(next == 2)
		{
			return new Instructions(assets);
		}
		return null;
	}
}