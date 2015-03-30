package com.groupc.game1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.groupc.game.TextureRegion;

public final class Assets
{
	private static Hashtable<String, Texture> textures;
	private static Hashtable<String, TextureRegion> sprites;	
	
	private static Properties joeyProps;
	
	public static void load()
	{
		reload();
		textures = new Hashtable<String, Texture>();
		sprites = new Hashtable<String, TextureRegion>();
		
		try {
			textures.put("sheet", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("mainmenu.png")));			 
			textures.put("atlas", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/atlas.png")));
			
			sprites.put("title", new TextureRegion(textures.get("sheet"), 0, 226, 235, 30));
			sprites.put("playBut", new TextureRegion(textures.get("sheet"), 0, 128, 116, 43));
			sprites.put("upgradeBut", new TextureRegion(textures.get("sheet"), 0, 176, 116, 42));
			sprites.put("optionsBut", new TextureRegion(textures.get("sheet"), 0, 84, 113, 42));
			sprites.put("soundOn", new TextureRegion(textures.get("sheet"), 0, 24, 54, 55));
			sprites.put("soundOff", new TextureRegion(textures.get("sheet"),  54, 24, 54, 55));
			sprites.put("soundLbl", new TextureRegion(textures.get("sheet"),  0, 0, 65, 23));
			sprites.put("arrowBut", new TextureRegion(textures.get("sheet"), 110, 24, 54, 55));
			
			sprites.put("sky", new TextureRegion(textures.get("atlas"), 0, 512 - 96, 32, 96));
			sprites.put("grass", new TextureRegion(textures.get("atlas"), 32, 512 - 32, 32, 32));
			sprites.put("cow", new TextureRegion(textures.get("atlas"), 64, 512 - 32, 64, 32));
			sprites.put("cowHit", new TextureRegion(textures.get("atlas"), 64, 512 - 32 - 64, 64, 64));
			sprites.put("seed", new TextureRegion(textures.get("atlas"), 128, 512 - 32, 32, 32));
			sprites.put("ramp", new TextureRegion(textures.get("atlas"), 160, 512 - 32, 32, 32));
			sprites.put("joeySk", new TextureRegion(textures.get("atlas"), 192, 512 - 32, 32, 32));
			sprites.put("joeyFly", new TextureRegion(textures.get("atlas"), 192, 512 - 32 - 32, 32, 32));
			sprites.put("joeyFall", new TextureRegion(textures.get("atlas"), 192, 512 - 64 - 32, 32, 32));
			sprites.put("joeyBou", new TextureRegion(textures.get("atlas"),192,  512 - 96 - 32, 32,32));
			sprites.put("joeyFlap", new TextureRegion(textures.get("atlas"),192, 512 - 128 - 32, 32, 32));
			sprites.put("joeyRamp", new TextureRegion(textures.get("atlas"), 192, 512 - 160 - 32, 32, 32));
			
			sprites.put("one", new TextureRegion(textures.get("atlas"), 0, 512 - 128 - 1, 192/10, 32));
			sprites.put("two", new TextureRegion(textures.get("atlas"), 192/10, 512 - 128 - 1, 192/10, 32));
			sprites.put("three", new TextureRegion(textures.get("atlas"), 192/10 * 2, 512 - 128 - 1, 192/10, 32));
			sprites.put("four", new TextureRegion(textures.get("atlas"), 192/10 * 3, 512 - 128 - 1, 192/10, 32));
			sprites.put("five", new TextureRegion(textures.get("atlas"), 192/10 * 4, 512 - 128 - 1, 192/10, 32));
			sprites.put("six", new TextureRegion(textures.get("atlas"), 192/10 * 5, 512 - 128 - 1, 192/10, 32));
			sprites.put("seven", new TextureRegion(textures.get("atlas"), 192/10 * 6, 512 - 128 - 1, 192/10, 32));
			sprites.put("eight", new TextureRegion(textures.get("atlas"), 192/10 * 7, 512 - 128 - 1, 192/10, 32));
			sprites.put("nine", new TextureRegion(textures.get("atlas"), 192/10 * 8, 512 - 128 - 1, 192/10, 32));
			sprites.put("zero", new TextureRegion(textures.get("atlas"), 192/10 * 9, 512 - 128 - 1, 192/10, 32));
			
			//row 1
			sprites.put("letterA", new TextureRegion(textures.get("atlas"), 0, 512-160, 192/8, 32));
			sprites.put("letterC", new TextureRegion(textures.get("atlas"), 192/8, 512-160, 192/8, 32));
			sprites.put("letterD", new TextureRegion(textures.get("atlas"), 192/8 * 2, 512-160, 192/8, 32));
			sprites.put("letterE", new TextureRegion(textures.get("atlas"), 192/8 * 3, 512-160, 192/8, 32));
			sprites.put("letterF", new TextureRegion(textures.get("atlas"), 192/8 * 4, 512-160, 192/8, 32));
			sprites.put("letterG", new TextureRegion(textures.get("atlas"), 192/8 * 5, 512-160, 192/8, 32));
			sprites.put("letterI", new TextureRegion(textures.get("atlas"), 192/8 * 6, 512-160, 192/8, 32));
			sprites.put("letterL", new TextureRegion(textures.get("atlas"), 192/8 * 7, 512-160, 192/8, 32));
			
			//row 2
			sprites.put("letterN", new TextureRegion(textures.get("atlas"), 0, 512-192, 192/8, 32));
			sprites.put("letterO", new TextureRegion(textures.get("atlas"), 192/8, 512-192, 192/8, 32));
			sprites.put("letterP", new TextureRegion(textures.get("atlas"), 192/8 * 2, 512-192, 192/8, 32));
			sprites.put("letterR", new TextureRegion(textures.get("atlas"), 192/8 * 3, 512-192, 192/8, 32));
			sprites.put("letterS", new TextureRegion(textures.get("atlas"), 192/8 * 4, 512-192, 192/8, 32));
			sprites.put("letterT", new TextureRegion(textures.get("atlas"), 192/8 * 5, 512-192, 192/8, 32));
			sprites.put("letterU", new TextureRegion(textures.get("atlas"), 192/8 * 6, 512-192, 192/8, 32));
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void save()
	{
		FileOutputStream out;
		try {
			out = new FileOutputStream("res/joey.properties");
			joeyProps.store(out, "---No Comment---");
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void reload()
	{
		joeyProps = new Properties();
		try {
			FileInputStream in = new FileInputStream("res/joey.properties");
			joeyProps.load(in);
			in.close();
			String temp = joeyProps.getProperty("firstTime", "true");
			if(temp.equals("true"))
			{
				joeyProps.setProperty("speedMult", "1");
				joeyProps.setProperty("statima", "1");
				joeyProps.setProperty("score", "0");
				joeyProps.setProperty("maxDistance", "0");
				joeyProps.setProperty("seeds", "0");
				joeyProps.setProperty("firstTime", "false");
				save();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}
	
	public static TextureRegion getImage(String img)
	{
		return sprites.get(img);
	}
	
	public static Texture getTexture(String txt)
	{
		return textures.get(txt);
	}
	
	public static Properties getProps()
	{
		return joeyProps;
	}
	
	public static void setProps(String key, String value)
	{
		joeyProps.setProperty(key, value);
	}
}
