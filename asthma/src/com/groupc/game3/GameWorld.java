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

import com.groupc.Database;
import com.groupc.TextDrawer;
import com.groupc.game.Asset;
import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
import com.groupc.game1.Cow;
import com.groupc.game1.Game1Assets;
import com.groupc.game3.PaperMan;
import com.groupc.game3.Rain;
import com.groupc.game3.HealthGlobe;
import com.groupc.math.CollisionChecker;
import com.groupc.math.Rectangle;

public class GameWorld extends GameScreen
{
	public static final float 	FRUSTUM_WIDTH 		= 10;
	public static final float 	FRUSTUM_HEIGHT 		= 10;
	
	public static final int 	WORLD_STATE_PAUSED 	= 0;
	public static final int 	WORLD_STATE_PLAYING = 1;
	public static final int 	WORLD_STATE_OVER 	= 2;
	
	public static final float 	TEXT_SIZE 			= .3f;

	public int 		state;
	
	private int		healthGlobeAmount;
	private int		chestAmount;
	private int 	previousGameScore;
	private int 	gameScore;
	private int 	hitCounter;
	private int		updatedScore;
	private int		rainCount;
	private int		treasureCount;
	private int 	healthGlobeCount;
	private int		currentGameScore;
	
	private boolean rain30Sec;
	private boolean rain60Sec;
	
	private float 	time;
	private float 	gameTime;
	private float	gameScoreHud;
	
	private Camera 				cam;
	private Random 				rand;
	
	public final PaperMan 		paper;
	public final Rain 			rainHit;
	public 		 Rain[] 		rain;
	public 		 HealthGlobe[] 	healthGlobe;
	public 		 Treasure[] 	treasure;
	
	public GameWorld(Asset assets)
	{        
		super(assets);
		assets.reload();
		
		rain30Sec 			= false;
		rain60Sec			= false;
		
		rainCount			=	4;
		treasureCount		=	1;
		healthGlobeCount	=	1;
		hitCounter 			= 	0;
		time 				= 	0;
		gameTime			=	0;
		currentGameScore	=	0;
		
		rand = 			new Random();
		
		cam = 			new Camera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		
		this.paper = 	new PaperMan(0.75f, .75f, Integer.parseInt(assets.getProps().getProperty("game3PaperHealth")));

		this.rainHit = 	new Rain(0, 0);
		
		this.state = 	WORLD_STATE_PLAYING;
	
		previousGameScore = Integer.parseInt(assets.getProps().getProperty("game3Score"));
		
		//gameScore = previousGameScore;
		
		// Rain droplets initializations
		this.rain = 	new Rain[rainCount];
		rain[0] = new Rain(cam.position.x + FRUSTUM_WIDTH, cam.position.y);
		rain[1] = new Rain(cam.position.x + FRUSTUM_WIDTH/2 + 0.3f, cam.position.y);
		rain[2] = new Rain(cam.position.x + FRUSTUM_WIDTH/2, cam.position.y + 2);
		rain[3] = new Rain(cam.position.x + FRUSTUM_WIDTH/2 + 0.2f, cam.position.y - 3);
		
		// Health globe initialization
		healthGlobeAmount = Integer.parseInt(assets.getProps().getProperty("game3HealthGlobe"));
				
		this.healthGlobe = new HealthGlobe[healthGlobeAmount];
		healthGlobe[0] = new HealthGlobe(cam.position.x - FRUSTUM_WIDTH/2, cam.position.y);
		
		// Treasure initialization
		chestAmount = Integer.parseInt(assets.getProps().getProperty("game3Chest"));
		
		this.treasure = new Treasure[chestAmount];
		treasure[0] = new Treasure(cam.position.x - FRUSTUM_WIDTH/2 + 5, cam.position.y);
	}
	
