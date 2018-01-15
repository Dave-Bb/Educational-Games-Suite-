package com.games.wordgames.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Robot {

	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private int direction;

	public static final int DOWN = 0;
	public static final int UP = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	public static final int TURN_AROUND = 4;
	private boolean setCompleate;
	private boolean instructionCompleate;
	
	private int setXChange;
	private int setYChange;
	private int instructionLenght;
	private int currentInstruction;
	
	private Vector2 directionV;
	
	private int defaultX;
	private int defaultY;
	
	private boolean levelCompleate;
	
	private int coins;
	
	private boolean run;
	private boolean drowning;
	private boolean directionChanged;
	
	private  Vector2[] offSet;
	
	
	public Robot(int x, int y){
		defaultX = x;
		defaultY = y;
		xPos = x;
		yPos = y;
		width = 159/3;
		height = 246/3;
		direction = RIGHT;
		instructionLenght=0;
		currentInstruction = 0;
		setCompleate = true;
		instructionCompleate = false;
		run = false;
		directionV = new Vector2();
		levelCompleate = false;
		coins = 0;
		drowning= false;
		directionChanged = false;
		initOffset();
	}
	
	private void initOffset(){
		offSet = new Vector2[4];
		offSet[0] = new Vector2(0,50);
		offSet[1] = new Vector2(0,-30);
		offSet[2] = new Vector2(50,0);
		offSet[3] = new Vector2(-5,0);
	}
	
	public void reset(){
		direction = RIGHT;
		xPos = defaultX;
		yPos = defaultY;
		directionV.x = 0;
		directionV.y = 0;
		run = false; 
		levelCompleate = false;
	}
	
	public void drown(){
		drowning = true;
	}
	
	public boolean isDrowning(){
		return drowning;
	}
	public void stopDrowning(){
		drowning = false;
	}
	public void moveRight(){
		xPos+=1;
	}
	public void moveLeft(){
		xPos-=1;
	}
	public void moveUp(){
		yPos+=1;
	}
	public void moveDown(){
		yPos-=1;
	}
	
	public void resetCoinCount(){
		coins = 0;
	}
	
	public int getCoinCount(){
		return coins;
	}
	
	public void addCoin(){
		coins+=1;
	}
	
	public void changeDirection(int d){
		switch(d){
		case 0:
		case 1:
		case 2:
		case 3:
			direction = d;
			directionChanged = false;
			break;
		case 4:
			if(!directionChanged){
				if(getDirection()==0){
					direction = UP;
					
				}else if(getDirection()==1){
					direction = DOWN;
				}else if(getDirection()==LEFT){
					direction = RIGHT;
				}else if(getDirection()==RIGHT){
					direction = LEFT;
				}
				directionChanged = true;
				break;
				
			}
			
		}
		
	}
	
	public int getDirection(){
		return direction;
	}

	public int getxPos() {
		return xPos;
	}
	
	public void setStart(int x, int y){
		defaultX = x;
		defaultY = y;
		reset();
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setDirection(int direction) {
		if(direction==4){
			directionV.x *= -1;
			directionV.y *= -1;
		}else{
			this.direction = direction;
		}
		
	}
	
	public void run(){
		run = true;
		
		
	}
	
	public void stopRun(){
		run = false;
	}
	
	public boolean levelCompleate(){
		return levelCompleate;
	}
	
	public void setLevelFinished(){
		run = false;
		levelCompleate = true;
	}
	
	public void update(){
		
		if(run){
			updateDirection();
			xPos+= directionV.x;
			yPos+= directionV.y;
		}
	}
	public void printPos(){
		
		System.out.println("X: "+getxPos()+" Y: "+getyPos());
		
	}
	private void updateDirection(){
		if(direction == 0){
			directionV.x = 0;
			directionV.y = -1;
		}if(direction == 1){
			directionV.x =0;
			directionV.y = 1;
		}if(direction ==2){
			directionV.x = -1;
			directionV.y = 0;
		}if(direction==3){
			directionV.x = 1;
			directionV.y = 0;
		}
	}
	public void setInstructionSet(int[][] set){
		instructionLenght = set.length-1;
		currentInstruction = 0;
		//setDirection=(set[0]);
		//setXChange = set[1];
		//setYChange = set[2];
		//setCompleate = false;
		instructionCompleate = false;
		
		
	}
	
	public Vector2 getOffSet(int i){
		return offSet[i];
	}
	
	public void instructionSet(){
		if(!instructionCompleate){
			if(setXChange!=0){
				if(setXChange>0){
					xPos +=1;
					setXChange-=1;
				}else if(setXChange<0){
					xPos-=1;
					setXChange +=1;
				}
				
			}if(setXChange >=-1&setXChange<=1 ){
				instructionCompleate = true;
				currentInstruction+=1;
				if(currentInstruction==instructionLenght){
					setCompleate = true;
				}
			}
		}
		//runInstructionSet();
		//Step 1
		//Direction Right
		//Move Right 385
		
		//Step 2
		//Direrction UP
		//Move Up 295
		
		//Step 3
		//Direction LEFT
		//Move left 385
		
		// xValu + or -
		// yValu + or -
		// Direction int 
		
	}
	
		

}
