package com.groupc.game1;

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
import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
import com.groupc.math.CollisionChecker;
import com.groupc.math.Rectangle;

public class World extends GameScreen
{
	public static final float FRUSTUM_WIDTH = 8;
	public static final float FRUSTUM_HEIGHT = 6;
	public static final float WORLD_WIDTH = FRUSTUM_WIDTH * 700; //400 (the * 100 means the total width is 5000 pixels but only 400 shown)
	public static final float WORLD_HEIGHT = FRUSTUM_HEIGHT * 30; //400 (the * 20 means the total height is 200 pixels but only 400 shown)
	
	public static final int WORLD_STATE_PAUSED = 0;
	public static final int WORLD_STATE_PLAYING = 1;
	public static final int WORLD_STATE_OVER = 2;
	
	public static final float TEXT_SIZE = .2f;
	public static final int MAX_SCORE_DIGITS = 8; //99999999 max score
	public static final int MAX_SEED_DIGITS = 4; //9999 max seeds
	public static final int MAX_DISTANCE = 4; //9999 max distance
	
	public static final int GRAVITY = -10;
	
	public int state;
	private int seedsCollected;
	private int score;
	private float distance;
	
	private Camera cam;
	public final JoeyRooster joey;
	public final Ramp ramp;
	public final Seed[] seeds;
	public final Cow cow;
	public final HayStack hay;
	private Random rand;
	
	public World()
	{        
		Assets.reload();
		rand = new Random();
		this.joey = new JoeyRooster(1, .5f, Float.parseFloat(Assets.getProps().getProperty("speedMult")), Integer.parseInt(Assets.getProps().getProperty("statima")));
		this.ramp = new Ramp(21, 0.5f, 5);
		this.seeds = new Seed[5];
		seeds[0] = new Seed(25, 5);
		seeds[1] = new Seed(25, 6);
		seeds[2] = new Seed(25, 7);
		seeds[3] = new Seed(25, 8);
		seeds[4] = new Seed(25, 9);
		this.cow = new Cow(30, 0.5f);
		this.state = WORLD_STATE_PLAYING;
		cam = new Camera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		score = Integer.parseInt(Assets.getProps().getProperty("score"));
		seedsCollected = Integer.parseInt(Assets.getProps().getProperty("seeds"));
		this.hay = new HayStack(25, 0.5f);
	}
	
	public void updateSeeds()
	{
		for(int i = 0; i < seeds.length; i++)
		{
			if(seeds[i].position.x < cam.position.x - FRUSTUM_WIDTH /2)
			{
				seeds[i].position.set(rand.nextFloat() + cam.position.x + FRUSTUM_WIDTH/2, rand.nextFloat() * (rand.nextInt(10) - 5) + cam.position.y);
				seeds[i].update();
			}
		}		
	}
	
	public void updateCow(float deltaTime)
	{
		if(cow.position.x < cam.position.x - FRUSTUM_WIDTH /2)
		{
			int chance = rand.nextInt(10);
			if(chance < 2)
			{
				cow.position.set(rand.nextFloat() * 5 + cam.position.x + FRUSTUM_WIDTH/2, 0.5f);
				cow.state = Cow.STATE_COW;
			}
		}
		cow.update(deltaTime);
	}
	
