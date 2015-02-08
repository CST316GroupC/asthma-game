
public class Resize 
{
	Runner run;
	public Resize(Runner run)
	{
		this.run=run;
	}
	public int locationX(int value)
	{
		double ratioX = (double)run.getContentPane().getWidth() / (double)run.SCR_WIDTH;
		double ratioY =(double)run.getContentPane().getHeight() / (double)run.SCR_HEIGHT;
		
		if(ratioY<ratioX)
		{
			int screenXPosition = (int)((run.getContentPane().getWidth()-(ratioY*run.SCR_WIDTH))/2.0);
			return (int)(((double)value*ratioY)+screenXPosition);
		}
		else
		{
			return (int)((double)value*ratioX);
		}
	}
	
	public int locationY(int value)
	{
		double ratioX = (double)run.getContentPane().getWidth() / (double)run.SCR_WIDTH;
		double ratioY =(double)run.getContentPane().getHeight() / (double)run.SCR_HEIGHT;
		
		if(ratioX<ratioY)
		{
			int screenYPosition = (int)((run.getContentPane().getHeight()-(ratioX*run.SCR_HEIGHT))/2.0);
			return (int)((double)value*ratioX)+screenYPosition;
		}
		else
		{
			return (int)((double)value*ratioY);
		}
	}
	
	public int width(int value)
	{
		double ratioX = (double)run.getContentPane().getWidth() / (double)run.SCR_WIDTH;
		double ratioY =(double)run.getContentPane().getHeight() / (double)run.SCR_HEIGHT;
		
		if(ratioY<ratioX)
		{
			return (int)((double)value*ratioY);
		}
		else
		{
			return (int)((double)value*ratioX);
		}
	}
	
	public int height(int value)
	{
		double ratioX = (double)run.getContentPane().getWidth() / (double)run.SCR_WIDTH;
		double ratioY =(double)run.getContentPane().getHeight() / (double)run.SCR_HEIGHT;
		
		if(ratioX<ratioY)
		{
			return (int)((double)value*ratioX);
		}
		else
		{
			return (int)((double)value*ratioY);
		}
	}
	public int font(int value)
	{
		double ratioX = (double)run.getContentPane().getWidth() / (double)run.SCR_WIDTH;
		double ratioY =(double)run.getContentPane().getHeight() / (double)run.SCR_HEIGHT;
		
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
