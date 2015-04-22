package com.groupc.game4;

import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import com.groupc.TextDrawer;
import com.groupc.game.Asset;
import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
import com.groupc.game3.Game3Assets;
import com.groupc.game4.*;
import com.groupc.math.CollisionChecker;
import com.groupc.math.Rectangle;
import com.groupc.math.Vector;


public class GameWorld extends GameScreen
{
	public static final float FRUSTUM_WIDTH = 10;
	public static final float FRUSTUM_HEIGHT = 10;
	
	public static final int WORLD_STATE_PAUSED = 0;
	public static final int WORLD_STATE_PLAYING = 1;
	public static final int WORLD_STATE_OVER = 2;
	
    public static final float TEXT_SIZE = .3f;
    public static final int MAX_SCORE_DIGITS = 8; //99999999 max score
    

	
	public static final int GRAVITY = -10;
	
    public int state;
    public float timeLeft;
    public final Food[] food;
    private static int maxTime;
    private int score;
    private float distance;
    private int foodTimeBonus, foodScoreBonus;
    
    private Camera cam;
    private Random rand;

	
	public GameWorld(Asset assets)
	{        
        super(assets);
        assets.reload();
        maxTime = Integer.parseInt(assets.getProps().getProperty("game4Timer"));
        foodTimeBonus = Integer.parseInt(assets.getProps().getProperty("healthyFoodTimeBonus"));
        foodScoreBonus = Integer.parseInt(assets.getProps().getProperty("healthyFoodScoreBonus"));
        timeLeft = maxTime;
        rand = new Random();
        this.state = WORLD_STATE_PLAYING;
        cam = new Camera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        score = Integer.parseInt(assets.getProps().getProperty("game4Score"));
        
        food = new Food[5];

	}
	
	public void update(float deltaTime)
	{
        inputHandling();
        switch(state)
        {
            case WORLD_STATE_PLAYING:
            	save();
                updateFood();
    			for(int i = 0; i < food.length; i++)
    			{
    				if(food[i] != null)
    				{
    					if(food[i].timedOut(deltaTime))
    						{
    						//if good food times out, subtractPoints, if bad food, add points.
    							score -= food[i].clicked();
    							food[i] = null;
    						};
    				}
    			}
    			timeLeft -= deltaTime;
                collision();
                if(timeLeft <= 0)
                {
                    state = WORLD_STATE_OVER;
                    save();                
                }
                break;
            case WORLD_STATE_OVER:
            case WORLD_STATE_PAUSED:
        }

	}
	
    private void collision()
	{
    	collisionFood();
		
	}
	public void collisionFood()
	{
		Vector mouseClick = new Vector();
		if(Mouse.isButtonDown(0))
		{
			mouseClick.set(Mouse.getX(), Mouse.getY());
			cam.click(mouseClick);
			for(int i = 0; i < food.length; i++)
			{
				if(food[i] != null)
				{
					if(CollisionChecker.PointToRect(mouseClick, food[i].bounds))
					{
						//clicked returns postive time for healty food, negative for unhealthy food
						score += food[i].clicked();
						if(score <= 0)
						{
							score = 0;
						}
						if(food[i].clicked() <=0)
							timeLeft += food[i].clicked();
						food[i] = null;
					}
				}
			}
		}
	}

