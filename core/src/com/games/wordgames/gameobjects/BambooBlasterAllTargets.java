package com.games.wordgames.gameobjects;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

public class BambooBlasterAllTargets {
	
	private ArrayList<BambooBlasterTarget> targets;
	private ArrayList<BambooBlasterTarget> correctTargets;
	private ArrayList<BambooBlasterTarget> inCorrectTargets;
	
	private BambooBlasterQuestion question;
	private boolean allAnswerd;
	private boolean holdForNextQuestion; 
	private int playerScore;
	private int result;
	
	public BambooBlasterAllTargets(){
		question = new BambooBlasterQuestion();
		allAnswerd = true;
		generateTargetArray();
		holdForNextQuestion = true;
		playerScore = 0;
		result = -1;
		
	}
	
	private void generateTargetArray(){
		targets = new ArrayList<BambooBlasterTarget>();
		for(int i = 0;i<600;i+=100){
			targets.add(new BambooBlasterTarget(i+Gdx.graphics.getWidth()/6,400,60,130,question.getNumber(i/100)));
			
		}
		correctTargets = getCorrectTargets();
		inCorrectTargets = getIncorrectTargets();
	}
	
	public void resetTargets(){
		
		checkHitAnswers();
		generateTargetArray();
		question.setNew();
		holdForNextQuestion = false;
		playerScore =0;
		result = -1;
	}
	
	public int getScore(){
		int misses = 0;
		for(BambooBlasterTarget t: inCorrectTargets){
			if(t.isHit()){
				misses += 1;
			}
		}
		return correctTargets.size()-misses;
	}
	public void checkAllAnswered(){
		allAnswerd = true;
		/*
		 * for(BambooBlasterTarget t: targets){
			if(!t.isHit()){
				allAnswerd = false;
			}
		}
		 */
		
		for(BambooBlasterTarget t: correctTargets){
			if(!t.isHit()){
				allAnswerd = false;
			}
		}
		
	}
	
	private ArrayList<BambooBlasterTarget> getCorrectTargets(){
		ArrayList<BambooBlasterTarget> temp = new ArrayList<BambooBlasterTarget>();
		for(BambooBlasterTarget t: targets){
			if(question.checkAnswer(t.getTargetNumber())){
				temp.add(t);
			}
		}
		
		return temp;
		
	}
	
	private ArrayList<BambooBlasterTarget> getIncorrectTargets(){
		ArrayList<BambooBlasterTarget> temp = new ArrayList<BambooBlasterTarget>();
		for(BambooBlasterTarget t: targets){
			if(!question.checkAnswer(t.getTargetNumber())){
				temp.add(t);
			}
		}
		
		return temp;
	}
	
	public int getPassFail(){
		if(checkPassFail()){
			return 1;
		}else{
			return 0;
		}
	}
	
	public boolean checkPassFail(){
		for(BambooBlasterTarget t: targets){
			if(t.isHit()){
				if(!question.checkAnswer(t.getTargetNumber())){
					return false;
				}
			}
		}
		int notHit = 0;
		for(BambooBlasterTarget t:targets){
			if(!t.isHit()){
				notHit +=1;
			}
			if(notHit ==6){
				return false;
			}
		}
		return true;
	}
	
	public void checkHitAnswers(){
		int correctOptions = 0;
		for(BambooBlasterTarget t: targets){
			if(question.checkAnswer(t.getTargetNumber())){
				correctOptions +=1;
			}
			if(t.isHit()){
				if(question.checkAnswer(t.getTargetNumber())){
					
				}
			}
		}
		System.out.println("CORRECT OPTIONS: "+correctOptions);
	}
	
	public boolean waitForHold(){
		return holdForNextQuestion;
	}
	
	public void breakHold(){
		holdForNextQuestion = false;
	}
	
	public void holdForNextQuestion(){
		holdForNextQuestion = true;
	}
	public boolean getAllAnsweredStatus(){
		checkAllAnswered();
		return allAnswerd;
	}
	
	public ArrayList<BambooBlasterTarget> getTargetArray(){
		return targets;
	}
	
	public boolean checkHit(Coconut cocoNut){
		for(BambooBlasterTarget t:targets){
			if(!t.isHit()){
				t.checkHit(cocoNut.getXpos(), cocoNut.getYpos());
				if (t.isHit()){
					return true;
				}
			}
		}
		return false;
	}
	
	public String getQuestion(){
		return question.getQuestion();
	}
	
	public BambooBlasterQuestion getQuestionObj(){
		return question;
	}
	
	

}
