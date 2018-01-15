package com.games.wordgames.gameobjects;

import com.badlogic.gdx.math.Rectangle;

public class RobotLogicGroundBloc {
	
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	
	private Rectangle rect;
	
	private int currentArrow;
	
	private int levelNumber;
	private boolean hasCoin;
	private boolean hasHole;
	private boolean draw;
	private boolean hasArrow;
	private boolean isFinishLine;
	private boolean isStart;
	private boolean arrowUsed;
	
	public RobotLogicGroundBloc(int x, int y){
		xPos = x;
		yPos = y;
		draw = false;
		width = 64;
		height = 72;
		rect = new Rectangle(xPos,yPos,width,height);
		hasArrow = false;
		currentArrow = 0;
		levelNumber = 0;
		isFinishLine = false;
		hasCoin = false;
		hasHole = false;
		isStart = false;
		arrowUsed = false;
	}
	
	public void setStart(){
		isStart = true;
	}
	
	public void useArrow(){
		arrowUsed = true;
	}
	
	public void resetArrow(){
		arrowUsed = false;
	}
	
	public boolean arrowUsed(){
		return arrowUsed;
	}
	
	public boolean isStart(){
		return isStart;
	}
	
	public void removeStart(){
		isStart = false;
	}
	
	public void makeHole(){
		hasHole = true;
	}
	
	public void fullHole(){
		hasHole = false;
	}
	
	public boolean hasHole(){
		return hasHole;
	}
	
	public void setLevelNumber(int i){
		levelNumber = i;
	}
	
	public String getLevelString(){
		return "Level "+levelNumber;
	}
	
	public boolean hasArrow(){
		return hasArrow;
	}
	
	
	public void setArrow(int i){
		hasArrow = true;
		currentArrow = i;
		//arrowUsed = false;
	}
	
	public void removeArrow(){
		hasArrow = false;
		
	}
	
	public int getCurrentArrow(){
		return currentArrow;
	}
	
	public boolean boxClicked(int x, int y){
		return rect.contains(x, y);
	}
	
	public boolean robotRectContact(Rectangle r){
		return rect.contains(r);
	}

	public int getxPos() {
		return xPos;
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

	public boolean isDraw() {
		return draw;
	}

	public void setDraw(boolean draw) {
		this.draw = draw;
	}
	
	public boolean isFinish(){
		return isFinishLine;
	}
	
	public void setFinish(){
		isFinishLine = true;
	}
	
	public void removeFinish(){
		isFinishLine = false;
	}
	
	public void addCoin(){
		hasCoin = true;
		
	}
	
	public void pickUpCoin(){
		hasCoin = false;
	}
	
	public boolean hasCoin(){
		return hasCoin;
	}
}
