package com.groupc.game;


import com.groupc.math.Vector;

/**
 * A game object that will definitely move
 * @author Edwin Avalos
 *
 */
public class MovingGameObject extends GameObject
{
	//Variables
	public Vector velocity;
	public Vector accel;
	public final Vector max;
	
	//Constructors
	/**
	 * Creates a new MovingGameObject from the values passed.
	 * Velocity, acceleration, and max velocity are unknown
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public MovingGameObject(float x, float y, int width, int height, int maxVX, int maxVY)
	{
		super(x, y, width, height);
		velocity = new Vector(0, 0);
		accel = new Vector(0, 0);
		max = new Vector(maxVX, maxVY);
	}
	
	/**
	 * Creates a new MovingGameObject from the values passed
	 * Velocity, acceleration, and max velocity are known
	 * @param x - x coordinate of lowerleft
	 * @param y - y coordinate of lowerleft
	 * @param width - width of rectangle
	 * @param height - height of rectangle
	 * @param velX - starting horizontal velocity
	 * @param velY - starting vertical velocity
	 * @param accX - starting horizontal acceleration
	 * @param accY - starting vertical acceleration
	 * @param maxVX - max horizontal velocity
	 * @param maxVY - max vertical velocity
	 */
	public MovingGameObject(float x, float y, int width, int height, float velX, float velY, float accX, float accY, float maxVX, float maxVY)
	{
		super(x, y, width, height);
		velocity = new Vector(velX, velY);
		accel = new Vector(accX, accY);
		max = new Vector(maxVX, maxVY);
	}
	
	/**
	 * Increases velocity by the acceleration unless already
	 * at max speed
	 * @param deltaTime - Time since last redraw
	 */
	public void updateVectors(float deltaTime)
	{
		if(velocity.limit(max))
			velocity.add(accel.mult(deltaTime));
	}
	
}
