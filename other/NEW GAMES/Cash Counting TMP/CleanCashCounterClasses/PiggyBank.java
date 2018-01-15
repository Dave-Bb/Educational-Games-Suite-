package com.games.wordgames.gameobjects;

import com.badlogic.gdx.math.Rectangle;

public class PiggyBank {
	private double totalInBank;
	private Rectangle rect;
	
	//This is class to keep track of how much cash has been dropped, and aslo if a click took place o
	// over the piggy bank. 
	public PiggyBank(){
		totalInBank = 0;
		rect = new Rectangle(400,160,260,260);
	}
	
	public Rectangle getRect(){
		return rect;
	}
	
	public boolean clickBox(int xPos, int yPos){
		return rect.contains(xPos,yPos);
	}
	
	public void deposit(double amount){
		totalInBank += amount;
	}
	
	public double getBalance(){
		return totalInBank;
	}

}
