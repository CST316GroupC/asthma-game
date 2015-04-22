package com.groupc;

import java.io.File;

import org.lwjgl.LWJGLException;

public final class Tester
{

	private Tester()
	{
	}
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws LWJGLException 
	 */
	public static void main(String[] args) throws InterruptedException
	{
		System.setProperty("org.lwjgl.librarypath", new File("natives/windows").getAbsolutePath());
		Runner test = new Runner();
		while(!(test.isClosing))
		{
			test.run();
		}
	}

}
