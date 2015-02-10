package com.groupc.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.groupc.AudioPlayer;

public class SoundTest {
	AudioPlayer player;
	String[] files = {"AMemoryAway.ogg", "badfile"};
	@Before
	public void setUp()
	{
		player = new AudioPlayer();
	}
	
	@Test
	public void fileTypetest() 
	{
		
		fail("Not yet implemented");
	}

}
