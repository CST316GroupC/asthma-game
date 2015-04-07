package com.groupc.game2;

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

import com.groupc.TextDrawer;
import com.groupc.game.TextureRegion;

public final class Assets
{
	private static Texture textures;
	private static Hashtable<String, TextureRegion> sprites;	
	
	private static Properties mazeProps;
	
	private Assets()
	{
	}
	
	public static void load()
	{
		reload();
		sprites = new Hashtable<String, TextureRegion>();
		
		try {
			textures = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/maze.png"));
			
			sprites.put("player", new TextureRegion(textures, 0, 512-32, 32, 32));
			sprites.put("wall", new TextureRegion(textures, 0, 512-64, 32, 32));
						
		} catch (IOException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TextDrawer.prepare();
	}
	
	public static void save()
	{
		FileOutputStream out;
		try {
			out = new FileOutputStream("res/maze.properties");
			mazeProps.store(out, "---No Comment---");
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void reload()
	{
		mazeProps = new Properties();
		try {
			FileInputStream in = new FileInputStream("res/maze.properties");
			mazeProps.load(in);
			in.close();
			String temp = mazeProps.getProperty("firstTime", "true");
			if("true".equals(temp))
			{
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
		return textures;
	}
	
	public static Properties getProps()
	{
		return mazeProps;
	}
	
	public static void setProps(String key, String value)
	{
		mazeProps.setProperty(key, value);
	}
}
