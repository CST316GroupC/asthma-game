package com.groupc.game;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import org.newdawn.slick.opengl.Texture;

public abstract class Asset
{
	public static Texture texture;
	public static Hashtable<String, TextureRegion> sprites;	
	protected static Properties props;
		
	protected abstract void load();
		
	public abstract void reload();
	
	public Asset()
	{
		props = new Properties();
	}
		
	public TextureRegion getImage(String img)
	{
		return sprites.get(img);
	}
		
	public Texture getTexture()
	{
		return texture;
	}
	
	public Properties getProps()
	{
		return props;
	}
		
	public void setProps(String key, String value)
	{
		props.setProperty(key, value);
	}
	
	public void save(String fileName)
	{
		FileOutputStream out;
		try {
			out = new FileOutputStream(fileName);
			props.store(out, "---No Comment---");
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
}

