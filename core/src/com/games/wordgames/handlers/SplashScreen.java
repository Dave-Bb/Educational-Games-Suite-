package com.games.wordgames.handlers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class SplashScreen {
	private String instructions;
	private Texture background;
	private Vector2 locationOnScreen;
	private boolean start;
	
	public SplashScreen(Texture bg, String inst, float xPos, float yPos){
		instructions = inst;
		background = bg;
		locationOnScreen= new Vector2(xPos,yPos);
		
		start = false;
	}
	
	public void startGame(){
		start = true;
	}
	
	public void stop(){
		start = false;
	}
	public boolean isStart(){
		return start;
	}

	public boolean isSplashScreen(){
		if(start){
			return false;
		}
		return true;
	}
	
	public Texture getBackground(){
		return background;
	}
	
	public String getInstructions(){
		return instructions; 
	}
	
	public Vector2 getPos(){
		return locationOnScreen;
	}
	

}
