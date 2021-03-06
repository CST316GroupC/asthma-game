package com.groupc.game3;

import java.util.Random;

import org.lwjgl.input.Mouse;

import com.groupc.TextDrawer;
import com.groupc.game.Asset;
import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
import com.groupc.game3.Rain;
import com.groupc.math.CollisionChecker;
import com.groupc.math.Rectangle;
import com.groupc.math.Vector;

public class MainMenu extends GameScreen
{	
	public static final float FRUSTUM_WIDTH = 10;
	public static final float FRUSTUM_HEIGHT = 10;

	public static final int WORLD_STATE_PLAYING = 1;
	
	public int state;
	
	private final Camera cam;
	
	//Variables
	private int 	next;
	
	//Display regions
	private final 	Rectangle	title;
	private final 	Rectangle	play;
	private final 	Rectangle	instruct;
	private final	Rectangle	upgrades;
	
	private final 	Vector 		mouseClick;
	
	private Random rand;
	
	public final Rain[] rain;
	
	
	public MainMenu(Asset assets)
	{
		super(assets);
		assets.reload();
		cam = new Camera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		
		this.state = WORLD_STATE_PLAYING;
		
		title 		= new Rectangle(0.8f, 6.5f, 9, 3);
		play 		= new Rectangle(3.5f, 4, 3, 1.5f);
		instruct 	= new Rectangle(3.5f, 2.5f, 3, 1.5f);
		upgrades	= new Rectangle(3.5f, 1, 3, 1.5f);
		
		mouseClick 	= new Vector();
		
		rand = new Random();
		
		this.rain = new Rain[6];
		rain[0] = new Rain(cam.position.x, cam.position.y);
		rain[1] = new Rain(cam.position.x - FRUSTUM_WIDTH/2, cam.position.y);
		rain[2] = new Rain(cam.position.x + FRUSTUM_WIDTH/2, cam.position.y + 2);
		rain[3] = new Rain(cam.position.x - FRUSTUM_WIDTH/2, cam.position.y - 3);
	}

	public void updateRain()
	{
		for(int i = 0; i < rain.length; i++)
		{
			if(rain[i] != null)
			{
				rain[i].update();
				
				if(rain[i].position.y <= cam.position.y - FRUSTUM_HEIGHT /2)
				{
					rain[i].position.set(rand.nextFloat() * (cam.position.x + FRUSTUM_WIDTH/2), cam.position.y + FRUSTUM_HEIGHT/2);
				}
			}
			else
			{
				int chance = rand.nextInt(10);
				
				if(chance < 5)
				{
					rain[i] = new Rain(rand.nextFloat() * (cam.position.x + FRUSTUM_WIDTH/2), cam.position.y + FRUSTUM_HEIGHT/2);
				}
			}
		}		
	}
	
	
	@Override
	public void update(float deltaTime) 
	{	
		
		updateRain();
		
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
		
		switch(state)
		{
			case WORLD_STATE_PLAYING:
				updateRain();
		}
		
	}
	
	private void renderRain()
	{
		for(int i =0; i < rain.length; i++)
		{
			if(rain[i] != null)
			{
				Rectangle rect = new Rectangle(rain[i].position.x - .2f, rain[i].position.y - .4f, 1f, 1f);
				assets.getImage("rain").draw(rect);
			}
		}
		
	}

	@Override
	public void present(float deltaTime)
	{
		cam.setCamera();
		
		renderPlaying();
		TextDrawer.drawStringinRect("Water dodgelets", title);
		TextDrawer.drawStringinRect("Start", play);
		TextDrawer.drawStringinRect("Instructions", instruct);
		TextDrawer.drawStringinRect("Upgrades", upgrades);
	}

	public void renderPlaying()
	{	
		assets.getImage("wall").draw(new Rectangle(0, 0, cam.frustumWidth, cam.frustumHeight));
		
		renderRain();
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