	public void updateHayStack()
	{
		if(hay.position.x < cam.position.x - FRUSTUM_WIDTH /2)
		{
			int chance = rand.nextInt(5);
			if(chance < 3)
			{
				hay.setHit(false);
				hay.position.set(rand.nextFloat() * 10 + cam.position.x + FRUSTUM_WIDTH/2, 0.5f);
				hay.update();
				if(CollisionChecker.RectToRect(hay.bounds, cow.bounds))
				{
					hay.position.set(rand.nextFloat() * 10 + cam.position.x + FRUSTUM_WIDTH/2, 0.5f);
					hay.update();
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
				updateSeeds();
				updateCow(deltaTime);
				updateHayStack();
				updateJoey(deltaTime);
				collision();
				if(joey.getState() == JoeyRooster.STATE_STOP)
				{
					state = WORLD_STATE_OVER;
					distance = joey.position.x - ramp.position.x;
					score += distance/10;
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
		Assets.getProps().setProperty("score", ""+score);
		
		//set new max distance
		if( Float.parseFloat(Assets.getProps().getProperty("maxDistance")) < distance)
		{
			Assets.getProps().setProperty("maxDistance", ""+distance);
		}
						
		//add the newly gained seeds]
		Assets.getProps().setProperty("seeds", ""+seedsCollected);
		Assets.save();
	}
	
	public void inputHandling()
	{
		while (Keyboard.next()) 
		{
			if(Keyboard.getEventKeyState())
			{
				if(Keyboard.getEventKey() == Keyboard.KEY_S || Keyboard.getEventKey() == Keyboard.KEY_DOWN)
				{
					if(state == WORLD_STATE_PLAYING)
					{
						joey.toggleGlide();
					}
				}
			}
			else
			{
				if(Keyboard.getEventKey() == Keyboard.KEY_W || Keyboard.getEventKey() == Keyboard.KEY_UP)
				{
					joey.flap();
				}
				
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
				
				if(Keyboard.getEventKey() == Keyboard.KEY_S || Keyboard.getEventKey() == Keyboard.KEY_DOWN)
				{
					if(state == WORLD_STATE_PLAYING)
		        	{
						joey.toggleGlide();
		        	}
				}
			}
		}
	}
	
	public void updateJoey(float deltaTime)
	{
		if(joey.getState() == JoeyRooster.STATE_RAMP && joey.position.x > ramp.position.x + ramp.bounds.width)
		{
			joey.setState(JoeyRooster.STATE_FLYING);
		}
		
		joey.update(deltaTime);
	}
	
	public void renderPlaying()
	{	
		if(joey.position.x > cam.position.x)
		{
			if(cam.position.x  < WORLD_WIDTH - 10)
			{
				cam.position.x = joey.position.x;
			}
			if(joey.position.y > cam.position.y || joey.position.y < cam.position.y)
			{
				if(cam.position.y  < WORLD_HEIGHT - 5)
				{
					if(joey.position.y >= FRUSTUM_HEIGHT / 2)
					{
						cam.position.y = joey.position.y;
					}
				}
			}
		}
		cam.setCamera();
		
		Assets.getTexture("atlas").bind();
		Assets.getImage("sky").draw(new Rectangle(0, 3, WORLD_WIDTH, WORLD_HEIGHT));
		Assets.getImage("grass").draw(new Rectangle(0, 0, WORLD_WIDTH, 3));
		renderHud();	
		renderRamp();
		renderSeeds();
		renderCow();
		renderHay();
		renderJoey();
	}
	
	public void renderHay()
	{
		Rectangle rect;
		if(hay.getHit())
		{
			rect = new Rectangle(hay.position.x - .5f, hay.position.y -.5f, 2, 1);
			Assets.getImage("hayHit").draw(rect);
		}
		else
		{
			rect = new Rectangle(hay.position.x - .5f, hay.position.y -.5f, 1, 1);
			Assets.getImage("hay").draw(rect);
		}
	}
	
	public void renderHud()
	{
		//hud
		Assets.getImage("joeyFly").draw(new Rectangle(0, cam.position.y + FRUSTUM_HEIGHT/2 - .35f, WORLD_WIDTH, .35f));
		TextDrawer.drawString("score", cam.position.x - FRUSTUM_WIDTH/2, cam.position.y + FRUSTUM_HEIGHT/2 - TEXT_SIZE, TEXT_SIZE, TEXT_SIZE);
		TextDrawer.drawInt(score, cam.position.x - 3.2f, cam.position.y + FRUSTUM_HEIGHT/2 - TEXT_SIZE, TEXT_SIZE, TEXT_SIZE, MAX_SCORE_DIGITS);
	
		TextDrawer.drawString("seeds", cam.position.x, cam.position.y + FRUSTUM_HEIGHT/2 - TEXT_SIZE, TEXT_SIZE, TEXT_SIZE);
		TextDrawer.drawInt(seedsCollected, cam.position.x +1f, cam.position.y + FRUSTUM_HEIGHT/2 - TEXT_SIZE, TEXT_SIZE, TEXT_SIZE, MAX_SEED_DIGITS);
		
	}
	
	public void renderJoey()
	{
		Rectangle rect = new Rectangle(joey.position.x - .5f, joey.position.y -.5f, 1, 1);
	
		switch(joey.getState())
		{
		case JoeyRooster.STATE_SB:
			Assets.getImage("joeySk").draw(rect);
			break;
		case JoeyRooster.STATE_STOP:
			Assets.getImage("joeySk").draw(rect);
			break;
		case JoeyRooster.STATE_FLYING:
			Assets.getImage("joeyFly").draw(rect);
			break;
		case JoeyRooster.STATE_FALLING:
			Assets.getImage("joeyFall").draw(rect);
			break;
		case JoeyRooster.STATE_BOUNCE:
			Assets.getImage("joeyBou").draw(rect);
			break;
		case JoeyRooster.STATE_RAMP:
			Assets.getImage("joeyBou").draw(rect);
			break;
		case JoeyRooster.STATE_FLAP:
			Assets.getImage("joeyFlap").draw(rect);
			break;
		case JoeyRooster.STATE_GLIDE:
			Assets.getImage("joeyGlide").draw(rect);
			break;
		}
	}
	
	public void renderRamp()
	{
		Rectangle rect = new Rectangle(ramp.position.x - 2.5f, ramp.position.y - 2.5f, ramp.bounds.width, ramp.bounds.height);
		Assets.getImage("ramp").draw(rect);
	}
	
	public void renderSeeds()
	{
		for(int i =0; i < seeds.length; i++)
		{
			Rectangle rect = new Rectangle(seeds[i].position.x - .1f, seeds[i].position.y - .2f, .2f, .4f);
			Assets.getImage("seed").draw(rect);
		}
	}
	
	public void renderCow()
	{		
		switch(cow.state)
		{
		case Cow.STATE_COW:
			Rectangle rect = new Rectangle(cow.position.x - 1f, cow.position.y - .5f, 2, 1);
			Assets.getImage("cow").draw(rect);
			break;
		case Cow.STATE_HIT:
			rect = new Rectangle(cow.position.x -1f, cow.position.y - 1f, 2, 2);
			Assets.getImage("cowHit").draw(rect);
			break;
		}
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
	
	public void collision()
	{
		collisionRamp();
		collisionSeed();
		collisionCow();
		collisionHay();
	}
	
	public void collisionHay()
	{
		if(CollisionChecker.RectToRect(joey.bounds, hay.bounds) && !(hay.getHit()))
		{
			joey.hitHay(hay);
			hay.setHit(true);
		}
	}
	
	public void collisionRamp()
	{
		if(CollisionChecker.RectToRect(joey.bounds, ramp.bounds))
		{
			joey.hitRamp();
			
		}
	}
	
	public void collisionCow()
	{
		if(CollisionChecker.RectToRect(joey.bounds, cow.bounds) && joey.getState() != JoeyRooster.STATE_GLIDE)
		{
			joey.hitCow(cow);	
			cow.position.set(cow.position.x, 1f);
			cow.hit();
		}
	}
	
	public void collisionSeed()
	{
		for(int i = 0; i < seeds.length; i++)
		{
			if(CollisionChecker.RectToRect(joey.bounds, seeds[i].bounds))
			{
				seeds[i].position.set(rand.nextFloat()* 5 + cam.position.x + FRUSTUM_WIDTH/2, rand.nextFloat()* - 3 + joey.position.y);
				seeds[i].update();
				seedsCollected+=1;
				score += 10;
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
		return new MainMenu();
	}
}
