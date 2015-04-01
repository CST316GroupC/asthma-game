package com.groupc.game;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import com.groupc.math.Rectangle;
import com.groupc.math.Vector;

/**
 * The TextureRegion class does all the hard work for you. It will calculate
 * the correct texture coords so that you can draw a texture onto a quad without
 * having to calculate the texture coords yourself. Heck it even draws it for you!
 * @author Edwin Avalos
 *
 */
public class TextureRegion 
{
	//Variables
	Vector[] points;
	Texture text;
	
	//Constructor
	
	/**
	 * Calculates the correct texture coordinates from topleft clockwise by passing
	 * the physical pixel location and size of a Rectangle in the image 
	 * @param text - Texture that is being used
	 * @param x - lowerleft x coordinate in the part of image wanted
	 * @param y - lowerleft y coordinate in the part of image wanted
	 * @param width - width of rectangular piece in the image wanted
	 * @param height - height of rectanular piece in the image wanted
	 * @return The 4 texture coordinates required to place texture onto a quad
	 */
	public TextureRegion(Texture text, float x, float y, float width, float height)
	{	
		this.text = text;
		points = new Vector[4];
		float textWidth = text.getImageWidth();
		float textHeight = text.getImageHeight();
		
		//topleft
		points[0] = new Vector((x+1)/textWidth, 1 - (y+height)/textHeight);
		//topright
		points[1] = new Vector((x+width-1)/textWidth, 1 - (y+height)/textHeight);
		//botright
		points[2] = new Vector((x+width-1)/textWidth, 1 - (y/textHeight));
		//botleft
		points[3] = new Vector((x+1)/textWidth, 1 - (y/textHeight));	
	}
	
	/**
	 * Using the previously calculated points, draws the TextureRegion in the 
	 * desired position on screen.
	 * @param rect - Rectangle of GameObject being drawn
	 */
	public void draw(Rectangle rect)
	{
		//Get the variables needed from rect
		float x = rect.lowerLeft.x;
		float y = rect.lowerLeft.y;
		float w = rect.width;
		float h = rect.height;
		
		//Get OpenGL ready to use a texture
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		text.bind(); // or GL11.glBind(texture.getTextureID());
		

		//Begin drawing the quad and placing texture over
		GL11.glBegin(GL11.GL_QUADS);
			//topleft
			GL11.glTexCoord2f(points[0].x, points[0].y);
		    GL11.glVertex2f(x, y + h);
		    //topright
		    GL11.glTexCoord2f(points[1].x, points[1].y);
		    GL11.glVertex2f(x + w, y + h);
		    //botright
		    GL11.glTexCoord2f(points[2].x, points[2].y);
		    GL11.glVertex2f(x + w, y);
		    //botleft
		    GL11.glTexCoord2f(points[3].x, points[3].y);
		    GL11.glVertex2f(x, y);
		GL11.glEnd();
	}
}
