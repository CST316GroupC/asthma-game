package com.groupc.game3;

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
import com.groupc.game.Asset;
import com.groupc.game.TextureRegion;
import com.groupc.math.Rectangle;

public class Game3Assets extends Asset
{	
	private static Texture sheet;
	private static Texture background;
	
	public Game3Assets()
	{
		super();
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/atlas.png"));
			sheet = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("mainmenu.png"));
			background = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("resources/game3/wall.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sprites = new Hashtable<String, TextureRegion>();
		load();
	}
	
	public void load()
	{
		reload();
		sprites = new Hashtable<String, TextureRegion>();
		sprites.put("title", new TextureRegion(sheet, 0, 226, 235, 30));
		sprites.put("grass", new TextureRegion(texture, 32, 512 - 31, 30, 30));
		sprites.put("sky", new TextureRegion(texture, 0, 512 - 96, 32, 96));
		sprites.put("back", new TextureRegion(texture, 128, 512 - 64, 32, 32));
		sprites.put("wall", new TextureRegion(background, 0, 0, 512, 512));
		
		TextDrawer.prepare();
	}
	
	public void reload()
	{
		try 
		{
			FileInputStream in = new FileInputStream("res/maze.properties");
			props.load(in);
			in.close();
			String temp = props.getProperty("firstTime", "true");
			if("true".equals(temp))
			{
				save();
			}
		} 
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}
}