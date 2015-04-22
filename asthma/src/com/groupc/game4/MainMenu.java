package com.groupc.game4;

import org.lwjgl.input.Mouse;

import com.groupc.TextDrawer;
import com.groupc.game.Asset;
import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
import com.groupc.game4.Upgrades;
import com.groupc.math.CollisionChecker;
import com.groupc.math.Rectangle;
import com.groupc.math.Vector;

public class MainMenu extends GameScreen
{	
	public static final float FRUSTUM_WIDTH = 10;
	public static final float FRUSTUM_HEIGHT = 10;
	private final Camera cam;
	
	//Variables
	private int 	next;
	
	//Display regions
	private final 	Rectangle	title;
	private final 	Rectangle	play;
	private final 	Rectangle	instruct;
	private final	Rectangle	upgrades;
	private final 	Vector 		mouseClick;
	
	
	public MainMenu(Asset assets)
	{
		super(assets);
		cam = new Camera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		cam.setCamera();
		
		title 		= new Rectangle(0.8f, 6.5f, 9, 3);
		play 		= new Rectangle(3.5f, 4, 3, 1.5f);
		instruct 	= new Rectangle(3.5f, 2.5f, 3, 1.5f);
		upgrades	= new Rectangle(3.5f, 1, 3, 1.5f);
		
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
			else if(CollisionChecker.PointToRect(mouseClick, instruct))
			{
				next = 2; //instruct display
				this.dispose();
			}
			else if(CollisionChecker.PointToRect(mouseClick, upgrades))
			{
				next = 3; //upgrades display
				this.dispose();
			}
		}
		
	}

	@Override
	public void present(float deltaTime)
	{
		cam.setCamera();
		//assets.getTexture("sheet").bind();
		assets.getImage("table").draw(new Rectangle(0, 0, cam.frustumWidth, cam.frustumHeight));
		//assets.getImage("title").draw(title);
		TextDrawer.drawStringinRect("Food Clicker", title);
		TextDrawer.drawStringinRect("Start", play);
		TextDrawer.drawStringinRect("Instructions", instruct);
		TextDrawer.drawStringinRect("Upgrades", upgrades);
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
		else if(next == 3)
		{
			return new Upgrades(assets);
		}
		return null;
	}
}