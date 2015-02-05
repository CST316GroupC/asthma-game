import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.openal.Audio;
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
	
	public boolean loadSong(String filename)
	{
		try {
			music = AudioLoader.getStreamingAudio("OGG", ResourceLoader.getResource(filename));
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
			sounds.add(AudioLoader.getStreamingAudio("OGG", ResourceLoader.getResource(filename)));
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

}
