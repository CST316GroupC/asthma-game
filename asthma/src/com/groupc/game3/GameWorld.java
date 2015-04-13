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
import com.groupc.math.CollisionChecker;
import com.groupc.math.Rectangle;

public class GameWorld extends GameScreen
{
	public static final float FRUSTUM_WIDTH = 10;
	public static final float FRUSTUM_HEIGHT = 10;
	public static final float WORLD_WIDTH = FRUSTUM_WIDTH * 500; //400 (the * 100 means the total width is 5000 pixels but only 400 shown)
	public static final float WORLD_HEIGHT = FRUSTUM_HEIGHT * 25; //400 (the * 20 means the total height is 200 pixels but only 400 shown)
	
	public static final int WORLD_STATE_PAUSED = 0;
	public static final int WORLD_STATE_PLAYING = 1;
	public static final int WORLD_STATE_OVER = 2;
	
	public static final float TEXT_SIZE = .3f;
	public static final int MAX_SCORE_DIGITS = 8; //99999999 max score
	public static final int MAX_SEED_DIGITS = 4; //9999 max seeds
	public static final int MAX_DISTANCE = 4; //9999 max distance
	
	public static final int GRAVITY = -10;
	
	public int state;
	private int seedsCollected;
	private int score;
	private float distance;
	
	private Camera cam;
	private Random rand;
	
	public GameWorld(Asset assets)
	{        
		super(assets);
		assets.reload();
		rand = new Random();
		this.state = WORLD_STATE_PLAYING;
		cam = new Camera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
	}
	
	public void update(float deltaTime)
	{
		inputHandling();
		switch(state)
		{
			case WORLD_STATE_PLAYING:
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
			}
		}
	}
	
	public void renderPlaying()
	{	
		cam.setCamera();
		
		//assets.getTexture("sheet").bind();
		assets.getImage("sky").draw(new Rectangle(0, 3, WORLD_WIDTH, WORLD_HEIGHT));
		assets.getImage("grass").draw(new Rectangle(0, 0, WORLD_WIDTH, 3));
		renderHud();
	}
	
	public void renderHud()
	{
		//hud
		
		TextDrawer.drawStringinRect("score", new Rectangle(cam.position.x - FRUSTUM_WIDTH/2, cam.position.y + FRUSTUM_HEIGHT/2 - TEXT_SIZE, TEXT_SIZE * 5, TEXT_SIZE));
		TextDrawer.drawStringinRect(score +"", new Rectangle(cam.position.x - 3f, cam.position.y + FRUSTUM_HEIGHT/2 - TEXT_SIZE, MAX_SCORE_DIGITS * TEXT_SIZE, TEXT_SIZE));
		TextDrawer.drawString("seeds", cam.position.x, cam.position.y + FRUSTUM_HEIGHT/2 - TEXT_SIZE, TEXT_SIZE, TEXT_SIZE);
		TextDrawer.drawInt(seedsCollected, cam.position.x +1f, cam.position.y + FRUSTUM_HEIGHT/2 - TEXT_SIZE, TEXT_SIZE, TEXT_SIZE, MAX_SEED_DIGITS);
		
	}
	
	public void renderOver()
	{
		TextDrawer.drawString("Distance", cam.position.x - 3, cam.position.y, TEXT_SIZE * 1.5f, TEXT_SIZE * 1.5f);
		TextDrawer.drawInt((int) distance, cam.position.x - 1, cam.position.y - 1f, TEXT_SIZE * 2, TEXT_SIZE * 2, MAX_DISTANCE);
		TextDrawer.drawString("Press q to quit", cam.position.x - FRUSTUM_WIDTH/2, cam.position.y + FRUSTUM_HEIGHT/2 - 1, TEXT_SIZE * 2, TEXT_SIZE * 2);
		
	}
	
	public void renderPaused()
	{
		TextDrawer.drawString("Paused", cam.position.x-2.5f, cam.position.y, TEXT_SIZE * 3, TEXT_SIZE * 3);
		TextDrawer.drawString("Press q to quit", cam.position.x - FRUSTUM_WIDTH/2, cam.position.y + FRUSTUM_HEIGHT/2 - 1, TEXT_SIZE * 2, TEXT_SIZE * 2);
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
