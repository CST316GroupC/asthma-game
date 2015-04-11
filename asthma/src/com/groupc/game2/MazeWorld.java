package com.groupc.game2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import com.groupc.game.Asset;
import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
import com.groupc.game1.JoeyRooster;
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
	
	private Camera cam;
	public final Player player;
	public final ArrayList<Wall> walls;
	private int buttonPresses;
	private int level;
	public MazeWorld(Asset asset) 
	{
		super(asset);
		cam = new Camera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		player = new Player(.5f, .5f);
		buttonPresses = 0;
		level = Integer.parseInt(assets.getProps().getProperty("level"));
		walls = new ArrayList<Wall>();
		generateLevel(1);
	}
	
	public void generateLevel(int level)
	{
		try {
			char sym;
			FileReader rd = new FileReader("resources/game2/levels.txt");
			BufferedReader brd = new BufferedReader(rd);
			String[] rows = new String[15]; //Each level is at most 15 rows
			
			//Get to point in file that contains the level
			for(int i =0; i < ((level-1) * 15); i++)
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
					System.out.println(sym);
					if(sym == 'W')
					{
						walls.add(new Wall(j+Wall.WIDTH/2, WORLD_HEIGHT-i-Wall.HEIGHT/2));
					}
					if(sym == 'P')
					{
						player.position.set(j+Player.WIDTH/2, WORLD_HEIGHT-i-Player.HEIGHT/2);
					}
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(float deltaTime) 
	{
		inputHandling();
		player.update(deltaTime);
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
				if((Keyboard.getEventKey() == Keyboard.KEY_W || Keyboard.getEventKey() == Keyboard.KEY_UP) 
						|| (Keyboard.getEventKey() == Keyboard.KEY_S || Keyboard.getEventKey() == Keyboard.KEY_DOWN) 
						|| (Keyboard.getEventKey() == Keyboard.KEY_A || Keyboard.getEventKey() == Keyboard.KEY_LEFT) 
						|| (Keyboard.getEventKey() == Keyboard.KEY_D || Keyboard.getEventKey() == Keyboard.KEY_RIGHT))
				{
					buttonPresses--;
					if(buttonPresses <= 0)
					{
						player.setState(Player.STATE_STILL);
						player.setDirection(new Vector(0, 0));
					}
				}				
			}
		}
	}

	@Override
	public void present(float deltaTime) 
	{
		cam.setCamera();
		renderBackground();
		renderPlayer();
		renderWalls();
	}
	
	public void renderWalls()
	{
		for(int i=0; i < walls.size(); i++)
		{
			assets.getImage("wall").draw(walls.get(i).bounds);
		}
	}
	public void renderBackground()
	{
		assets.getImage("background").draw(new Rectangle(0, 0, WORLD_WIDTH, WORLD_HEIGHT));
	}
	
	public void renderPlayer()
	{
		Rectangle rect = new Rectangle(player.position.x - Player.WIDTH/2, player.position.y - Player.HEIGHT/2, 1f, 1f);
		
		switch(player.getState())
		{
		default:
			assets.getImage("player").draw(rect);
		}
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
