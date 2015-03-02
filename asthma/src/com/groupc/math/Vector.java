package com.groupc.math;

/**
 * Vector class is used for anything revolving x and y coordinates.
 * This class will handle all math that will be used to move objects.
 * Most methods return vectors since both x and y are affected.
 *
 * @author Edwin Avalos
 */

public class Vector 
{
	//Variables
	public float x;
	public float y;
	
	//Constructors
	/**
	 * Used for when position or value of object is unknown
	 */
	public Vector()
	{}
	
	/** 
	 * Used for when position of object is known
	 * @param x - x value
	 * @param y - y value
	 */
	public Vector(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	//Setters
	/**
	 * Sets the vector to hold a new value for x and y
	 * @param x - new value for x
	 * @param y - new value for y
	 */
	public void set(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Sets the vector to have same values as other vector
	 * @param v - other vector that values are being copied
	 */
	public void set(Vector v)
	{
		this.x = v.x;
		this.y = v.y;
	}
	
	//functions
	/**
	 * Copies the current Vector and returns it as a new Vector
	 * @return a copy of the vector that called the method
	 */
	public Vector copy()
	{
		return new Vector(x, y);
	}
	
	/**
	 * Adds the values of another Vector onto the Vector
	 * @param v - Vector being added
	 */
	public void add(Vector v)
	{
		this.x += v.x;
		this.y += v.y;
	}
	
	/**
	 * Subtracts the values of another Vector onto the Vector
	 * @param v - Vector being subtracted
	 */
	public void sub(Vector v)
	{
		this.x -= v.x;
		this.y -= v.y;
	}
	
	/**
	 * Checks if the Vectors values are greater than another
	 * Vector's values. If one or more values are greater than
	 * set the values to match the other Vector. Then return
	 * whether the two vectors are equal
	 * @param v - Vector that is acting as a max
	 * @return whether or not the Vector are equal
	 */
	public boolean limit(Vector v)
	{
		//check x
		if(Math.abs(this.x) > v.x)
			this.x = v.x;
		//check y
		if(Math.abs(this.y) > v.y)
			this.y = v.y;
		
		return (Math.abs(this.x) == v.x && Math.abs(this.y) == v.y);
	}
	
	/**
	 * Multiples the Vector by a scalar value
	 * @param scalar - number to multiply by
	 * @return  a new Vector that has been scaled up by scalar
	 */
	public Vector mult(float scalar) 
	{
        return new Vector(this.x * scalar, this.y * scalar);
    }
}
