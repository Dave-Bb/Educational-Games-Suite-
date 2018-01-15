package com.games.wordgames.gameobjects;


import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.utils.Array;

public class BambooBlasterQuestion {
	
	private int questionType;
	private int answerL1;
	private int answerL2;
	private int answerL3;
	private int randAns1;
	private int randAns2;
	private int randAns3;
	private Array<Integer> answers;
	
	private Random rand;
	
	private static final int EVEN_QUESTION = 0;
	private static final int ODD_QUESTION = 1;
	private static final int LESS_THAN_QUESTION = 2;
	private static final int GREATER_THAN_QUESTION = 3;
	private static final int MULTIPLES_OF = 4;
	private static final int LESS_THAN_SUM = 5;
	private static final int GREATER_THAN_SUM = 6;
	
	private String questionString;
	
	public BambooBlasterQuestion(){
		rand = new Random();
		//questionType = rand.nextInt(2);
		answers = new Array<Integer>();
		
		initQuestion();
		questionType = 0;
		
	}
	
	public void setNew(){
		
		System.out.println("QUEST TYPE:"+questionType);
		//answers.clear();
		
		initQuestion();
		
	}
	
	private void initQuestion(){
		//questionType = rand.nextInt(7);
		questionType = rand.nextInt(2);
		switch(questionType){
		case EVEN_QUESTION:
			initEvenQuestion();
			System.out.println("EVENQUESTIN");
			break;
		case ODD_QUESTION:
			initOddQuestion();
			System.out.println("ODD QUESTION");
			break;
		case LESS_THAN_QUESTION:
			initLessThanQuestion();
			System.out.println("ODD QUESTION");
			break;
		case GREATER_THAN_QUESTION:
			initGreaterThanQuestion();
			System.out.println("ODD QUESTION");
			break;
		case MULTIPLES_OF:
			initMultiplesOfQuestion();
			System.out.println("ODD QUESTION");
			break;
		case LESS_THAN_SUM:
			initLessThanSum();
			System.out.println("ODD QUESTION");
			break;
		case GREATER_THAN_SUM:
			initGreaterThanSum();
			System.out.println("ODD QUESTION");
			break;
		}
	}
	
	private void initLessThanSum(){
		int n1 = rand.nextInt(12);
		int n2 = rand.nextInt(12);
		int anchor = n1+n2;
		//int anchor = rand.nextInt(50);
		questionString = "Shoot the numbers LESS than\n "+n1+" + "+n2+" only!";
		answers.clear();
		answerL1 = rand.nextInt(anchor-1);
		answerL2 = rand.nextInt(anchor-1);
		answerL3 = rand.nextInt(anchor-1);
		randAns1 = rand.nextInt(anchor)+anchor/2;
		randAns2 = rand.nextInt(anchor)+anchor;
		randAns3 = rand.nextInt(anchor)+anchor;
		
		answers.add(answerL1);
		answers.add(answerL2);
		answers.add(answerL3);
		answers.add(randAns1);
		answers.add(randAns2);
		answers.add(randAns3);
		
		answers.shuffle();
		
		
	}
	
	private void initGreaterThanSum(){
		//questionType = EVEN_QUESTION;
		int n1 = rand.nextInt(12);
		int n2 = rand.nextInt(12);
		int anchor = n1+n2;
		//int anchor = rand.nextInt(50);
		questionString = "Shoot the numbers GREATER\n 	than "+n1+" + "+n2+" only!";
		answers.clear();
		answerL1 = rand.nextInt(anchor)+anchor;
		answerL2 = rand.nextInt(anchor)+anchor;
		answerL3 = rand.nextInt(anchor)+anchor;
		randAns1 = rand.nextInt(anchor)-1;
		randAns2 = rand.nextInt(anchor)-1;
		randAns3 = rand.nextInt(anchor)-1;
		
		answers.add(answerL1);
		answers.add(answerL2);
		answers.add(answerL3);
		answers.add(randAns1);
		answers.add(randAns2);
		answers.add(randAns3);
		
		answers.shuffle();
				
		
	}
	
	private void initMultiplesOfQuestion(){
		int anchor = rand.nextInt(12)+2;
		questionString = "Shoot the multiples of "+anchor+" only!";
		answers.clear();
		answerL1 = rand.nextInt(12)*anchor;
		answerL2 = rand.nextInt(12)*anchor;
		answerL3 = rand.nextInt(12)*anchor;
		randAns1 = rand.nextInt(100);
		randAns2 = rand.nextInt(100);
		randAns3 = rand.nextInt(100);
		
		answers.add(answerL1);
		answers.add(answerL2);
		answers.add(answerL3);
		answers.add(randAns1);
		answers.add(randAns2);
		answers.add(randAns3);
		
		answers.shuffle();
		
	}
	
