package com.groupc.game1;

import java.util.Random;

import org.lwjgl.input.Keyboard;

import com.groupc.Database;
import com.groupc.TextDrawer;
import com.groupc.game.Asset;
import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
import com.groupc.math.CollisionChecker;
import com.groupc.math.Rectangle;

public class World extends GameScreen
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
	public final JoeyRooster joey;
	public final Ramp ramp;
	public final Seed[] seeds;
	public final Cow cow;
	public final HayStack hay;
	private Random rand;
	
	public World(Asset assets)
	{        
		super(assets);
		assets.reload();
		rand = new Random();
		this.joey = new JoeyRooster(1, .75f, Float.parseFloat(assets.getProps().getProperty("joeyspeedMult")), Integer.parseInt(assets.getProps().getProperty("joeystatima")));
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
		score = Integer.parseInt(assets.getProps().getProperty("joeyscore"));
		seedsCollected = Integer.parseInt(assets.getProps().getProperty("joeyseeds"));
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
				Database.updateg1(assets.getProps().getProperty("Email"), assets.getProps().getProperty("joeymaxDistance"));
			case WORLD_STATE_PAUSED:
				break;
		}
	}
	
	/**
	 * Prepares the property file to store all the relevent player data at moment of method called.
	 */
	private void save()
	{
		assets.getProps().setProperty("joeyscore", ""+score);
		
		//set new max distance
		if( Float.parseFloat(assets.getProps().getProperty("joeymaxDistance")) < distance)
		{
			assets.getProps().setProperty("joeymaxDistance", ""+distance);
		}
						
		//add the newly gained seeds]
		assets.getProps().setProperty("joeyseeds", ""+seedsCollected);
		assets.save();
	}
	
	public void inputHandling()
	{
		while (Keyboard.next()) 
		{
			if(Keyboard.getEventKeyState())
			{
				if(Keyboard.getEventKey() == Keyboard.KEY_S || Keyboard.getEventKey() == Keyboard.KEY_DOWN)
				{
					if(state == WORLD_STATE_PLAYING && joey.getState() != JoeyRooster.STATE_SB)
					{
						joey.toggleGlide();
					}
				}
			}
			else
			{
				if(Keyboard.getEventKey() == Keyboard.KEY_W || Keyboard.getEventKey() == Keyboard.KEY_UP)
				{
					if(state == WORLD_STATE_PLAYING)
					{
						joey.flap();
					}
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
					if(state == WORLD_STATE_PLAYING && joey.getState() != JoeyRooster.STATE_SB)
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
		
		assets.getTexture().bind();
		assets.getImage("sky").draw(new Rectangle(0, 3, WORLD_WIDTH, WORLD_HEIGHT));
		assets.getImage("grass").draw(new Rectangle(0, 0, WORLD_WIDTH, 3));
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
			assets.getImage("hayHit").draw(rect);
		}
		else
		{
			rect = new Rectangle(hay.position.x - .5f, hay.position.y -.5f, 1, 1);
			assets.getImage("hay").draw(rect);
		}
	}
	
	public void renderHud()
	{
		//hud
		assets.getImage("joeyFly").draw(new Rectangle(0, cam.position.y + FRUSTUM_HEIGHT/2 - .35f, WORLD_WIDTH, .35f));
		
		TextDrawer.drawStringinRect("score", new Rectangle(cam.position.x - FRUSTUM_WIDTH/2, cam.position.y + FRUSTUM_HEIGHT/2 - TEXT_SIZE, TEXT_SIZE * 5, TEXT_SIZE), false);
		TextDrawer.drawStringinRect(score +"", new Rectangle(cam.position.x - 3f, cam.position.y + FRUSTUM_HEIGHT/2 - TEXT_SIZE, MAX_SCORE_DIGITS * TEXT_SIZE, TEXT_SIZE), false);
		TextDrawer.drawString("seeds", cam.position.x, cam.position.y + FRUSTUM_HEIGHT/2 - TEXT_SIZE, TEXT_SIZE, TEXT_SIZE);
		TextDrawer.drawInt(seedsCollected, cam.position.x +1f, cam.position.y + FRUSTUM_HEIGHT/2 - TEXT_SIZE, TEXT_SIZE, TEXT_SIZE, MAX_SEED_DIGITS);
		
	}
	
	public void renderJoey()
	{
		Rectangle rect = new Rectangle(joey.position.x - .5f, joey.position.y -.5f, 1.5f, 1.5f);
	
		switch(joey.getState())
		{
		case JoeyRooster.STATE_SB:
			assets.getImage("joeySk").draw(rect);
			break;
		case JoeyRooster.STATE_STOP:
			assets.getImage("joeySk").draw(rect);
			break;
		case JoeyRooster.STATE_FLYING:
			assets.getImage("joeyFly").draw(rect);
			break;
		case JoeyRooster.STATE_FALLING:
			assets.getImage("joeyFall").draw(rect);
			break;
		case JoeyRooster.STATE_BOUNCE:
			assets.getImage("joeyBou").draw(rect);
			break;
		case JoeyRooster.STATE_RAMP:
			assets.getImage("joeyBou").draw(rect);
			break;
		case JoeyRooster.STATE_FLAP:
			assets.getImage("joeyFlap").draw(rect);
			break;
		case JoeyRooster.STATE_GLIDE:
			assets.getImage("joeyGlide").draw(rect);
			break;
		}
	}
	
	public void renderRamp()
	{
		Rectangle rect = new Rectangle(ramp.position.x - 2.5f, ramp.position.y - 2.5f, ramp.bounds.width, ramp.bounds.height);
		assets.getImage("ramp").draw(rect);
	}
	
	public void renderSeeds()
	{
		for(int i =0; i < seeds.length; i++)
		{
			Rectangle rect = new Rectangle(seeds[i].position.x - .2f, seeds[i].position.y - .4f, .4f, .8f);
			assets.getImage("seed").draw(rect);
		}
	}
	
	public void renderCow()
	{		
		switch(cow.state)
		{
		case Cow.STATE_COW:
			Rectangle rect = new Rectangle(cow.position.x - 1f, cow.position.y - .5f, 2, 1);
			assets.getImage("cow").draw(rect);
			break;
		case Cow.STATE_HIT:
			rect = new Rectangle(cow.position.x -1f, cow.position.y - 1f, 2, 2);
			assets.getImage("cowHit").draw(rect);
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
		return new MainMenu(assets);
	}
}