	public void updateRain()
	{
		if((int)gameTime >=30 && rain30Sec == false )
		{
			rainCount = 8;
			this.rain = new Rain[rainCount];

			rain[0] = new Rain(cam.position.x + FRUSTUM_WIDTH, cam.position.y);
			rain[1] = new Rain(cam.position.x + FRUSTUM_WIDTH/2 + 0.3f, cam.position.y);
			rain[2] = new Rain(cam.position.x + FRUSTUM_WIDTH/2, cam.position.y + 2);
			rain[3] = new Rain(cam.position.x + FRUSTUM_WIDTH/2 + 0.2f, cam.position.y - 3);
			
			
			rainHit.speedIncrease((float) 0.02);
			rain30Sec = true;
		}
		else if((int)gameTime >=60 && rain60Sec == false )
		{
			rainCount = 12;
			this.rain = new Rain[rainCount];

			rain[0] = new Rain(cam.position.x + FRUSTUM_WIDTH, cam.position.y);
			rain[1] = new Rain(cam.position.x + FRUSTUM_WIDTH/2 + 0.3f, cam.position.y);
			rain[2] = new Rain(cam.position.x + FRUSTUM_WIDTH/2, cam.position.y + 2);
			rain[3] = new Rain(cam.position.x + FRUSTUM_WIDTH/2 + 0.2f, cam.position.y - 3);
			rain[4] = new Rain(cam.position.x + FRUSTUM_WIDTH/2 + 0.4f, cam.position.y - 2);
			rain[5] = new Rain(cam.position.x + FRUSTUM_WIDTH/2 + 0.7f, cam.position.y - 1);
			rain[6] = new Rain(cam.position.x + FRUSTUM_WIDTH/2 + 0.1f, cam.position.y + 1);
			
			rainHit.speedIncrease((float) 0.03);
			rain60Sec = true;
		}
		
		for(int i = 0; i < rain.length; i++)
		{
			if(rain[i] != null)
			{
				rain[i].update();
				
				if(rain[i].position.y <= cam.position.y - FRUSTUM_HEIGHT /2)
				{
					rain[i].position.set(rand.nextFloat() * (cam.position.x + FRUSTUM_WIDTH/2), rand.nextFloat() + cam.position.y + FRUSTUM_HEIGHT/2);
				}				
			}
			else
			{	
				int chance = rand.nextInt(10);
				
				if(chance < 5)
				{
					rain[i] = new Rain(rand.nextFloat() * (cam.position.x + FRUSTUM_WIDTH/2), rand.nextFloat() + cam.position.y + FRUSTUM_HEIGHT/2);
				}
			}
		}		
	}
	
	public void updatePaper(float deltaTime)
	{
		paper.update(deltaTime);
	}
	
	public void updateHealthGlobe()
	{
		for(int i = 0; i < healthGlobe.length; i++)
		{
			if(healthGlobe[i] != null)
			{
				healthGlobe[i].update();
				
				if(healthGlobe[i].position.y <= cam.position.y - FRUSTUM_HEIGHT /2)
				{
					healthGlobe[i].position.set(rand.nextFloat() * (cam.position.x + FRUSTUM_WIDTH/2), cam.position.y + FRUSTUM_HEIGHT/2);
				}
			}
			else
			{
				int chance = rand.nextInt(10);
				
				if(chance < 5)
				{
					healthGlobe[i] = new HealthGlobe(rand.nextFloat() * (cam.position.x + FRUSTUM_WIDTH/2), cam.position.y + FRUSTUM_HEIGHT/2);
				}
			}
		}		
	}
	
