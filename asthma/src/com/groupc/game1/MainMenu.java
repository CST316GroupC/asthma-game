package com.groupc.game1;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.groupc.game.Camera;
import com.groupc.game.GameScreen;
import com.groupc.math.CollisionChecker;

public class MainMenu extends GameScreen
{
	Ball[] balls;
	Camera camera;
	
	public MainMenu()
	{
		Assets.load();
		balls = new Ball[]{new Ball(50, 100, 32, 32),new Ball(100, 100, 32, 32),new Ball(50, 50, 32, 32),
				new Ball(0, 100, 32, 32),new Ball(300, 100, 32, 32),new Ball(250, 100, 32, 32),
				new Ball(50, 0, 32, 32),new Ball(5, 10, 32, 32),new Ball(200, 100, 32, 32),
				new Ball(150, 0, 32, 32),new Ball(50, 10, 32, 32)};
		//player = new ObjectTest(50, 50, 50, 50, 0, 0, 0, 0, 50, 50);
		for(int i = 0; i < 10; i++)
		{
			balls[i].velocity.set(50, 25);
		}
		
		camera = new Camera(400, 400);
		camera.setCamera();
	}

	@Override
	public void update(float deltaTime) {		
		while (Keyboard.next()) {
		    if (Keyboard.getEventKeyState()) {
		        if (Keyboard.getEventKey() == Keyboard.KEY_W) {
		        System.out.println("A Key Pressed");
		        //player.angle++;
		        //player.velocity.set(0, 50);
		        camera.zoom *= 2;
		        }
		    }
		    else {
		        if (Keyboard.getEventKey() == Keyboard.KEY_W) {
		        System.out.println("A Key Released");
		        }
		    }
		    if (Keyboard.getEventKeyState()) {
		        if (Keyboard.getEventKey() == Keyboard.KEY_A) {
		        System.out.println("A Key Pressed");
		        //player.angle++;
		        //player.velocity.set(-50, 0);
		        camera.zoom /= 2;
		        }
		    }
		    else {
		        if (Keyboard.getEventKey() == Keyboard.KEY_A) {
		        System.out.println("A Key Released");
		        }
		    }
		    if (Keyboard.getEventKeyState()) {
		        if (Keyboard.getEventKey() == Keyboard.KEY_S) {
		        System.out.println("A Key Pressed");
		        //player.angle--;
		        //player.velocity.set(0, -50);
		        }
		    }
		    else {
		        if (Keyboard.getEventKey() == Keyboard.KEY_S) {
		        System.out.println("A Key Released");
		        }
		    }
		    if (Keyboard.getEventKeyState()) {
		        if (Keyboard.getEventKey() == Keyboard.KEY_D) {
		        System.out.println("A Key Pressed");
		        //player.angle--;
		        //player.velocity.set(50, 0);
		        }
		    }
		    else {
		        if (Keyboard.getEventKey() == Keyboard.KEY_D) {
		        System.out.println("A Key Released");
		        }
		    }
		}
		//player.update(deltaTime);
		for(int i = 0; i < 10; i++)
		{
			balls[i].update(deltaTime);
			if(!(CollisionChecker.RectInside(balls[i].bounds, Display.getWidth(), Display.getHeight())))
			{
				if(balls[i].bounds.lowerLeft.x < 0 || balls[i].bounds.lowerLeft.x + balls[i].bounds.width > Display.getWidth())
					balls[i].velocity.set(balls[i].velocity.x * -1, balls[i].velocity.y);
				if(balls[i].bounds.lowerLeft.y < 0 || balls[i].bounds.lowerLeft.y + balls[i].bounds.height > Display.getHeight())
					balls[i].velocity.set(balls[i].velocity.x, balls[i].velocity.y * -1);
			}
			for(int j = 0; j < 10; j++)
			{
				if(i != j)
				{
					if((CollisionChecker.RectToRect(balls[i].bounds, balls[j].bounds)))
					{
						balls[i].velocity.set(balls[i].velocity.x * -1, balls[i].velocity.y);
					}
				}
			}
		}
	}

	@Override
	public void present(float deltaTime)
	{
		camera.setCamera();
		Assets.mainmenu.bind();
		 // set the color of the quad (R,G,B,A)
             
        // draw quad
        GL11.glBegin(GL11.GL_QUADS);
        	GL11.glTexCoord2f(0, 1);
            GL11.glVertex2f(0, 0);
            GL11.glTexCoord2f(1, 1);
            GL11.glVertex2f(Display.getWidth(), 0);
            GL11.glTexCoord2f(1,0);
            GL11.glVertex2f(Display.getWidth(), Display.getHeight());
            GL11.glTexCoord2f(0,0);
            GL11.glVertex2f(0, Display.getHeight());
        GL11.glEnd();
        
        //player.draw();
        drawBall();
	}

	public void drawBall()
	{
		for(int i = 0; i < 10; i++)
		{
			Assets.ball.draw(balls[i].bounds);
		}
		
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
