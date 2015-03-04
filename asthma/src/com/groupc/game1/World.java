package com.groupc.game1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
import com.groupc.math.CollisionChecker;
import com.groupc.math.Rectangle;

public class World extends GameScreen
{
	public static final float FRUSTUM_WIDTH = 10;
	public static final float FRUSTUM_HEIGHT = 10;
	public static final float WORLD_WIDTH = FRUSTUM_WIDTH * 100; //400
	public static final float WORLD_HEIGHT = FRUSTUM_HEIGHT; //400 (the * 10 means the total width is 4000 pixels but only 400 shown)
	
	public static final int WORLD_STATE_PAUSED = 0;
	public static final int WORLD_STATE_PLAYING = 1;
	public static final int WORLD_STATE_OVER = 2;
	
	public static final int GRAVITY = -10;
	
	public int state;
	public int score;
	
	public Camera cam;
	public final JoeyRooster joey;
	public final Ramp ramp;
	public final Seed[] seeds;
	public Random rand;
	
	public World()
	{
		rand = new Random();
		this.joey = new JoeyRooster(1, 0, 1);
		this.ramp = new Ramp(21, 0);
		this.seeds = new Seed[5];
		seeds[0] = new Seed(25, 5);
		seeds[1] = new Seed(25, 6);
		seeds[2] = new Seed(25, 7);
		seeds[3] = new Seed(25, 8);
		seeds[4] = new Seed(25, 9);
		this.state = WORLD_STATE_PAUSED;
		cam = new Camera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
	}
	
	public void updateSeeds()
	{
		for(int i = 0; i < seeds.length; i++)
		{
			if(seeds[i].position.x < cam.position.x - FRUSTUM_WIDTH /2)
			{
				seeds[i].position.set(rand.nextFloat()* 5 + cam.position.x + FRUSTUM_WIDTH/2, rand.nextFloat()* - 3 + joey.position.y);
				seeds[i].update();
			}
		}
		
	}
	
	public void update(float deltaTime)
	{
		updateSeeds();
		updateJoey(deltaTime);
		collision();
	}
	
	public void updateJoey(float deltaTime)
	{
		joey.update(deltaTime);
	}
	
	public void render()
	{
		
		if(joey.position.x > cam.position.x)
		{
			if(cam.position.x  < WORLD_WIDTH - 20)
			{
				cam.position.x = joey.position.x;
			}
		}
		cam.setCamera();
		renderJoey();
		renderRamp();
		renderSeeds();
		
	}
	
	public void renderJoey()
	{
		Rectangle rect = new Rectangle(joey.position.x, joey.position.y, 1, 1);
	
		switch(joey.state)
		{
		case JoeyRooster.STATE_MOVE:
		case JoeyRooster.STATE_STOP:
			Assets.joey_SK.draw(rect);
			break;
		case JoeyRooster.STATE_FLYING:
		case JoeyRooster.STATE_FALLING:
		case JoeyRooster.STATE_BOUNCE:
			Assets.joey_fly.draw(rect);
			break;
		}
	}
	
	public void renderRamp()
	{
		Rectangle rect = new Rectangle(ramp.position.x, ramp.position.y, 2, 2);
		Assets.ramp.draw(rect);
	}
	
	public void renderSeeds()
	{
		for(int i =0; i < seeds.length; i++)
		{
			Rectangle rect = new Rectangle(seeds[i].position.x, seeds[i].position.y, .2f, .4f);
			Assets.ball.draw(rect);
		}
	}
	
	public void collision()
	{
		collisionRamp();
		collisionSeed();
	}
	
	public void collisionRamp()
	{
		if(CollisionChecker.RectToRect(joey.bounds, ramp.bounds))
		{
			joey.hitRamp();
			
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
				score+=1;
			}
		}
	}

	@Override
	public void present(float deltaTime) 
	{
		render();
		System.out.println(score);
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