	public void updateTreasure()
	{
		for(int i = 0; i < treasure.length; i++)
		{
			if(treasure[i] != null)
			{
				treasure[i].update();
				
				if(treasure[i].position.y <= cam.position.y - FRUSTUM_HEIGHT /2)
				{
					treasure[i].position.set(rand.nextFloat() * (cam.position.x + FRUSTUM_WIDTH/2), cam.position.y + FRUSTUM_HEIGHT/2);
				}
			}
			else
			{
				int chance = rand.nextInt(10);
				
				if(chance < 5)
				{
					treasure[i] = new Treasure(rand.nextFloat() * (cam.position.x + FRUSTUM_WIDTH/2), cam.position.y + FRUSTUM_HEIGHT/2);
				}
			}
		}		
	}
	
	
	public void update(float deltaTime)
	{
		inputHandling();
		switch(state)
		{
			case WORLD_STATE_PLAYING:
				save();
				updateRain();
				updateHealthGlobe();
				updateTreasure();
				updatePaper(deltaTime);
				collision();
				if(paper.getState() == PaperMan.STATE_GONE)
				{
					state = WORLD_STATE_OVER;
					Database.updateg3(assets.getProps().getProperty("Email"), assets.getProps().getProperty("game3MaxScore"));
					save();				
				}
				time += deltaTime;
				gameTime += deltaTime;
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
		updatedScore = currentGameScore + previousGameScore;
		assets.getProps().setProperty("game3Score", ""+updatedScore);
		
		//set new max score
		if( Float.parseFloat(assets.getProps().getProperty("game3MaxScore")) < currentGameScore)
		{
			assets.getProps().setProperty("game3MaxScore", ""+currentGameScore);
		}
		
		assets.save();
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

				if(Keyboard.getEventKey() == Keyboard.KEY_LEFT || Keyboard.getEventKey() == Keyboard.KEY_A)
				{
					if(state == WORLD_STATE_PLAYING)
					{
						paper.moveLeft(cam);
						
					}
				}
				
				if(Keyboard.getEventKey() == Keyboard.KEY_RIGHT || Keyboard.getEventKey() == Keyboard.KEY_D)
				{
					if(state == WORLD_STATE_PLAYING)
					{
						paper.moveRight(cam);
							
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
		renderHealthGlobe();
		renderTreasure();
	}
	
	private void renderPaper()
	{
		Rectangle rect = new Rectangle(paper.position.x -0.75f, paper.position.y -0.75f, 1.5f, 1.5f);
		
		switch(paper.getState())
		{
		case PaperMan.STATE_NORMAL:
			assets.getImage("paperMan").draw(rect);
			break;
			
		case PaperMan.STATE_HIT:
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
	
	private void renderHealthGlobe()
	{
		for(int i =0; i < healthGlobe.length; i++)
		{
			if(healthGlobe[i] != null)
			{
				Rectangle rect = new Rectangle(healthGlobe[i].position.x - .2f, healthGlobe[i].position.y - .4f, 1f, 1f);
				assets.getImage("healthGlobe").draw(rect);
			}
		}
		
	}
	
	private void renderTreasure()
	{
		for(int i =0; i < treasure.length; i++)
		{
			if(treasure[i] != null)
			{
				Rectangle rect = new Rectangle(treasure[i].position.x - .2f, treasure[i].position.y - .4f, 1f, 1f);
				assets.getImage("treasure").draw(rect);
			}
		}
	}
	
	public void renderHud()
	{
		if(paper != null)
		{
			currentGameScore = (int) time;
			
			TextDrawer.drawString("Health", cam.position.x - FRUSTUM_WIDTH/2 + 0.1f, cam.position.y + FRUSTUM_HEIGHT/2 - 0.70f, 0.2f, 0.6f);
			TextDrawer.drawString("Score  " + currentGameScore , cam.position.x - FRUSTUM_WIDTH/2 + 6.7f, cam.position.y + FRUSTUM_HEIGHT/2 - 0.7f, 0.15f, 0.6f);
			
			
			for(float i=0; i<paper.getCurrentHealth(); i++)		
			{
				Rectangle healthRect = new Rectangle(cam.position.x - FRUSTUM_WIDTH/2 + 1.5f + i/2, cam.position.y + FRUSTUM_HEIGHT/2 - 1.0f, 0.55f, 1.0f);
				assets.getImage("healthBar").draw(healthRect);
			}
			
			if((int)gameTime == 30 || (int)gameTime == 60){
				renderMoreDropletsText();
			}
		}
	}
	
	public void renderOver()
	{
		TextDrawer.drawString("Press q to quit", cam.position.x - FRUSTUM_WIDTH/2 + 0.5f, cam.position.y + FRUSTUM_HEIGHT/2 - 2.5f, TEXT_SIZE * 2, TEXT_SIZE * 2);	
	}
	
	public void renderPaused()
	{
		TextDrawer.drawString("Paused", cam.position.x-2.5f, cam.position.y, TEXT_SIZE * 3, TEXT_SIZE * 3);
		TextDrawer.drawString("Press q to quit", cam.position.x - FRUSTUM_WIDTH/2 + 0.5f, cam.position.y + FRUSTUM_HEIGHT/2 - 2.5f, TEXT_SIZE * 2, TEXT_SIZE * 2);
	}
	
	public void renderMoreDropletsText()
	{
		TextDrawer.drawString("More Droplets", cam.position.x - FRUSTUM_WIDTH/2 + 1, cam.position.y + FRUSTUM_HEIGHT/2 - 4, TEXT_SIZE * 2, TEXT_SIZE * 2);
	}
	
	public void collision()
	{
		collisionRain();
		collisionHealthGlobe();
		collisionTreasure();
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
					hitCounter += 1;
					time -= 2;
				}
			}
		}
		
		if( hitCounter >= 3)
		{
			rainHit.speedIncrease((float) 0.01);
			hitCounter = 0;
		}
	}

	public void collisionHealthGlobe()
	{
		for(int i = 0; i < healthGlobe.length; i++)
		{
			if(healthGlobe[i] != null)
			{
				if(CollisionChecker.RectToRect(paper.bounds, healthGlobe[i].bounds))
				{
					healthGlobe[i].position.set(rand.nextFloat() * (cam.position.x + FRUSTUM_WIDTH/2), cam.position.y + FRUSTUM_HEIGHT/2);
					healthGlobe[i].update();
					paper.hitHealthGlobe();
				}
			}
		}
	}
	
	public void collisionTreasure()
	{
		for(int i = 0; i < treasure.length; i++)
		{
			if(treasure[i] != null)
			{
				if(CollisionChecker.RectToRect(paper.bounds, treasure[i].bounds))
				{
					treasure[i].position.set(rand.nextFloat() * (cam.position.x + FRUSTUM_WIDTH/2), cam.position.y + FRUSTUM_HEIGHT/2);
					treasure[i].update();
					rainHit.speedDecrease((float) 0.01);
					
					if(paper.getCurrentHealth() >= 10 )
					{
						time = time + 2;	
					}
					
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
	public void resume() 
	{
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
