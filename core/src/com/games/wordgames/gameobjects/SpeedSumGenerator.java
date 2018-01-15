package com.games.wordgames.gameobjects;

import java.util.Random;

public class SpeedSumGenerator {
	
	private Random rand;
	private int term1;
	private int term2;
	private int term3;
	private int term4;
	
	private int answer;
	private String questionString;
	public static final String[] operators = {"+","-","x","÷","="};
	private static final int ADDITION = 0;
	private static final int SUBTRACTION = 1;
	private static final int MULTIPLICATION = 2;
	private static final int DIVISION = 3;
	
	public SpeedSumGenerator(){
		rand = new Random();
		term1 = 0;
		term2 = 0;
		term3 = 0;
		term4 = 0;
		answer = 0;
		questionString = "";
		
		
	}
	
	public void generateSum(int level){
		switch(level){
		case 1:
			generateLevel1();
			break;
		case 2:
			generateLevel2();
			break;
		case 3:
			generateLevel3();
			break;
		}
	}
	
	private void generateLevel1(){
		String operator = "";
		int randQuestionType = rand.nextInt(2);
		switch(randQuestionType){
		case ADDITION:
			operator = operators[0];
			term1 = rand.nextInt(12);
			term2 = rand.nextInt(12);
			answer = term1+term2;
			break;
		case SUBTRACTION:
			operator = operators[1];
			term1 = rand.nextInt(24);
			term2 = rand.nextInt(24);
			if(term1<term2){
				int temp = term1;
				term1 = term2;
				term2 = temp;
			}
			answer = term1-term2;
			break;
		}
		
		questionString = term1+" "+operator+" "+term2+" "+operators[4];
		
	}
	
	private void generateLevel2(){
		String operator = "";
		int randQuestionType = rand.nextInt(4);
		switch(randQuestionType){
		case ADDITION:
			operator = operators[0];
			term1 = rand.nextInt(24);
			term2 = rand.nextInt(24);
			answer = term1+term2;
			break;
		case SUBTRACTION:
			operator = operators[1];
			term1 = rand.nextInt(50);
			term2 = rand.nextInt(30);
			if(term1<term2){
				int temp = term1;
				term1 = term2;
				term2 = temp;
			}
			answer = term1-term2;
			break;
		case MULTIPLICATION:
			operator = operators[2];
			term1 = rand.nextInt(6);
			term2 = rand.nextInt(3);
			answer = term1*term2;
			break;
		case DIVISION:
			operator = operators[3];
			term1 = rand.nextInt(6);
			term2 = rand.nextInt(4);
			while(term2==0){
				term2 = rand.nextInt(4);
			}
			answer = term1;
			term1 = term1*term2;
			break;
		}
		
		questionString = term1+" "+operator+" "+term2+" "+operators[4];
		
	}

	private void generateLevel3(){
		String operator = "";
		int randQuestionType = rand.nextInt(4);
		switch(randQuestionType){
		case ADDITION:
			operator = operators[0];
			term1 = rand.nextInt(100);
			term2 = rand.nextInt(100);
			answer = term1+term2;
			break;
		case SUBTRACTION:
			operator = operators[1];
			term1 = rand.nextInt(100);
			term2 = rand.nextInt(100);
			if(term1<term2){
				int temp = term1;
				term1 = term2;
				term2 = temp;
			}
			answer = term1-term2;
			break;
		case MULTIPLICATION:
			operator = operators[2];
			term1 = rand.nextInt(12);
			term2 = rand.nextInt(12);
			answer = term1*term2;
			break;
		case DIVISION:
			operator = operators[3];
			term1 = rand.nextInt(10);
			term2 = rand.nextInt(8);
			while(term2==0){
				term2 = rand.nextInt(4);
			}
			answer = term1;
			term1 = term1*term2;
			break;
		}
		
		questionString = term1+" "+operator+" "+term2+" "+operators[4];
	
	}
	
	public String getSumString(){
		return questionString;
	}

	public boolean checkAnswer(int a){
		return (a==answer);
	}
	

}
