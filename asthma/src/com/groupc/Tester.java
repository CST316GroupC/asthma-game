package com.groupc;

public class Tester {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException
	{
		Runner test = new Runner();
		while(!(test.isClosing))
		{
			test.run();
		}
	}

}
