package com.groupc.game;

import org.lwjgl.opengl.GL11;

import com.groupc.math.Rectangle;

/**
 * An object that has a position and size but generally 
 * will not move
 * @author Edwin Avalos
 *
 */
public class GameObject
{
	//Variables
	public Rectangle rect;
	
	//Constructors
	/**
	 * Creates a new GameObject from the values passed
	 * @param x - x coordinate of lowerleft
	 * @param y - y coordinate of lowerleft
	 * @param width - width of object
	 * @param height - width of object
	 */
	public GameObject(float x, float y, int width, int height)
	{
		rect = new Rectangle(x, y, width, height);
	}
}

