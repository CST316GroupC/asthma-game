package com.groupc.game1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
import com.groupc.math.CollisionChecker;
import com.groupc.math.Rectangle;

public class World extends GameScreen
{
	public static final float FRUSTUM_WIDTH = 10;
	public static final float FRUSTUM_HEIGHT = 10;
	public static final float WORLD_WIDTH = FRUSTUM_WIDTH * 100; //400
	public static final float WORLD_HEIGHT = FRUSTUM_HEIGHT * 20; //400 (the * 10 means the total width is 4000 pixels but only 400 shown)
	
	public static final int WORLD_STATE_PAUSED = 0;
	public static final int WORLD_STATE_PLAYING = 1;
	public static final int WORLD_STATE_OVER = 2;
	
	public static final int GRAVITY = -10;
	public int state;
	public int seedsCollected;
	
	public Camera cam;
	public final JoeyRooster joey;
	public final Ramp ramp;
	public final Seed[] seeds;
	public final Cow cow;
	public Random rand;
	
	public World()
	{
		rand = new Random();
		this.joey = new JoeyRooster(1, .5f, Float.parseFloat(Assets.joeyProps.getProperty("speedMult")), Integer.parseInt(Assets.joeyProps.getProperty("statima")));
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
	
	public void updatecow()
	{
		if(cow.position.x < cam.position.x - FRUSTUM_WIDTH /2)
		{
			int chance = rand.nextInt(10);
			if(chance < 2)
			{
				cow.position.set(rand.nextFloat() * 5 + cam.position.x + FRUSTUM_WIDTH/2, 0.5f);
				cow.update();
			}
		}
	}
	
	
	public void update(float deltaTime)
	{
		switch(state)
		{
		case WORLD_STATE_PLAYING:
			inputHandling();
			updateSeeds();
			updatecow();
			updateJoey(deltaTime);
			collision();
			if(joey.state == JoeyRooster.STATE_STOP)
			{
				state = WORLD_STATE_OVER;
				
				//increase the score
				int temp = Integer.parseInt(Assets.joeyProps.getProperty("score"));
				temp += seedsCollected;
				float distance = joey.position.x - ramp.position.x;
				temp += distance/10;
				Assets.joeyProps.setProperty("score", ""+temp);
				
				//set new max distance
				if( Float.parseFloat(Assets.joeyProps.getProperty("maxDistance")) < distance)
				{
					Assets.joeyProps.setProperty("maxDistance", ""+distance);
				}
								
				//add the newly gained seeds
				temp = Integer.parseInt(Assets.joeyProps.getProperty("seeds")) + seedsCollected;
				Assets.joeyProps.setProperty("seeds", ""+temp);
				Assets.save();
			}
			break;
		case WORLD_STATE_OVER:
			break;
		}
	}
	
	public void inputHandling()
	{
		while (Keyboard.next()) {
		    if (Keyboard.getEventKeyState()) {
		        if (Keyboard.getEventKey() == Keyboard.KEY_W) {
		        System.out.println("W Key Pressed");
		        
		        }
		    }
		    else {
		        if (Keyboard.getEventKey() == Keyboard.KEY_W) {
		        System.out.println("W Key Released");
		        joey.flap();
		        }
		    }
		}
	}
	
	public void updateJoey(float deltaTime)
	{
		if(joey.state == JoeyRooster.STATE_RAMP && joey.position.x > ramp.position.x + ramp.bounds.width)
		{
			joey.state = JoeyRooster.STATE_FLYING;
		}
		
		joey.update(deltaTime);
	}
	
	public void render()
	{
		Assets.sky.draw(new Rectangle(0, 3, WORLD_WIDTH, WORLD_HEIGHT));
		Assets.grass.draw(new Rectangle(0, 0, WORLD_WIDTH, 3));
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
		renderRamp();
		renderSeeds();
		rendercow();
		renderJoey();
	}
	
	public void renderJoey()
	{
		Rectangle rect = new Rectangle(joey.position.x - .5f, joey.position.y -.5f, 1, 1);
	
		switch(joey.state)
		{
		case JoeyRooster.STATE_SB:
			Assets.joeysk.draw(rect);
			break;
		case JoeyRooster.STATE_STOP:
			Assets.joeysk.draw(rect);
			break;
		case JoeyRooster.STATE_FLYING:
			Assets.joeyfly.draw(rect);
			break;
		case JoeyRooster.STATE_FALLING:
			Assets.joeyfall.draw(rect);
			break;
		case JoeyRooster.STATE_BOUNCE:
			Assets.joeyBou.draw(rect);
			break;
		case JoeyRooster.STATE_RAMP:
			Assets.joeyRamp.draw(rect);
			break;
		case JoeyRooster.STATE_FLAP:
			Assets.joeyflap.draw(rect);
			break;
		}
	}
	
	public void renderRamp()
	{
		Rectangle rect = new Rectangle(ramp.position.x - 2.5f, ramp.position.y - 2.5f, ramp.bounds.width, ramp.bounds.height);
		Assets.ramp.draw(rect);
	}
	
	public void renderSeeds()
	{
		for(int i =0; i < seeds.length; i++)
		{
			Rectangle rect = new Rectangle(seeds[i].position.x - .1f, seeds[i].position.y - .2f, .2f, .4f);
			Assets.seed1.draw(rect);
		}
	}
	
	public void rendercow()
	{		
		switch(cow.state)
		{
		case Cow.STATE_COW:
			Rectangle rect = new Rectangle(cow.position.x - 1f, cow.position.y - .5f, 2, 1);
			Assets.cow.draw(rect);
			break;
		case Cow.STATE_HIT:
			rect = new Rectangle(cow.position.x -1f, cow.position.y - 1f, 2, 2);
			Assets.cowhit.draw(rect);
			break;
		}
	}
	
	public void collision()
	{
		collisionRamp();
		collisionSeed();
		collisionCow();
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
		if(CollisionChecker.RectToRect(joey.bounds, cow.bounds))
		{
			joey.hitCow();	
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
			}
		}
	}

	@Override
	public void present(float deltaTime) 
	{
		render();
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

	@Override
	public GameScreen getNext() {
		// TODO Auto-generated method stub
		return null;
	}
}
