package com.games.wordgames.gameobjects;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Letters {
	
	private ShapeRenderer shapeRenderer;
	private Vector2 blockPosition;
	private float letterXPosiition;
	
	private final String[] alphabetArray  = {"A","B","C","D",
			"E","F","G","H","I",
			"J","K","L","M","N",
			"O","P","Q","R","S",
			"T","U","V","W","X",
			"Y","Z"};
	private Random random;
	private String blockLetter;
	private Color color;
	private float xVel;
	private float yVel;
	
	private final int[] letterX = {50,140,230,320,210,300,390,480,570,660,720};
	
	public Letters(float x, float y){
		//(random.nextInt((8))*60)+22
		
		random = new Random();
		blockPosition = new Vector2(letterX[random.nextInt(letterX.length)],-5);
		blockLetter = nextLetter();
		color = new Color(random.nextFloat(),random.nextFloat(),random.nextFloat(),1);
		yVel = -1;

		letterXPosiition = setLetterPosition(blockLetter);
		
	}
	
	private String nextLetter(){
		int r = random.nextInt(25);
		
		return alphabetArray[r];
	}
	
	public String getString(){
		return blockLetter;
	}
	
	public Vector2 getPosition(){
		return blockPosition;
	}
	
	public Color getColor(){
		return color;
	}
	
	public void updatePosition(){
		
		blockPosition.y -= yVel;
		
	}
	
	public boolean offScreen(){
		return blockPosition.y>=Gdx.graphics.getHeight()+30;
		
	}

	private float setLetterPosition(String letter){
		float letterPos = 0.0f;
		if("MUHOWNQG".contains(letter)){
			letterPos = this.blockPosition.x-5;
		}else if("ILJST".contains(letter)){
		    letterPos = this.blockPosition.x+5;
        }else{
		    letterPos = this.blockPosition.x;
        }

        return letterPos;

	}

	public float getLetterXPosiition(){
	    return letterXPosiition;
    }

}
