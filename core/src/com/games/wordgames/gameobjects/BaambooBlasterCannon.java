package com.games.wordgames.gameobjects;

import com.badlogic.gdx.Gdx;
import com.games.wordgames.handlers.HelpMethods;
import com.games.wordgames.main.Game;

public class BaambooBlasterCannon {
	//public static final float VP_CANON_BASE_X = 
	//public static final float CANON_BASE_Y =600/6.5f;
	
	private float cannonTip_x;
	private float cannonTip_y;
	private int canon_angle;
	private float base_X;
	private float base_Y;
	
	public BaambooBlasterCannon(){
		canon_angle = 0;
		cannonTip_x = 0;
		cannonTip_y = 0;
		base_X = 400;
		base_Y= 600/6.5f;
		//viewPort_Base_x = (400/(Game.V_WIDTH/Game.getViewPort().width))+Game.getViewPort().x;
		//viewPort_Base_y= (600/6.5f*((Game.V_WIDTH/Game.getViewPort().width)))-Game.getViewPort().y;
	}
	
	public void updateCannonAngle(){
		calculateCannonAngle();
	}
	
	public int getCannonAngle(){
		return canon_angle;
	}
	
	public float getCannonTip_X(){
		return cannonTip_x;
	}
	
	public float getCannonTip_Y(){
		return cannonTip_y;
	}
	
	private void calculateCannonAngle(){
		//viewPort_Base_x
		//viewPort_Base_y
		double origonX = base_X-32;
		double origonY =((Gdx.graphics.getHeight())- base_Y);
		double opposite = origonY-Gdx.input.getY()+120;
		double adjasent = 0;
		boolean neg = true;
		if(Gdx.input.getX()>origonX){
			adjasent = Gdx.input.getX()- origonX-32;
			neg = false;
		}else{
			adjasent = origonX - Gdx.input.getX();
			
		}
		double hyp = Math.sqrt((adjasent*adjasent)+(opposite*opposite));
		
		canon_angle = (int) (Math.asin(adjasent/hyp)*100);
		
		if(!neg){
			canon_angle*=-1;
		}
		
		if(canon_angle<-45){
			canon_angle=-45;
		}
		if(canon_angle>45){
			canon_angle = 45;
		}
		
		cannonTip_x = (float) (base_X-((Math.sin(Math.toRadians(canon_angle)))*120))-20;
		cannonTip_y = base_Y+(float) ((Math.cos(Math.toRadians(canon_angle)))*120)-20;
		
	}
}
