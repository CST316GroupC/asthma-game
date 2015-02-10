package com.groupc;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioImpl;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;


public class AudioPlayer 
{
	Audio music;
	ArrayList<Audio> sounds;
	
	public void playMusic(boolean looping)
	{
		music.playAsMusic(1.0f, 1.0f, looping);
	}
	
	public void pauseMusic()
	{
		AudioImpl.pauseMusic();
	}
	
	public void resume()
	{
		AudioImpl.restartMusic();
	}
	
	public boolean loadSong(String filename)
	{
		try {
			music = AudioLoader.getStreamingAudio(getFileType(filename), ResourceLoader.getResource(filename));
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addSound(String filename)
	{
		try {
			sounds.add(AudioLoader.getStreamingAudio(getFileType(filename), ResourceLoader.getResource(filename)));
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void playSound(int index)
	{
		sounds.get(index).playAsSoundEffect(1.0f, 1.0f, false);
	}
	
	public String getFileType(String filename)
	{
		String type;
		type = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
		type = type.toUpperCase();
		System.out.println(type);
		return type;
	}

}
