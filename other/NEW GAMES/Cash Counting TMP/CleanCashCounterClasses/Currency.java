package com.games.wordgames.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Currency {
	//This class is for the notes and coins,
	// It holds their value , clickRectangle, texture and if it is held 
	private double value;
	private Rectangle clickRectangle;
	private Texture texture; 
	private boolean isHeld;
	
	public Currency(double value, Texture tx, int rectX, int rectY, int rectW, int rectH){
		this.texture = tx;
		this.value = value;
		this.clickRectangle = new Rectangle(rectX, rectY, rectW, rectH);
		isHeld = false;
		
	}
	
	public Currency(){
		
	}
	
	
	//Random getters, setters and boolean check methods 
	
	public Texture getTexture(){
		return texture;
	}
	
	public double getValue(){
		return value;
	}
	
	public Rectangle getRect(){
		return clickRectangle;
	}
	
	//If the rectangle of a particular Currency has the mouse pos, then it must have been clicked
	// NOTE this method is only called in the handleInput after going through all the Currency object array 
	public boolean clickBox(int xPos, int yPos){
		return clickRectangle.contains(xPos,yPos);
	}
	
	public void holdCash(){
		isHeld = true;
	}
	
	public void dropCash(){
		isHeld = false;
	}
	
	public boolean checkHeld(){
		return isHeld;
	}

}
