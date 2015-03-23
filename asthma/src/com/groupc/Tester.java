package com.groupc;

import java.io.File;

public class Tester {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException////////
	{
		System.setProperty("org.lwjgl.librarypath", new File("natives/windows").getAbsolutePath());
		Runner test = new Runner();
		while(!(test.isClosing))
		{
			test.run();
		}
	}

}
