package com.groupc;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Locale;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.groupc.game.TextureRegion;
import com.groupc.game1.Game1Assets;
import com.groupc.math.Rectangle;

/**
 * 
 * @author Edwin Avalos
 *
 */
public final class TextDrawer 
{
	
	private TextDrawer()
	{
	}
	
	private static Hashtable<String, TextureRegion> characters;
	private static Texture text;
	
	/**
	 * Loads all the Textures that will be used when drawing text.
	 */
	public static void prepare()
	{
		characters = new Hashtable<String, TextureRegion>(); 
		try
		{
			text = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("resources/text.png"));

			//row 3 0 to 9
			characters.put("char0", new TextureRegion(text, 39*0 + 1, 512 - 287, 39, 72));
			characters.put("char1", new TextureRegion(text, 39*1 + 1, 512 - 287, 39, 72));
			characters.put("char2", new TextureRegion(text, 39*2 + 1, 512 - 287, 39, 72));
			characters.put("char3", new TextureRegion(text, 39*3 + 1, 512 - 287, 39, 72));
			characters.put("char4", new TextureRegion(text, 39*4 + 1, 512 - 287, 39, 72));
			characters.put("char5", new TextureRegion(text, 39*5 + 1, 512 - 287, 39, 72));
			characters.put("char6", new TextureRegion(text, 39*6 + 1, 512 - 287, 39, 72));
			characters.put("char7", new TextureRegion(text, 39*7 + 1, 512 - 287, 39, 72));
			characters.put("char8", new TextureRegion(text, 39*8 + 1, 512 - 287, 39, 72));
			characters.put("char9", new TextureRegion(text, 39*9 + 1, 512 - 287, 39, 72));
			
			//row 1 a to m
			characters.put("chara", new TextureRegion(text, 39*0 + 1, 512-87, 39, 85));
			characters.put("charb", new TextureRegion(text, 39*1 + 1, 512-87, 39, 85));
			characters.put("charc", new TextureRegion(text, 39*2 + 1, 512-87, 39, 85));
			characters.put("chard", new TextureRegion(text, 39*3 + 1, 512-87, 39, 85));
			characters.put("chare", new TextureRegion(text, 39*4 + 1, 512-87, 39, 85));
			characters.put("charf", new TextureRegion(text, 39*5 + 1, 512-87, 39, 85));
			characters.put("charg", new TextureRegion(text, 39*6 + 1, 512-87, 39, 85));
			characters.put("charh", new TextureRegion(text, 39*7 + 1, 512-87, 39, 85));
			characters.put("chari", new TextureRegion(text, 39*8 + 1, 512-87, 39, 85));
			characters.put("charj", new TextureRegion(text, 39*9 + 1, 512-87, 39, 85));
			characters.put("chark", new TextureRegion(text, 39*10 + 1, 512-87, 39, 85));
			characters.put("charl", new TextureRegion(text, 39*11 + 1, 512-87, 39, 85));
			characters.put("charm", new TextureRegion(text, 39*12 + 1, 512-87, 39, 85));
			
			//row 2 n to z
			characters.put("charn", new TextureRegion(text, 39*0 + 1, 512-192, 39, 85));
			characters.put("charo", new TextureRegion(text, 39*1 + 1, 512-192, 39, 85));
			characters.put("charp", new TextureRegion(text, 39*2 + 1, 512-192, 39, 85));
			characters.put("charq", new TextureRegion(text, 39*3 + 1, 512-192, 39, 85));
			characters.put("charr", new TextureRegion(text, 39*4 + 1, 512-192, 39, 85));
			characters.put("chars", new TextureRegion(text, 39*5 + 1, 512-192, 39, 85));
			characters.put("chart", new TextureRegion(text, 39*6 + 1, 512-192, 39, 85));
			characters.put("charu", new TextureRegion(text, 39*7 + 1, 512-192, 39, 85));
			characters.put("charv", new TextureRegion(text, 39*8 + 1, 512-192, 39, 85));
			characters.put("charw", new TextureRegion(text, 39*9 + 1, 512-192, 39, 85));
			characters.put("charx", new TextureRegion(text, 39*10 + 1, 512-192, 39, 85));
			characters.put("chary", new TextureRegion(text, 39*11 + 1, 512-192, 39, 85));
			characters.put("charz", new TextureRegion(text, 39*12 + 1, 512-192, 39, 85));
			
			//row 4 A to M
			characters.put("charA", new TextureRegion(text, 39*0 + 1, 512-402, 39, 85));
			characters.put("charB", new TextureRegion(text, 39*1 + 1, 512-402, 39, 85));
			characters.put("charC", new TextureRegion(text, 39*2 + 1, 512-402, 39, 85));
			characters.put("charD", new TextureRegion(text, 39*3 + 1, 512-402, 39, 85));
			characters.put("charE", new TextureRegion(text, 39*4 + 1, 512-402, 39, 85));
			characters.put("charF", new TextureRegion(text, 39*5 + 1, 512-402, 39, 85));
			characters.put("charG", new TextureRegion(text, 39*6 + 1, 512-402, 39, 85));
			characters.put("charH", new TextureRegion(text, 39*7 + 1, 512-402, 39, 85));
			characters.put("charI", new TextureRegion(text, 39*8 + 1, 512-402, 39, 85));
			characters.put("charJ", new TextureRegion(text, 39*9 + 1, 512-402, 39, 85));
			characters.put("charK", new TextureRegion(text, 39*10 + 1, 512-402, 39, 85));
			characters.put("charL", new TextureRegion(text, 39*11 + 1, 512-402, 39, 85));
			characters.put("charM", new TextureRegion(text, 39*12 + 1, 512-402, 39, 85));
			
			//row 5 N to Z
			characters.put("charN", new TextureRegion(text, 39*0 + 1, 0, 39, 85));
			characters.put("charO", new TextureRegion(text, 39*1 + 1, 0, 39, 85));
			characters.put("charP", new TextureRegion(text, 39*2 + 1, 0, 39, 85));
			characters.put("charQ", new TextureRegion(text, 39*3 + 1, 0, 39, 85));
			characters.put("charR", new TextureRegion(text, 39*4 + 1, 0, 39, 85));
			characters.put("charS", new TextureRegion(text, 39*5 + 1, 0, 39, 85));
			characters.put("charT", new TextureRegion(text, 39*6 + 1, 0, 39, 85));
			characters.put("charU", new TextureRegion(text, 39*7 + 1, 0, 39, 85));
			characters.put("charV", new TextureRegion(text, 39*8 + 1, 0, 39, 85));
			characters.put("charW", new TextureRegion(text, 39*9 + 1, 0, 39, 85));
			characters.put("charX", new TextureRegion(text, 39*10 + 1, 0, 39, 85));
			characters.put("charY", new TextureRegion(text, 39*11 + 1, 0, 39, 85));
			characters.put("charZ", new TextureRegion(text, 39*12 + 1, 0, 39, 85));
			
			//space
			characters.put("char ", new TextureRegion(text, 0, 0, 1, 1));
			
			//border
			characters.put("border", new TextureRegion(text, 470, 512-312, 32, 32));
			
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
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
			getChar("char"+temp).draw(rect);				
		}
	}
	
	/**
	 * Draws a string in all lower caps. Final size of drawn string is x + width*str.length() by height
	 * @param str - The string to draw
	 * @param x - The starting x coordinate
	 * @param y - The starting y coordinate
	 * @param width - The width of each char
	 * @param height - The height of each char
	 */
	public static void drawString(String string, float x, float y, float width, float height)
	{
		//String str = string.toLowerCase(new Locale("US"));
		for(int i = 0; i < string.length(); i++)
		{
			Rectangle rect = new Rectangle(x + i*width, y, width, height);
			drawChar(string.charAt(i), rect);			
		}		
	}
	
	/**
	 * Draws the correct char in the rectangle specified
	 * @param temp - char to be drawn
	 * @param rect - Rectangle to draw char inside off.
	 */
	public static void drawChar(char temp, Rectangle rect)
	{
		getChar("char"+temp).draw(rect);
	}
	
	/**
	 * Draws a string inside of a specify rectangle
	 * @param string - String to be drawn
	 * @param rect - Rectangle to draw string inside of.
	 */
	public static void drawStringinRect(String string, Rectangle rect, Boolean border)
	{
		//String str = string.toLowerCase(new Locale("US"));
		float newWidth = rect.width / string.length();
		for(int i = 0; i < string.length(); i++)
		{
			Rectangle let = new Rectangle(rect.lowerLeft.x + i*newWidth, rect.lowerLeft.y, newWidth, rect.height);
			drawChar(string.charAt(i), let);			
		}
		if(border)
		{
			characters.get("border").draw(rect);
		}
	}
	
	/**
	 * Return the TextureRegion of the char to draw
	 * @param cha - char that will be drawn
	 * @return TextureRegion to draw
	 */
	private static TextureRegion getChar(String cha)
	{
		return characters.get(cha);
	}
}