	private void initLessThanQuestion(){
		//questionType = EVEN_QUESTION;
		
		int anchor = rand.nextInt(50)+1;
		questionString = "Shoot the numbers LESS\n 		than "+anchor+" only!";
		answers.clear();
		answerL1 = rand.nextInt(anchor-1);
		answerL2 = rand.nextInt(anchor-1);
		answerL3 = rand.nextInt(anchor-1);
		randAns1 = rand.nextInt(anchor)+anchor/2;
		randAns2 = rand.nextInt(anchor)+anchor;
		randAns3 = rand.nextInt(anchor)+anchor;
		
		answers.add(answerL1);
		answers.add(answerL2);
		answers.add(answerL3);
		answers.add(randAns1);
		answers.add(randAns2);
		answers.add(randAns3);
		
		answers.shuffle();
		
		
	}private void initGreaterThanQuestion(){
		//questionType = EVEN_QUESTION;
		int anchor = rand.nextInt(50);
		questionString = "Shoot the numbers GREATER\n 	than "+anchor+" only!";
		answers.clear();
		answerL1 = rand.nextInt(anchor)+anchor;
		answerL2 = rand.nextInt(anchor)+anchor;
		answerL3 = rand.nextInt(anchor)+anchor;
		randAns1 = rand.nextInt(anchor)-1;
		randAns2 = rand.nextInt(anchor)-1;
		randAns3 = rand.nextInt(anchor)-1;
		
		answers.add(answerL1);
		answers.add(answerL2);
		answers.add(answerL3);
		answers.add(randAns1);
		answers.add(randAns2);
		answers.add(randAns3);
		
		answers.shuffle();
		
		
	}
	
	private void initEvenQuestion(){
		//questionType = EVEN_QUESTION;
		questionString = "Shoot the EVEN numbers only!";
		answers.clear();
		answerL1 = getEvenNumber();
		answerL2 = getEvenNumber();
		answerL3 = getEvenNumber();
		randAns1 = getOddNumber();
		randAns2 = rand.nextInt(24);
		randAns3 = rand.nextInt(24);
		
		answers.add(answerL1);
		answers.add(answerL2);
		answers.add(answerL3);
		answers.add(randAns1);
		answers.add(randAns2);
		answers.add(randAns3);
		
		answers.shuffle();
		
		
	}
	
	private void initOddQuestion(){
		//questionType = ODD_QUESTION;
		questionString = "Shoot the ODD numbers only!";
		answers.clear();
		answerL1 = getOddNumber();
		answerL2 = getOddNumber();
		answerL3 = getOddNumber();
		randAns1 = getEvenNumber();
		randAns2 = rand.nextInt(24);
		randAns3 = rand.nextInt(24);
		
		answers.add(answerL1);
		answers.add(answerL2);
		answers.add(answerL3);
		answers.add(randAns1);
		answers.add(randAns2);
		answers.add(randAns3);
		
		answers.shuffle();
		
	}
	
	public int getNumber(int i){
		return answers.get(i);
	}
	
	public boolean checkAnswer(int i){
		boolean a = false;
		if(questionType == EVEN_QUESTION){
			if(i%2==0){
				a = true;
			}
		}else if(questionType == ODD_QUESTION){
			if(i%2!=0){
				a = true;
			}
		}
		
		return a;
	}
	
	private int getOddNumber(){
		int oddNumber = rand.nextInt(24)+1;
		while(oddNumber%2==0){
			oddNumber = rand.nextInt(24)+1;
		}
		return oddNumber;
	}
	
	private int getEvenNumber(){
		int evenNumber = rand.nextInt(24)+1;
		while(evenNumber%2!=0){
			evenNumber = rand.nextInt(24)+1;
		}
		return evenNumber;
	}
	
	public String getQuestion(){
		return questionString;
	}
	
	public static void main(String[] args){
		BambooBlasterQuestion q = new BambooBlasterQuestion();
		System.out.println(q.getQuestion());
		for(int j = 0; j<6;j++){
			int a = q.getNumber(j);
			if(q.checkAnswer(a)){
				System.out.print(a+":C,");
			}else if(!q.checkAnswer(a)){
				System.out.print(a+":X, ");
			}
		}
		
		
	}
	

}