	public void updateFood()
    {
        for(int i = 0; i < food.length; i++)
        {
            if(food[i] != null)
            {
                food[i].update();
            }
            else
            {
                int chance = rand.nextInt(4);
                switch(chance){
                case 0: food[i] = new Orange(0.5f + (rand.nextFloat() * (FRUSTUM_WIDTH - 1)), 0.5f + (rand.nextFloat() * (FRUSTUM_HEIGHT - 1)), foodTimeBonus, foodScoreBonus);
                    break;
                case 1: food[i] = new Broccoli(0.5f + (rand.nextFloat() * (FRUSTUM_WIDTH - 1)), 0.5f + (rand.nextFloat() * (FRUSTUM_HEIGHT - 1)), foodTimeBonus, foodScoreBonus);
                    break;
                case 2: food[i] = new Cake(0.5f + (rand.nextFloat() * (FRUSTUM_WIDTH - 1)), 0.5f + (rand.nextFloat() * (FRUSTUM_HEIGHT - 1)));
                    break;
                case 3: food[i] = new Cookie(0.5f + (rand.nextFloat() * (FRUSTUM_WIDTH - 1)), 0.5f + (rand.nextFloat() * (FRUSTUM_HEIGHT - 1)));
                    break;
                
                }
            }
        }        
    }

	/**
	 * Prepares the property file to store all the relevent player data at moment of method called.
	 */
	private void save()
	{
		System.out.println(score);
		assets.getProps().setProperty("game4Score", ""+score);
		
		//set new max score
		if( Float.parseFloat(assets.getProps().getProperty("game4MaxScore")) < score)
		{
			assets.getProps().setProperty("game4MaxScore", ""+score);
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
			}
		}
	}
	
	public void renderPlaying()
	{	
		cam.setCamera();
		
		//assets.getTexture("sheet").bind();
		assets.getImage("table").draw(new Rectangle(0, 0, 10, 10));
		renderHud();
		renderFood();
	}
	
	 private void renderFood()
	    {
	        for(int i =0; i < food.length; i++)
	        {
	            if(food[i] != null)
	            {
	                Rectangle rect = new Rectangle(food[i].position.x - .5f, food[i].position.y - .5f, 1f, 1f);
	                if(food[i] instanceof Orange){
	                    assets.getImage("orange").draw(rect);
	                }
	                else if(food[i] instanceof Broccoli){
	                    assets.getImage("broccoli").draw(rect);
	                }
	                else if(food[i] instanceof Cake){
	                    assets.getImage("cake").draw(rect);
	                }
	                else if(food[i] instanceof Cookie){
	                    assets.getImage("cookie").draw(rect);
	                }
	                
	            }
	        }
	        
	    }

	public void renderHud()
	{
		//hud
		
		TextDrawer.drawStringinRect("score", new Rectangle(cam.position.x - FRUSTUM_WIDTH/2, cam.position.y + FRUSTUM_HEIGHT/2 - TEXT_SIZE, TEXT_SIZE * 5, TEXT_SIZE));
        if(score <= 0){
        	score = 0;
        }
		TextDrawer.drawStringinRect(score +"", new Rectangle(cam.position.x - 3f, cam.position.y + FRUSTUM_HEIGHT/2 - TEXT_SIZE, TEXT_SIZE, TEXT_SIZE));
		TextDrawer.drawStringinRect("time", new Rectangle(cam.position.x - FRUSTUM_WIDTH/2, cam.position.y + FRUSTUM_HEIGHT/2 - 2 * TEXT_SIZE, TEXT_SIZE * 5, TEXT_SIZE));
        if(timeLeft <= 0){
        	timeLeft = 0;
        }
        int time = (int) timeLeft;
		TextDrawer.drawStringinRect(time +"  seconds", new Rectangle(cam.position.x - 3f, cam.position.y + FRUSTUM_HEIGHT/2 - 2 * TEXT_SIZE, TEXT_SIZE * 5, TEXT_SIZE));
	}
	
	public void renderOver()
	{
		TextDrawer.drawString("Game Over", cam.position.x - FRUSTUM_WIDTH/2, cam.position.y + FRUSTUM_HEIGHT/2 - 4 * TEXT_SIZE, TEXT_SIZE * 2, TEXT_SIZE * 2);
        TextDrawer.drawString("Press q to quit", cam.position.x - FRUSTUM_WIDTH/2, cam.position.y + FRUSTUM_HEIGHT/2 - 6 * TEXT_SIZE, TEXT_SIZE * 2, TEXT_SIZE * 2);

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
