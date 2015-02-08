
public class Resize 
{
	Runner run;
	public Resize(Runner run)
	{
		this.run=run;
	}
	
	//Gets the exact X location on the screen were it should be drawn
	public int locationX(int value)
	{
		double ratioX = (double)run.frame.getContentPane().getWidth() / (double)run.SCR_WIDTH;
		double ratioY =(double)run.frame.getContentPane().getHeight() / (double)run.SCR_HEIGHT;
		
		if(ratioY<ratioX)
		{
			int screenXPosition = (int)((run.frame.getContentPane().getWidth()-(ratioY*run.SCR_WIDTH))/2.0);
			return (int)(((double)value*ratioY)+screenXPosition);
		}
		else
		{
			return (int)((double)value*ratioX);
		}
	}
	
	//Gets the exact Y location on the screen were it should be drawn
	public int locationY(int value)
	{
		double ratioX = (double)run.frame.getContentPane().getWidth() / (double)run.SCR_WIDTH;
		double ratioY =(double)run.frame.getContentPane().getHeight() / (double)run.SCR_HEIGHT;
		
		if(ratioX<ratioY)
		{
			int screenYPosition = (int)((run.frame.getContentPane().getHeight()-(ratioX*run.SCR_HEIGHT))/2.0);
			return (int)((double)value*ratioX)+screenYPosition;
		}
		else
		{
			return (int)((double)value*ratioY);
		}
	}
	
	//Gets the width it should be drawn at
	public int width(int value)
	{
		double ratioX = (double)run.frame.getContentPane().getWidth() / (double)run.SCR_WIDTH;
		double ratioY =(double)run.frame.getContentPane().getHeight() / (double)run.SCR_HEIGHT;
		
		if(ratioY<ratioX)
		{
			return (int)((double)value*ratioY);
		}
		else
		{
			return (int)((double)value*ratioX);
		}
	}
	
	//Gets the height it should be drawn at
	public int height(int value)
	{
		double ratioX = (double)run.frame.getContentPane().getWidth() / (double)run.SCR_WIDTH;
		double ratioY =(double)run.frame.getContentPane().getHeight() / (double)run.SCR_HEIGHT;
		
		if(ratioX<ratioY)
		{
			return (int)((double)value*ratioX);
		}
		else
		{
			return (int)((double)value*ratioY);
		}
	}
	
	//Gets the fonts height it should be drawn at
	public int font(int value)
	{
		double ratioX = (double)run.frame.getContentPane().getWidth() / (double)run.SCR_WIDTH;
		double ratioY =(double)run.frame.getContentPane().getHeight() / (double)run.SCR_HEIGHT;
		
		if(ratioX<ratioY)
		{
			return (int)((double)value*ratioX);
		}
		else
		{
			return (int)((double)value*ratioY);
		}
	}
}
