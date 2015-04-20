package com.groupc.screens;

/*
 * Author(s):		Team C
 * Course: 			CST 316 Spring
 * Instructor:		Dr. Gary
 * Date Changed:	4/19/2015
 * 
 * Description:		TokenPanel displays useful information about a users tokens
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import org.lwjgl.opengl.Display;

import com.groupc.Runner;
import com.groupc.math.Resize;
import com.groupc.math.Tokens;


public class TokenPanel extends JPanel 
{
	//Variables
	private Runner run;
	private final Resize resize;
	//Tokens 	tokens;
	
	//Display Elements
	JPanel  	tokenBox  	= new JPanel();
	JLabel 		tokenText	= new JLabel("0 x",SwingConstants.RIGHT);
	JLabel 		tokenPic	= new JLabel();
	
	private ImageIcon 	tokenImage 	= new ImageIcon("resources/interface/Token.png");

	public TokenPanel(Runner run) 
	{
		this.run = run;
		resize   = new Resize(run);
		
		//Set colors
		this.setOpaque(false);
		tokenBox.setBackground(Color.LIGHT_GRAY);
		
		//add things to the panel
		this.add(tokenText);
		this.add(tokenPic);		
		this.add(tokenBox);		
		this.setLayout(null);
	}
	
	public void redrawUpdate()
	{
		//main bounding box
		this.setBounds(resize.locationX(350), resize.locationY(450), resize.width(150), resize.height(40));
		
		//tokenBox
		//tokenBox.setBounds(resize.width(0), resize.height(0), resize.width(150), resize.height(40));
		
		//tokenText
		tokenText.setBounds(resize.width(0), resize.height(5), resize.width(100), resize.height(30));
		tokenText.setFont(new Font(tokenText.getFont().getFontName(),tokenText.getFont().getStyle(), resize.font(20)));
		
		//tokenPic
		tokenPic.setBounds(resize.width(100), resize.height(0), resize.width(40), resize.height(40));
		tokenPic.setIcon(new ImageIcon(tokenImage.getImage().getScaledInstance(resize.width(40), resize.height(40), java.awt.Image.SCALE_SMOOTH)));
	}
	
	public void RefreshCount(int count)
	{
		tokenText.setText(count+" x");
	}
}
