package com.groupc.game3;

import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import com.groupc.TextDrawer;
import com.groupc.game.Asset;
import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
import com.groupc.game1.Cow;
import com.groupc.game3.PaperMan;
import com.groupc.game3.Rain;
import com.groupc.math.CollisionChecker;
import com.groupc.math.Rectangle;

public class GameWorld extends GameScreen
{
	public static final float FRUSTUM_WIDTH = 10;
	public static final float FRUSTUM_HEIGHT = 10;
	public static final float WORLD_WIDTH = 500;
	public static final float WORLD_HEIGHT = 500;
	
	public static final int WORLD_STATE_PAUSED = 0;
	public static final int WORLD_STATE_PLAYING = 1;
	public static final int WORLD_STATE_OVER = 2;
	
	public static final float TEXT_SIZE = .3f;
	public static final int MAX_SCORE_DIGITS = 8; //99999999 max score
	public static final int MAX_DISTANCE = 4; //9999 max distance
	
	public static final int GRAVITY = -10;
	
	public int state;
	private int score;
	
	private Camera cam;
	public final PaperMan paper;
	public final Rain[] rain;
	private Random rand;
	
	//TODO: change assets for paperMan and change rain positiong (from array to random objects?)
	public GameWorld(Asset assets)
	{        
		super(assets);
		assets.reload();
		rand = new Random();
		
		cam = new Camera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		
		this.paper = new PaperMan(1, .75f, Integer.parseInt(assets.getProps().getProperty("health")));

		this.state = WORLD_STATE_PLAYING;
		
		this.rain = new Rain[8];
		rain[0] = new Rain(cam.position.x, cam.position.y);
		rain[1] = new Rain(cam.position.x - FRUSTUM_WIDTH/2, cam.position.y);
		rain[2] = new Rain(cam.position.x + FRUSTUM_WIDTH/2, cam.position.y);
		score = Integer.parseInt(assets.getProps().getProperty("score"));
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
	
	public void updatePaper(float deltaTime)
	{
		paper.update(deltaTime);
	}
	
	public void update(float deltaTime)
	{
		inputHandling();
		switch(state)
		{
			case WORLD_STATE_PLAYING:
				updateRain();
				updatePaper(deltaTime);
				collision();
				if(paper.getState() == PaperMan.STATE_GONE)
				{
					state = WORLD_STATE_OVER;
					//TODO: Implement scoring based on total time played
					//score += distance/10;
					save();				
				}
				break;
				
			case WORLD_STATE_OVER:
			case WORLD_STATE_PAUSED:
				break;
		}
	}
	
	/**
	 * Prepares the property file to store all the relevent player data at moment of method called.
	 */
	private void save()
	{
		//TODO: Implement save feature (requires scoring first and checking proper assets current Assets.java saves to joey)
	}
	
	public void inputHandling()
	{
		while (Keyboard.next()) 
		{
			if(Keyboard.getEventKeyState())
			{

			}
			else
			{
				if(Keyboard.getEventKey() == Keyboard.KEY_Q || Keyboard.getEventKey() == Keyboard.KEY_ESCAPE)
				{
					if(state == WORLD_STATE_OVER || state == WORLD_STATE_PAUSED)
		        	{
		        		dispose();
		        	}
				}
				
				if(Keyboard.getEventKey() == Keyboard.KEY_P || Keyboard.getEventKey() == Keyboard.KEY_RETURN)
				{
					if(state == WORLD_STATE_PLAYING || state == WORLD_STATE_OVER)
		        	{
		        		pause();
		        	}
		        	else
		        	{
		        		state = WORLD_STATE_PLAYING;
		        	}
				}
				
				if(Keyboard.getEventKey() == Keyboard.KEY_A || Keyboard.getEventKey() == Keyboard.KEY_LEFT)
				{
					if(state == WORLD_STATE_PLAYING)
					{
						paper.position.x -= 1;
						
					}
				}
				
				if(Keyboard.getEventKey() == Keyboard.KEY_D || Keyboard.getEventKey() == Keyboard.KEY_RIGHT)
				{
					if(state == WORLD_STATE_PLAYING)
					{
						paper.position.x += 1;
						
					}
				}
			}
		}
	}
	
	public void renderPlaying()
	{	
		cam.setCamera();
		
		assets.getImage("wall").draw(new Rectangle(0, 0, 10, 10));
		renderHud();
		renderRain();
		renderPaper();
	}
	
	private void renderPaper()
	{
		Rectangle rect = new Rectangle(paper.position.x - .5f, paper.position.y -.5f, 1.5f, 1.5f);
		
		switch(paper.getState())
		{
		case PaperMan.STATE_NORMAL:
			assets.getImage("paperMan").draw(rect);
			break;
			
		case PaperMan.STATE_HIT:
			//TODO: make flicker or give invincibility image. 
			assets.getImage("paperMan").draw(rect);
			break;
			
		case PaperMan.STATE_GONE:
			assets.getImage("paperGone").draw(rect);
			break;
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
	//TODO: update renderers to properly reflect score for game 3
	public void renderHud()
	{
		//hud
		
		//TextDrawer.drawStringinRect("score", new Rectangle(cam.position.x - FRUSTUM_WIDTH/2, cam.position.y + FRUSTUM_HEIGHT/2 - TEXT_SIZE, TEXT_SIZE * 5, TEXT_SIZE));
		//TextDrawer.drawStringinRect(score +"", new Rectangle(cam.position.x - 3f, cam.position.y + FRUSTUM_HEIGHT/2 - TEXT_SIZE, MAX_SCORE_DIGITS * TEXT_SIZE, TEXT_SIZE));
		//TextDrawer.drawString("seeds", cam.position.x, cam.position.y + FRUSTUM_HEIGHT/2 - TEXT_SIZE, TEXT_SIZE, TEXT_SIZE);
		//TODO: change to score and add health bar, remove null check
		if(paper != null)
		{
			TextDrawer.drawInt(paper.getCurrentHealth(), cam.position.x +1f, cam.position.y + FRUSTUM_HEIGHT/2 - TEXT_SIZE, TEXT_SIZE, TEXT_SIZE, 2);
		}
	}
	
	public void renderOver()
	{
		//TextDrawer.drawString("Distance", cam.position.x - 3, cam.position.y, TEXT_SIZE * 1.5f, TEXT_SIZE * 1.5f);
		//TextDrawer.drawInt((int) distance, cam.position.x - 1, cam.position.y - 1f, TEXT_SIZE * 2, TEXT_SIZE * 2, MAX_DISTANCE);
		TextDrawer.drawString("Press q to quit", cam.position.x - FRUSTUM_WIDTH/2, cam.position.y + FRUSTUM_HEIGHT/2 - 1, TEXT_SIZE * 2, TEXT_SIZE * 2);
		
	}
	
	public void renderPaused()
	{
		TextDrawer.drawString("Paused", cam.position.x-2.5f, cam.position.y, TEXT_SIZE * 3, TEXT_SIZE * 3);
		TextDrawer.drawString("Press q to quit", cam.position.x - FRUSTUM_WIDTH/2, cam.position.y + FRUSTUM_HEIGHT/2 - 1, TEXT_SIZE * 2, TEXT_SIZE * 2);
	}
	
	public void collision()
	{
		collisionRain();
	}
	
	public void collisionRain()
	{
		for(int i = 0; i < rain.length; i++)
		{
			if(rain[i] != null)
			{
				if(CollisionChecker.RectToRect(paper.bounds, rain[i].bounds))
				{
					rain[i].position.set(rand.nextFloat() * (cam.position.x + FRUSTUM_WIDTH/2), cam.position.y + FRUSTUM_HEIGHT/2);
					rain[i].update();
					paper.hit();
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) 
	{
		renderPlaying();
		switch(state)
		{
			case WORLD_STATE_PLAYING:
				break;
			case WORLD_STATE_OVER:
				renderOver();
				break;
			case WORLD_STATE_PAUSED:
				renderPaused();
				break;
		}
	}

	@Override
	public void pause() 
	{
		state = WORLD_STATE_PAUSED;
		save();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	@Override
	public void dispose()
	{
		isClosing = true;
		save();
	}
	
	@Override
	public GameScreen getNext()
	{
		return new MainMenu(assets);
	}
}
