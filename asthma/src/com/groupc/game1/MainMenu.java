package com.groupc.game1;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.groupc.game.GameObject;
import com.groupc.game.GameScreen;

public class MainMenu extends GameScreen
{
	Ball player;
	
	public MainMenu()
	{
		Assets.load();
		player = new Ball(50, 100, 32, 32);
		//player = new ObjectTest(50, 50, 50, 50, 0, 0, 0, 0, 50, 50);
	}

	@Override
	public void update(float deltaTime) {		
		while (Keyboard.next()) {
		    if (Keyboard.getEventKeyState()) {
		        if (Keyboard.getEventKey() == Keyboard.KEY_W) {
		        System.out.println("A Key Pressed");
		        //player.angle++;
		        player.velocity.set(0, 50);
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
		        player.velocity.set(-50, 0);
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
		        player.velocity.set(0, -50);
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
		        player.velocity.set(50, 0);
		        }
		    }
		    else {
		        if (Keyboard.getEventKey() == Keyboard.KEY_D) {
		        System.out.println("A Key Released");
		        }
		    }
		}
		player.update(deltaTime);
	}

	@Override
	public void present(float deltaTime)
	{
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
		Assets.ball.draw(player.rect);
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
