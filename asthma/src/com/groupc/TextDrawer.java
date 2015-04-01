package com.groupc;

import java.util.Locale;

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
	public static void drawInt(int number, float x, float y, float width, float height, float length)
	{
		int num = number;
		for(int i=0; i < length; i++)
		{
			int temp = num % 10;
			num = num / 10;
			Rectangle rect = new Rectangle(x + (length*width) - (i * width), y, width, height);
			switch(temp)
			{
				case 0:
					Assets.getImage("zero").draw(rect);
					break;
				case 1:
					Assets.getImage("one").draw(rect);
					break;
				case 2:
					Assets.getImage("two").draw(rect);
					break;
				case 3:
					Assets.getImage("three").draw(rect);
					break;
				case 4:
					Assets.getImage("four").draw(rect);
					break;
				case 5:
					Assets.getImage("five").draw(rect);
					break;
				case 6:
					Assets.getImage("six").draw(rect);
					break;
				case 7:
					Assets.getImage("seven").draw(rect);
					break;
				case 8:
					Assets.getImage("eight").draw(rect);
					break;
				case 9:
					Assets.getImage("nine").draw(rect);
					break;
				default:
					break;
						
			}
		}
	}
	
	/**
	 * Draws a string in all lower caps. Final size of drawn string is x + width*str.length() by height
	 * @param str - The string to draw
	 * @param x - The starting x coordinate
	 * @param y - The starting y coordinate
	 * @param width - The width of each letter
	 * @param height - The height of each letter
	 */
	public static void drawString(String string, float x, float y, float width, float height)
	{
		String str = string.toLowerCase(new Locale("US"));
		for(int i = 0; i < str.length(); i++)
		{
			Rectangle rect = new Rectangle(x + i*width, y, width, height);
			drawLetter(str.charAt(i), rect);			
		}		
	}
	
	/**
	 * Draws the correct letter in the rectangle specified
	 * @param temp - Letter to be drawn
	 * @param rect - Rectangle to draw letter inside off.
	 */
	public static void drawLetter(char temp, Rectangle rect)
	{
		switch(temp)
		{
			case 'a':
				Assets.getImage("letterA").draw(rect);
				break;
			case 'c':
				Assets.getImage("letterC").draw(rect);
				break;
			case 'd':
				Assets.getImage("letterD").draw(rect);
				break;
			case 'e':
				Assets.getImage("letterE").draw(rect);
				break;
			case 'f':
				Assets.getImage("letterF").draw(rect);
				break;
			case 'g':
				rect.lowerLeft.y -= rect.height/6;
				Assets.getImage("letterG").draw(rect);
				break;
			case 'i':
				Assets.getImage("letterI").draw(rect);
				break;
			case 'l':
				Assets.getImage("letterL").draw(rect);
				break;
			case 'n':
				Assets.getImage("letterN").draw(rect);
				break;
			case 'o':
				Assets.getImage("letterO").draw(rect);
				break;
			case 'p':
				rect.lowerLeft.y -= rect.height/6;
				Assets.getImage("letterP").draw(rect);
				break;
			case 'q':
				rect.lowerLeft.y -= rect.height/6;
				rect.width *= -1;
				Assets.getImage("letterP").draw(rect);
				break;
			case 'r':
				Assets.getImage("letterR").draw(rect);
				break;
			case 's':
				Assets.getImage("letterS").draw(rect);
				break;
			case 't':
				Assets.getImage("letterT").draw(rect);
				break;
			case 'u':
				Assets.getImage("letterU").draw(rect);
				break;
			case 'w':
				Assets.getImage("letterW").draw(rect);
				break;
			default:
				break;
		}
	}
	
	/**
	 * Draws a string inside of a specify rectangle
	 * @param string - String to be drawn
	 * @param rect - Rectangle to draw string inside of.
	 */
	public static void drawStringinRect(String string, Rectangle rect)
	{
		String str = string.toLowerCase(new Locale("US"));
		float newWidth = rect.width / str.length();
		System.out.println(str.length());
		for(int i = 0; i < str.length(); i++)
		{
			Rectangle let = new Rectangle(rect.lowerLeft.x + i*newWidth, rect.lowerLeft.y, newWidth, rect.height);
			drawLetter(str.charAt(i), let);			
		}
	}
}
