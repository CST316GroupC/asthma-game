package com.groupc;

import com.groupc.game1.Assets;
import com.groupc.math.Rectangle;

/**
 * 
 * @author Edwin Avalos
 *
 */
public class TextDrawer 
{
	/**
	 * Draws a number on the screen. Draws from right to left and only draws the number of digits specified
	 * 
	 * @param num - The number to be drawn
	 * @param x - The x coordinate of the leftmost digit
	 * @param y - the y coordinate of the bottom of the number being drawn
	 * @param width - The width of each digit
	 * @param height - The height of each digit
	 * @param length - The number of digits
	 */
	public static void drawInt(int num, float x, float y, float width, float height, float length)
	{
		for(int i=0; num > 0; i++)
		{
			int temp = num% 10;
			num = num / 10;
			Rectangle rect = new Rectangle(x + length - (i * width), y, width, height);
			switch(temp)
			{
				case 0:
					Assets.zero.draw(rect);
					break;
				case 1:
					Assets.one.draw(rect);
					break;
				case 2:
					Assets.two.draw(rect);
					break;
				case 3:
					Assets.three.draw(rect);
					break;
				case 4:
					Assets.four.draw(rect);
					break;
				case 5:
					Assets.five.draw(rect);
					break;
				case 6:
					Assets.six.draw(rect);
					break;
				case 7:
					Assets.seven.draw(rect);
					break;
				case 8:
					Assets.eight.draw(rect);
					break;
				case 9:
					Assets.nine.draw(rect);
					break;
			}
		}
	}
	

	public static void drawString(String str, float x, float y, float width, float height)
	{
		
	}
}
