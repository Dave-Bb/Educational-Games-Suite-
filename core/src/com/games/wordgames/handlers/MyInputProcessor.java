package com.games.wordgames.handlers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;


public class MyInputProcessor extends InputAdapter{
	
	
	public boolean keyDown(int k){
		//System.out.println(k);
		
		if(k == Keys.ESCAPE){
			MyInput.setKey(MyInput.ESCAPE, true);
		}
		if(k == Keys.DOWN){
			MyInput.setKey(MyInput.DOWN, true);
		}
		if(k == Keys.UP){
			MyInput.setKey(MyInput.UP, true);
		}
		if(k == Keys.ENTER){
			MyInput.setKey(MyInput.ENTER, true);
		}
		
		
		return true;
	}
	
	public boolean keyUp(int k){
		
		if(k == Keys.ESCAPE){
			MyInput.setKey(MyInput.ESCAPE, false);
		}
		if(k == Keys.DOWN){
			MyInput.setKey(MyInput.DOWN, false);
		}
		if(k == Keys.UP){
			MyInput.setKey(MyInput.UP, false);
		}
		if(k == Keys.ENTER){
			MyInput.setKey(MyInput.ENTER, false);
		}
		
		return true;
	}
	
	

}
