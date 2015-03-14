package com.groupc.math;


/**
 * The CollisionChecker class is used to determine if collisions are happening and or if something like a point
 * likes inside a rectangle somewhere on screen
 * @author Edwin Avalos
 *
 */
public class CollisionChecker
{
	/**
	 * Check if two Rectangles are colliding
	 * @param r1 - One of the Rectangles
	 * @param r2 - The other Rectangle
	 * @return whether the two rectangles have collided
	 */
	public static boolean RectToRect(Rectangle r1, Rectangle r2)
	{
		return (r1.lowerLeft.x < r2.lowerLeft.x + r2.width &&
		           r1.lowerLeft.x + r1.width > r2.lowerLeft.x &&
		           r1.lowerLeft.y < r2.lowerLeft.y + r2.height &&
		           r1.lowerLeft.y + r1.height > r2.lowerLeft.y);
	}
	
	/**
	 * Check if a point (mouse click) happens to be inside a rectangle
	 * @param v - point on screen
	 * @param r - rectangle that is on screen
	 * @return whether the point is inside the Rectangle or not
	 */
	public static boolean PointToRect(Vector v, Rectangle r)
	{
		return (r.lowerLeft.x <= v.x && r.lowerLeft.x + r.width >= v.x &&
	               r.lowerLeft.y <= v.y && r.lowerLeft.y + r.height >= v.y);
	}
	
	/**
	 * Check if the rectangle is visible on screen
	 * @param r - rectangle in question
	 * @param width - width of the screen
	 * @param height - height of the screen
	 * @return True if on screen, false if otherwise
	 */
	public static boolean RectInside(Rectangle r, int width, int height)
	{
		return (r.lowerLeft.x + r.width <= width && r.lowerLeft.x >= 0
				&& r.lowerLeft.y + height <= height && r.lowerLeft.y >= 0);
	}

}
