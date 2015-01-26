
public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Runner test = new Runner();
		while(!(test.isClosing))
		{
			test.run();
		}
	}

}
