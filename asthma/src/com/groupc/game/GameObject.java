package com.groupc.game;

import com.groupc.math.Rectangle;
import com.groupc.math.Vector;

/**
 * An object that has a position and size but generally 
 * will not move
 * @author Edwin Avalos
 *
 */
public class GameObject
{
	//Variables
	public Rectangle bounds;
	public Vector position;
	
	//Constructors
	/**
	 * Creates a new GameObject from the values passed
	 * @param x - x coordinate of lowerleft
	 * @param y - y coordinate of lowerleft
	 * @param width - width of object
	 * @param height - width of object
	 */
	public GameObject(float x, float y, float width, float height)
	{
		position = new Vector(x, y);
		bounds = new Rectangle(x - width/2, y - height/2, width, height);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(0,0,0,0);
	}
}

