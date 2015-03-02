package com.groupc.game1;

import com.groupc.game.MovingGameObject;

/**
 * Ball object that gets shot out of the cannon for the game
 * @author Edwin Avalos
 *
 */
public class Ball extends MovingGameObject
{
	//States
	public final int STATE_STILL = 0; //in cannon
	public final int STATE_FIRED = 1; //being fired, at this point initial velocity set
	public final int STATE_MOVING = 2; //gravity is in effect and so is collision detecting
	public final int STATE_STOPPED = 3; //ball has being stopped by forces
	
	//variables
	public static int maxSpeed = 50;
	public static int maxFalling = 100;
	

	//Constructor
	/**
	 * The ball object has no set velocity or acceleration, those are determine at the
	 * time of being fired from the cannon.
	 * @param x - starting x coordinate
	 * @param y - starting y coordinate
	 * @param width - width of ball
	 * @param height - height of ball
	 */
	public Ball(float x, float y, int width, int height) 
	{
		super(x, y, width, height, maxSpeed , maxFalling);
		
	}
	
	public void update(float deltaTime)
	{
		updateVectors(deltaTime);
		bounds.lowerLeft.add(velocity.mult(deltaTime));
	}

}
