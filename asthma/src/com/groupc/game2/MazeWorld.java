package com.groupc.game2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import com.groupc.TextDrawer;
import com.groupc.game.Asset;
import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
import com.groupc.game1.JoeyRooster;
import com.groupc.math.CollisionChecker;
import com.groupc.math.Rectangle;
import com.groupc.math.Vector;

public class MazeWorld extends GameScreen
{
	public static final float FRUSTUM_WIDTH = 15;
	public static final float FRUSTUM_HEIGHT = 15;
	public static final float WORLD_WIDTH = FRUSTUM_WIDTH * 1; //400 (the * 100 means the total width is 5000 pixels but only 400 shown)
	public static final float WORLD_HEIGHT = FRUSTUM_HEIGHT *1; //400 (the * 20 means the total height is 200 pixels but only 400 shown)
	
	public static final int WORLD_STATE_PAUSED = 0;
	public static final int WORLD_STATE_PLAYING = 1;
	public static final int WORLD_STATE_OVER = 2;
	
	private final Camera cam;
	public final Player player;
	public final ArrayList<Wall> walls;
	public final ArrayList<Pit> pits;
	public final ArrayList<Enemy> enemies;
	public final ArrayList<Gem> gems;
	public final ArrayList<Spike> spikes;
	public final ArrayList<Dirt> dirts;
	public PlayerDig playerDig;
	public final Goal goal;
	private int buttonPresses;
	private int level;
	private int state;
	private int score;
	
	public MazeWorld(Asset asset) 
	{
		super(asset);
		state = WORLD_STATE_PLAYING;
		cam = new Camera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		player = new Player(0, 0); //place holders
		buttonPresses = 0;
		setLevel(Integer.parseInt(assets.getProps().getProperty("mazeLevel")));
		walls = new ArrayList<Wall>();
		pits = new ArrayList<Pit>();
		goal = new Goal(0, 0); //place holder
		enemies = new ArrayList<Enemy>();
		gems = new ArrayList<Gem>();
		dirts = new ArrayList<Dirt>();
		spikes = new ArrayList<Spike>();
		playerDig = new PlayerDig(-1,0); //placeHolder
		generateLevel(getLevel());
		score = Integer.parseInt(assets.getProps().getProperty("mazeScore"));
	}
	
