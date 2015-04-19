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
	private static Texture g3texture;
	
	public Game3Assets()
	{
		super();
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("resources/game1/atlas.png"));
			sheet = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("resources/game1/mainmenu.png"));
			background = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("resources/game3/wall.png"));
			g3texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("resources/game3/game3assets.png"));
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
		sprites.put("back", new TextureRegion(texture, 128, 512 - 64, 32, 32));
		sprites.put("wall", new TextureRegion(background, 0, 0, 512, 512));
		
		sprites.put("rain", new TextureRegion(g3texture, 0, 600 - 1*58, 58, 58));
		sprites.put("treasure", new TextureRegion(g3texture, 0, 600 - 2*58, 58, 58));
		sprites.put("paperMan", new TextureRegion(g3texture, 0, 600 - 3*58, 58, 58));
		sprites.put("paperGone", new TextureRegion(g3texture, 0, 600 - 4*58, 58, 58));
		sprites.put("healthBar", new TextureRegion(g3texture, 0, 600 - 5*58, 58, 58));
		
		TextDrawer.prepare();
	}
	
	public void reload()
	{
		try 
		{
			FileInputStream in = new FileInputStream("res/paperMan.properties");
			props.load(in);
			in.close();
			String temp = props.getProperty("firstTime", "true");
			if("true".equals(temp))
			{
				save("res/maze.properties");
				props.setProperty("paperHealth", "10");
				props.setProperty("sound", "true");
				props.setProperty("game3Score", "0");
				props.setProperty("firstTime", "false");
				props.setProperty("timeSinceHit", "0");
				save(temp);
			}
		} 
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}
}