package com.groupc.math;

/**
 * A rectangle class that holds the lowerleft points
 * as a Vector and the width and height of the rectangle
 * @author Edwin Avalos
 *
 */
public class Rectangle 
{
	//Variables
	public Vector lowerLeft;
	public float width;
	public float height;
	
	//Constructor
	/**
	 * Constructs a new Rectangle based off the values passed
	 * @param x - x coordinate of lowerleft
	 * @param y - y coordinate of lowerleft
	 * @param width - width of rectangle
	 * @param height - height of rectangle
	 */
	public Rectangle(float x, float y, float width, float height)
	{
		this.lowerLeft = new Vector(x, y);
		this.width = width;
		this.height = height;
	}
}