	public void generateLevel(int level)
	{
		FileReader rd = null;
		BufferedReader brd = null;
		try {
			char sym;
			rd = new FileReader("resources/game2/levels.txt");
			brd = new BufferedReader(rd);
			String[] rows = new String[15]; //Each level is at most 15 rows
			//Get to point in file that contains the level
			for(int i =0; i < ((level) * 15); i++)
			{
				brd.readLine();
			}
			
			//Read the next 15 lines
			for(int i =0; i < 15; i++)
			{
				rows[i] = brd.readLine();
			}
			
			//Generate level row at a time
			for(int i =0; i < 15; i++)
			{
				for(int j =0; j < 15; j++)
				{
					sym = rows[i].charAt(j);
					if(sym == 'W') //Walls
					{
						walls.add(new Wall(j+Wall.WIDTH/2, WORLD_HEIGHT-i-Wall.HEIGHT/2));
					}
					else if(sym == 'S') //Player start
					{
						player.position.set(j+Player.WIDTH/2, WORLD_HEIGHT-i-Player.HEIGHT/2);
						player.bounds.lowerLeft.set(player.position.sub(player.bounds.width / 2, player.bounds.height / 2));
					}
					else if(sym == 'P') //Pits
					{
						pits.add(new Pit(j+Wall.WIDTH/2, WORLD_HEIGHT-i-Wall.HEIGHT/2)); //Use the same as wall because drawn the same as a wall but bounds are different
					}
					else if(sym == 'E') //Goal or end of level
					{
						goal.position.set(j+Wall.WIDTH/2, WORLD_HEIGHT-i-Wall.HEIGHT/2);
						goal.bounds.lowerLeft.set(goal.position.sub(goal.bounds.width / 2, goal.bounds.height / 2));
						
					}
					else if(sym == 'G') //Goal or end of level
					{
						gems.add(new Gem(j+Wall.WIDTH/2, WORLD_HEIGHT-i-Wall.HEIGHT/2));//Use the same as wall because drawn the same as a wall but bounds are different))
					}
					else if(sym == 'A') //Goal or end of level
					{
						spikes.add(new Spike(j+Wall.WIDTH/2, WORLD_HEIGHT-i-Wall.HEIGHT/2));//Use the same as wall because drawn the same as a wall but bounds are different))
					}
					else if(sym == 'D') //Goal or end of level
					{
						dirts.add(new Dirt(j+Wall.WIDTH/2, WORLD_HEIGHT-i-Wall.HEIGHT/2));//Use the same as wall because drawn the same as a wall but bounds are different))
					}
					else if(sym == '1') //Goal or end of level
					{
						enemies.add(new LREnemy(j+Wall.WIDTH/2, WORLD_HEIGHT-i-Wall.HEIGHT/2));//Use the same as wall because drawn the same as a wall but bounds are different
					}
					else if(sym == '2') //Goal or end of level
					{
						enemies.add(new UDEnemy(j+Wall.WIDTH/2, WORLD_HEIGHT-i-Wall.HEIGHT/2));//Use the same as wall because drawn the same as a wall but bounds are different
					}
					
				}
			}
			
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}
		finally
		{			
			try
			{
				rd.close();
				brd.close();
			} catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(float deltaTime) 
	{
		inputHandling();
		if(state==WORLD_STATE_PLAYING)
		{
			player.update(deltaTime);
			playerDig.update(deltaTime);
			updateEnemies(deltaTime);
			updateSpikes(deltaTime);
			collisionChecker();
		}		
	}
	
	public void updateSpikes(float deltaTime)
	{
		for(int i=0; i < spikes.size(); i++)
		{
			spikes.get(i).update(deltaTime);
		}
	}
	
	public void updateEnemies(float deltaTime)
	{
		for(int i=0; i < enemies.size(); i++)
		{
			enemies.get(i).update(deltaTime);
		}
	}
	
	public void collisionChecker()
	{
		collisionWall();
		collisionPit();
		collisionGoal();
		collisionEnemy();
		collisionSpike();
		collisionGem();
		collisionDirt();
	}
	
	public void collisionDirt()
	{
		for(int i = 0; i < dirts.size(); i++)
		{
			if(dirts.get(i).isActive())
			{
				if(CollisionChecker.RectToRect(player.bounds, dirts.get(i).bounds))
				{
					player.hitWall(dirts.get(i));
				}
				if(CollisionChecker.RectToRect(playerDig.bounds, dirts.get(i).bounds))
				{
					if(dirts.get(i).getHasGem())
					{
						gems.add(new Gem(dirts.get(i).position.x-.5f, dirts.get(i).position.y-.5f));
					}
					dirts.get(i).setActive(false);
				}
			}
		}
	}
	
	public void collisionSpike()
	{
		for(int i=0; i < spikes.size(); i++)
		{
			if(CollisionChecker.RectToRect(player.bounds, spikes.get(i).bounds) && spikes.get(i).getState() == Spike.STATE_UP)
			{
				state = WORLD_STATE_OVER;
			}
		}
	}
	
	public void collisionGem()
	{
		for(int i=0; i < gems.size(); i++)
		{
			if(CollisionChecker.RectToRect(player.bounds, gems.get(i).bounds) && gems.get(i).isActive())
			{
				score += 10;
				gems.get(i).setActive(false);
			}
		}
	}
	
	public void collisionEnemy()
	{
		for(int i=0; i < enemies.size(); i++)
		{
			if(CollisionChecker.RectToRect(player.bounds, enemies.get(i).bounds))
			{
				state = WORLD_STATE_OVER;
			}
			for(int j=0; j < walls.size(); j++)
			{
				if(CollisionChecker.RectToRect(enemies.get(i).bounds, walls.get(j).bounds))
				{
					enemies.get(i).collision(walls.get(j));
				}
			}
			for(int k=0; k < pits.size(); k++)
			{
				if(CollisionChecker.RectToRect(enemies.get(i).bounds, pits.get(k).bounds))
				{
					enemies.get(i).collision(pits.get(k));
				}
			}
			for(int l=0; l < spikes.size(); l++)
			{
				if(CollisionChecker.RectToRect(enemies.get(i).bounds, spikes.get(l).bounds))
				{
					enemies.get(i).collision(spikes.get(l));
				}
			}
			for(int m=0; m < dirts.size(); m++)
			{
				if(CollisionChecker.RectToRect(enemies.get(i).bounds, dirts.get(m).bounds) && dirts.get(m).isActive())
				{
					enemies.get(i).collision(dirts.get(m));
				}
			}
		}
	}
	
	public void collisionGoal()
	{
		if(CollisionChecker.RectToRect(player.bounds, goal.bounds))
		{
			levelDone();
		}
	}
	
	public void collisionWall()
	{
		for(int i = 0; i < walls.size(); i++)
		{
			if(CollisionChecker.RectToRect(player.bounds, walls.get(i).bounds))
			{
				player.hitWall(walls.get(i));
			}
		}
	}
	
	public void collisionPit()
	{
		for(int i = 0; i < pits.size(); i++)
		{
			if(CollisionChecker.RectToRect(player.bounds, pits.get(i).bounds))
			{
				player.hitPit(pits.get(i));
			}
		}
	}
	
	public void inputHandling()
	{
		while (Keyboard.next()) 
		{
			if(Keyboard.getEventKeyState()) //pushed down
			{
				if(Keyboard.getEventKey() == Keyboard.KEY_W || Keyboard.getEventKey() == Keyboard.KEY_UP)
				{
					player.setDirection(new Vector(0, 10));
					player.setState(Player.STATE_MOVING);
					buttonPresses++;
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_S || Keyboard.getEventKey() == Keyboard.KEY_DOWN)
				{
					player.setDirection(new Vector(0, -10));
					player.setState(Player.STATE_MOVING);
					buttonPresses++;
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_D || Keyboard.getEventKey() == Keyboard.KEY_RIGHT)
				{
					player.setDirection(new Vector(10, 0));
					player.setState(Player.STATE_MOVING);
					buttonPresses++;
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_A || Keyboard.getEventKey() == Keyboard.KEY_LEFT)
				{
					player.setDirection(new Vector(-10, 0));
					player.setState(Player.STATE_MOVING);
					buttonPresses++;
				}
			}
			else //released
			{
				if(Keyboard.getEventKey() == Keyboard.KEY_W || Keyboard.getEventKey() == Keyboard.KEY_UP
						|| Keyboard.getEventKey() == Keyboard.KEY_S || Keyboard.getEventKey() == Keyboard.KEY_DOWN
						|| Keyboard.getEventKey() == Keyboard.KEY_A || Keyboard.getEventKey() == Keyboard.KEY_LEFT 
						|| Keyboard.getEventKey() == Keyboard.KEY_D || Keyboard.getEventKey() == Keyboard.KEY_RIGHT)
				{
					buttonPresses--;
					if(buttonPresses <= 0)
					{
						player.setState(Player.STATE_STILL);
						player.setDirection(new Vector(0, 0));
					}
				}
				if(state == WORLD_STATE_PLAYING)
				{
					if(Keyboard.getEventKey() == Keyboard.KEY_SPACE)
					{
						player.align();
					}
					if(Keyboard.getEventKey() == Keyboard.KEY_V && player.getState() == Player.STATE_STILL)
					{
						playerDig = player.dig();
					}
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_P)
				{
					if(state == WORLD_STATE_PLAYING)
					{
						pause();
					}
					else if(state == WORLD_STATE_PAUSED)
					{
						resume();
					}
				}
			}
		}
	}
	
	public void levelDone()
	{
		setLevel(getLevel() + 1);
		System.out.println(getLevel());
		walls.clear();
		pits.clear();
		enemies.clear();
		gems.clear();
		spikes.clear();
		dirts.clear();
		generateLevel(getLevel());
		assets.setProps("mazeLevel", getLevel()+"");
		assets.setProps("mazeScore", score+"");
		assets.save(assets.getFilename());
	}

	@Override
	public void present(float deltaTime) 
	{
		cam.setCamera();
		renderBackground();
		renderWalls();
		renderPits();
		renderGoal();
		renderHint();
		renderEnemy();
		renderSpikes();
		renderGem();
		renderDirt();
		renderPlayer();
		
		if(state == WORLD_STATE_PAUSED)
		{
			renderPaused();
		}
	}
	
	public void renderPaused()
	{
		TextDrawer.drawStringinRect("Game Paused", new Rectangle(2, 12, 10,2));
		TextDrawer.drawStringinRect("Press P to continue", new Rectangle(2, 9, 10,2));
	}
	
	public void renderDirt()
	{
		for(int i=0; i < dirts.size(); i++)
		{
			if(dirts.get(i).isActive())
			{
				assets.getImage("dirt").draw(new Rectangle(dirts.get(i).position.x -.5f, dirts.get(i).position.y -.5f, 1, 1));
			}
		}
	}
	
	public void renderSpikes()
	{
		for(int i=0; i < spikes.size(); i++)
		{
			if(spikes.get(i).getState() == Spike.STATE_DOWN)
			{
				assets.getImage("spikeDown").draw(new Rectangle(spikes.get(i).position.x -.5f, spikes.get(i).position.y -.5f, 1, 1));
			}
			else
			{
				assets.getImage("spikeUp").draw(new Rectangle(spikes.get(i).position.x -.5f, spikes.get(i).position.y -.5f, 1, 1));
			}
		}
	}
	
	public void renderGem()
	{
		for(int i=0; i < gems.size(); i++)
		{
			if(gems.get(i).isActive())
			{
				assets.getImage("gem").draw(new Rectangle(gems.get(i).position.x -.5f, gems.get(i).position.y -.5f, 1, 1));
			}
		}
	}
	
	public void renderEnemy()
	{
		for(int i=0; i < enemies.size(); i++)
		{			
			Rectangle drawBox = new Rectangle(enemies.get(i).position.x -.5f, enemies.get(i).position.y -.5f, 1, 1);
			if(enemies.get(i) instanceof LREnemy)
			{
				assets.getImage("LREnemy").draw(drawBox);
			}
			else if(enemies.get(i) instanceof UDEnemy)
			{
				assets.getImage("UDEnemy").draw(drawBox);
			}
		}
	}
	
	public void renderHint()
	{
		if(getLevel()==1)
		{
			TextDrawer.drawStringinRect("Arrow Keys or WASD to move", new Rectangle(1,1,13, 4), false);
		}
		else if(getLevel()==2)
		{
			TextDrawer.drawStringinRect("Collect Gems", new Rectangle(2,1,12, 4), false);
		}
		else if(getLevel()==3)
		{
			TextDrawer.drawStringinRect("Do not fall in holes", new Rectangle(2,1,12, 4), false);
		}
		else if(getLevel()==4)
		{
			TextDrawer.drawStringinRect("Avoid Baddies", new Rectangle(2,1,12, 4), false);
		}
	}
	
	public void renderGoal()
	{
		assets.getImage("exit").draw(new Rectangle(goal.position.x -.5f, goal.position.y -.5f, 1, 1));
	}
	
	public void renderWalls()
	{
		for(int i=0; i < walls.size(); i++)
		{
			assets.getImage("wall").draw(walls.get(i).bounds);
		}
	}
	
	public void renderPits()
	{
		for(int i=0; i < pits.size(); i++)
		{
			assets.getImage("pit").draw(new Rectangle(pits.get(i).position.x - .5f, pits.get(i).position.y - .5f, 1, 1));
		}
	}
	
	public void renderBackground()
	{
		assets.getImage("background").draw(new Rectangle(0, 0, WORLD_WIDTH, WORLD_HEIGHT));
	}
	
	public void renderPlayer()
	{
		Rectangle rect = new Rectangle(player.position.x - Player.WIDTH/2, player.position.y - Player.HEIGHT/2, 1f, 1f);
		
		if(player.getLastDirection() == 0)//left
		{
			assets.getImage("playerLeft").draw(rect);
			assets.getImage("playerDigLeft").draw(playerDig.bounds);
		}
		else if(player.getLastDirection() == 1)//up
		{
			assets.getImage("playerUp").draw(rect);
			assets.getImage("playerDigUp").draw(playerDig.bounds);
		}
		else if(player.getLastDirection() == 2)//right
		{
			assets.getImage("playerRight").draw(rect);
			assets.getImage("playerDigRight").draw(playerDig.bounds);
		}
		else //down
		{
			assets.getImage("playerDown").draw(rect);
			assets.getImage("playerDigDown").draw(playerDig.bounds);
		}
	}

	@Override
	public void pause() 
	{
		state = WORLD_STATE_PAUSED;
		assets.save(assets.getFilename());
	}

	@Override
	public void resume() 
	{
		state = WORLD_STATE_PLAYING;
	}

	@Override
	public void dispose() 
	{
		isClosing = true;
	}

	@Override
	public GameScreen getNext() 
	{
		return new MainMenu(assets);
	}
	
	public int getState()
	{
		return state;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
