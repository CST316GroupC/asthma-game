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
import com.groupc.game.Asset;
import com.groupc.game.TextureRegion;

public class Game2Assets extends Asset
{	
	public final static String FILENAME = "resources/game2/maze.properties";
	public Game2Assets()
	{
		super();
		load();
	}
	
	public void load()
	{
		reload();
		sprites = new Hashtable<String, TextureRegion>();
		
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("resources/game2/maze.png"));
			
			sprites.put("player", new TextureRegion(texture, 0, 512-32, 32, 32));
			sprites.put("wall", new TextureRegion(texture, 0, 512-32*2, 32, 32));
			sprites.put("grass", new TextureRegion(texture, 0, 512-32*3, 32, 32));
			sprites.put("dirt", new TextureRegion(texture, 0, 512-32*4, 32, 32));
			sprites.put("spikeDown", new TextureRegion(texture, 0, 512-32*5, 32, 32));
			sprites.put("spikeUp", new TextureRegion(texture, 32, 512-32*5, 32, 32));
			sprites.put("background", new TextureRegion(texture, 0, 512-(32*6), 32, 32));
			sprites.put("pit", new TextureRegion(texture, 0, 512-32*7, 32, 32));
			sprites.put("exit", new TextureRegion(texture, 0, 512-32*8, 32, 32));
			sprites.put("gem", new TextureRegion(texture, 0, 512-32*9, 32, 32));
						
		} catch (IOException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TextDrawer.prepare();
	}
	
	public void reload()
	{
		try 
		{
			FileInputStream in = new FileInputStream(FILENAME);
			props.load(in);
			in.close();
			String temp = props.getProperty("firstTime", "true");
			if("true".equals(temp))
			{
				props.setProperty("firstTime", "false");
				props.setProperty("x", "0");
				props.setProperty("y", "0");
				save(FILENAME);
			}
		} 
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}
}