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
	public static void drawInt(int num, float x, float y, float width, float height, float length)
	{
		for(int i=0; i < length; i++)
		{
			int temp = num% 10;
			num = num / 10;
			Rectangle rect = new Rectangle(x + (length*width) - (i * width), y, width, height);
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
	
	/**
	 * Draws a string in all lower caps. Final size of drawn string is x + width*str.length() by height
	 * @param str - The string to draw
	 * @param x - The starting x coordinate
	 * @param y - The starting y coordinate
	 * @param width - The width of each letter
	 * @param height - The height of each letter
	 */
	public static void drawString(String str, float x, float y, float width, float height)
	{
		str = str.toLowerCase(new Locale("US"));
		for(int i = 0; i < str.length(); i++)
		{
			Rectangle rect = new Rectangle(x + i*width, y, width, height);
			char temp = str.charAt(i);
			switch(temp)
			{
				case 'a':
					Assets.letterA.draw(rect);
					break;
				case 'c':
					Assets.letterC.draw(rect);
					break;
				case 'd':
					Assets.letterD.draw(rect);
					break;
				case 'e':
					Assets.letterE.draw(rect);
					break;
				case 'f':
					Assets.letterF.draw(rect);
					break;
				case 'g':
					Assets.letterG.draw(rect);
					break;
				case 'i':
					Assets.letterI.draw(rect);
					break;
				case 'l':
					Assets.letterL.draw(rect);
					break;
				case 'n':
					Assets.letterN.draw(rect);
					break;
				case 'o':
					Assets.letterO.draw(rect);
					break;
				case 'p':
					Assets.letterP.draw(rect);
					break;
				case 'r':
					Assets.letterR.draw(rect);
					break;
				case 's':
					Assets.letterS.draw(rect);
					break;
				case 't':
					Assets.letterT.draw(rect);
					break;
				case 'u':
					Assets.letterU.draw(rect);
					break;
			}
		}
		
	}
}
