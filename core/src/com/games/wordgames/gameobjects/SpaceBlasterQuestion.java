package com.games.wordgames.gameobjects;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.games.wordgames.handlers.HelpMethods;

public class SpaceBlasterQuestion {
	
	private String question;
	private String answer;
	
	private boolean isCorrect;
	
	private Random rand;
	private int questionTypeInt;
	
	public static final int VERBS = 0;
	public static final int NOUNS = 1;
	public static final int PRONOUNS = 2;
	public static final int ADVERBS = 3;
	public static final int ADJECTIVES = 4;
	public static final int CONJUNCTIONS = 5;
	public static final int PREPOSITIONS = 6;
	
	public static final int lvl1Qt = 2;
	public static final int lvl2Qt = 2;
	public static final int lvl3Qt = 3;
	public static final int lvl4Qt = 3;
	public static final int lvl5Qt = 3;
	public static final int lvl6Qt = 4;
	public static final int lvl7Qt = 4;
	public static final int lvl8Qt = 5;
	public static final int lvl9Qt = 5;
	public static final int lvl10Qt = 6;
	
	public static final int[] levelQuestionTypes = {lvl1Qt,lvl2Qt,lvl3Qt,lvl4Qt,lvl5Qt,lvl6Qt,lvl7Qt,lvl8Qt,lvl9Qt,lvl10Qt}; 
	
	private int currentLevel;
	
	private int questionNumber;
	private String randWord;
	
	private Array<Texture> questionTypeImages;
	
	public static final String[] questionType = {"Verbs","Nouns","Pronouns",
			"Adverbs","Adjectives","Conjunctions","Prepositions"};
	
	
	
	public static final String wordClassPath = "textFiles/spaceBlasterWords/";
	//public static final String wordClassPath = "textFiles/spaceBlasterWords/";
	public static final Array<String> verbListOne = HelpMethods.getWords(wordClassPath+"verbs1.txt");
	//public static final Array<String> verbListOne = HelpMethods.splitWords("run play eat sleep was where am is are work sleep walk sing cry ran sit cut");
	//public static final Array<String> verbListTwo = HelpMethods.splitWords("create fishing swimming runnings sitting laughing eating writing listening "
													//+ "speaking build building driving flying tie drink wash set open");
	//public static final Array<String> verbListThree = HelpMethods.splitWords("confuse belive destroy celebrate calculating solving moving unwind");
	
	public static final Array<String> verbListTwo = HelpMethods.getWords(wordClassPath+"verbs2.txt");
	public static final Array<String> verbListThree = HelpMethods.getWords(wordClassPath+"verbs3.txt");
	
	
	
	public static final Array<String> nounListOne = HelpMethods.getWords(wordClassPath+"nouns1.txt");
	public static final Array<String> nounListTwo = HelpMethods.getWords(wordClassPath+"nouns2.txt");
	public static final Array<String> nounListThree = HelpMethods.getWords(wordClassPath+"nouns2.txt");
	
	
//	public static final Array<String> pronounListOne = HelpMethods.splitWords("he she her his ours their mine me");
	//public static final Array<String> pronounListTwo = HelpMethods.splitWords("these everything many group myself themselves who what");
	//public static final Array<String> pronounListThree = HelpMethods.splitWords("these everything many group myself themselve who what");
	
//	public static final Array<String> adverbListOne = HelpMethods.splitWords("fast slow hard softly happily quickly slowly accurately willfully badly gracefully surprisingly nicely thoughtfully");
	//public static final Array<String> adverbListTwo = HelpMethods.splitWords("awkwardly finally kindly lazily hourly exactly perfectly loosely faithfully frequently seriously madly politely");
	//public static final Array<String> adverbListThree = HelpMethods.splitWords("awkwardly finally kindly lazily hourly exactly perfectly loosely faithfully frequently seriously madly politely");

//	public static final Array<String>adjectivesListOne = HelpMethods.splitWords("funny furry hard soft clean three four lots small big white yellow messy neat happy sad silly crazy");
	//public static final Array<String>adjectivesListTwo = HelpMethods.splitWords("most cooles messiest mischievous lamorous good-tempered aggressive brave delightful faithful adorable gentle massive skinny cheerful amused delightful determined gentle proud successful nervous embarrassed depressed disgusting handsome");
	
	//public static final Array<String> conjunctionListOne = HelpMethods.splitWords("and or for nor but yet so while before whether when after as if provided in that even if as far as since either or both so as however therefor otherwise in fact indeed still afer all likewise thus");
	//public static final Array<String> prepositionListOne = HelpMethods.splitWords("in on at beside next to under over next as off around against apart from through along within behind between before beneath beyond underneath except during unlike into toward despite throughout");
	//public static final Array<String> nounListOne = HelpMethods.splitWords("Bird Hat House Car Bike Shoe Cup Bag Box Bed Stick Man Ice Dog Cat Paper Pen Pot Pan Fan Book Bus Desk Table Baby Fish Bird Water Milk Pants");
	//public static final Array<String> nounListTwo = HelpMethods.splitWords("Fireman Fairies Books President Hous  Engine Toothbrush Museum Blender Computer Shoelace Bowl Kittens Rabbits Horses Policeman Bicycle Motorbike peaches Bananas Notebooks Eskimo");
	//public static final Array<String> nounListThree = HelpMethods.splitWords("Mailbox Oatmeal Cactus Marble Pavement Trousers Donkeys Machine Lamp Shelve Cupboard Plastic Guitar Piano Scissors Calculator");
	
	public static final Array<String> pronounListOne = HelpMethods.getWords(wordClassPath+"pronouns2.txt");
	public static final Array<String> pronounListTwo = HelpMethods.getWords(wordClassPath+"pronouns2.txt");
	public static final Array<String> pronounListThree = HelpMethods.getWords(wordClassPath+"pronouns2.txt");
	
	public static final Array<String> adverbListOne = HelpMethods.getWords(wordClassPath+"adverbs1.txt");
	public static final Array<String> adverbListTwo = HelpMethods.getWords(wordClassPath+"adverbs2.txt");
	public static final Array<String> adverbListThree = HelpMethods.getWords(wordClassPath+"adverbs3.txt");
	
	public static final Array<String>adjectivesListOne = HelpMethods.getWords(wordClassPath+"adjictives1.txt");
	public static final Array<String>adjectivesListTwo = HelpMethods.getWords(wordClassPath+"adjictives2.txt");
	
	public static final Array<String> conjunctionListOne = HelpMethods.getWords(wordClassPath+"conjunctions.txt");
	
	public static final Array<String> prepositionListOne = HelpMethods.getWords(wordClassPath+"prepositions.txt");
	
	
	
	public static final String[] verbList = {"Running","Swimming","Playing",
			"Laughing","Walking","Fishing",	"Writing","Reading","Looking",
			"Laugh","Sing","Clap","Speak","Run","Cook","Build"};
	
	public static final String[] nounList = {"Cat","Dog","Cow",
			"Shoe","Car","House","Phone","Pen","Box","Kite",
			"Shield","Mirror","Toy","Cup","Table","Grass",
			"Piano","Wheel","Book","Plate","Jar","Wire","Computer"};

	public static final String[] pronounsList = {"All","Another","Any","Anybody","Anyone","Anything","Everybody", 
			"Everyone","Everything","Few","He","Her","Hers","Herself","Him",
			"Himself","His","Little","Many","Me","Mine","More","Most","Much", 
			"My","Myself","Neither","Nobody","None","Nothing","Several","She", 
			"Some","Somebody","Someone"};
																																																																																										
	
	public static final String[][] answerSets = {verbList,nounList,pronounsList};
	
	private Array<String> allVerbs;
	private Array<String> allNouns;
	private Array<String> allAdverbs;
	private Array<String> allPronouns;
	private Array<String> allAdjectives;																																																																																																	
	private Array<String> allConjunctions;
	private Array<String> allPrepositions;
	
	private Array<String> lvl1WordBank;
	private Array<String> lvl2WordBank;
	private Array<String> lvl3WordBank;
	private Array<String> lvl4WordBank;
	private Array<String> lvl5WordBank;																																																																																																	
	private Array<String> lvl6WordBank;
	private Array<String> lvl7WordBank;
	private Array<String> lvl8WordBank;																																																																																																	
	private Array<String> lvl9WordBank;
	private Array<String> lvl10WordBank;
	
	
	
	
	private Array<Array<String>> allWordArrays;
	private Array<Array<String>> alllevelBanks;
	
	
	
	
	public SpaceBlasterQuestion(){
		rand = new Random();
		
		init();
		
	}
	
	private void init(){
		initQuestionTypeimg();
		initWordBank();
		currentLevel = 0;
		questionTypeInt = rand.nextInt(levelQuestionTypes[currentLevel]);
		question = questionType[questionTypeInt];
		questionNumber = 0;
		randWord = getRandomWord();
		
		
		
		
	}
	
	public void nextLevel(){
		if(currentLevel<9){
			currentLevel+=1;
		}
	}
	
	private void initWordBank(){
		
		allWordArrays = new Array<Array<String>>();
		alllevelBanks = new Array<Array<String>>();
		
		lvl1WordBank= new Array<String>();
		lvl2WordBank= new Array<String>();
		lvl3WordBank= new Array<String>();
		lvl4WordBank= new Array<String>();
		lvl5WordBank= new Array<String>();																																																																																																
		lvl6WordBank= new Array<String>();
		lvl7WordBank= new Array<String>();
		lvl8WordBank= new Array<String>();																																																																																																
		lvl9WordBank= new Array<String>();
		lvl10WordBank= new Array<String>();
		
		
		allVerbs = new Array<String>();
		allVerbs.addAll(verbListOne);
		allVerbs.addAll(verbListTwo);
		allVerbs.addAll(verbListThree);
		
		allNouns = new Array<String>();
		allNouns.addAll(nounListOne);
		allNouns.addAll(nounListTwo);
		allNouns.addAll(nounListThree);
		
		allAdverbs= new Array<String>();
		allAdverbs.addAll(adverbListOne);
		allAdverbs.addAll(adverbListTwo);
		allAdverbs.addAll(adverbListThree);
		
		allPronouns= new Array<String>();
		allPronouns.addAll(pronounListOne);
		allPronouns.addAll(pronounListTwo);
		allPronouns.addAll(pronounListThree);
		
		allAdjectives= new Array<String>();
		allAdjectives.addAll(adjectivesListOne);
		allAdjectives.addAll(adjectivesListTwo);
		
		allConjunctions= new Array<String>();
		allConjunctions.addAll(conjunctionListOne);
		allPrepositions= new Array<String>();
		allPrepositions.addAll(prepositionListOne);
		
		allWordArrays.add(allVerbs);
		allWordArrays.add(allNouns);
		allWordArrays.add(allPronouns);
		allWordArrays.add(allAdverbs);
		allWordArrays.add(allAdjectives);
		allWordArrays.add(allConjunctions);
		allWordArrays.add(allPrepositions);
		
		lvl1WordBank.addAll(verbListOne);
		lvl1WordBank.addAll(nounListOne);
		
		lvl2WordBank.addAll(lvl1WordBank);
		lvl2WordBank.addAll(verbListTwo);
		lvl2WordBank.addAll(nounListTwo);
		
		lvl3WordBank.addAll(lvl2WordBank);
		lvl3WordBank.addAll(pronounListOne);
		
		
		lvl4WordBank.addAll(lvl3WordBank);
		lvl4WordBank.addAll(nounListThree);
		lvl4WordBank.addAll(verbListThree);
		
		
		lvl5WordBank.addAll(lvl4WordBank);	
		lvl5WordBank.addAll(pronounListTwo);
		
		lvl6WordBank.addAll(lvl5WordBank);	
		lvl6WordBank.addAll(adverbListOne);	
		lvl6WordBank.addAll(pronounListThree);	
		
		lvl7WordBank.addAll(lvl6WordBank);	
		lvl7WordBank.addAll(adverbListTwo);
		
		lvl8WordBank.addAll(lvl7WordBank);		
		lvl8WordBank.addAll(adjectivesListOne);	
		lvl8WordBank.addAll(adverbListThree);	
		
		lvl9WordBank.addAll(lvl8WordBank);		
		lvl9WordBank.addAll(adjectivesListTwo);
		
		lvl9WordBank.addAll(conjunctionListOne);		
			
		
		lvl10WordBank.addAll(lvl8WordBank);
		lvl10WordBank.addAll(prepositionListOne);	
		
		alllevelBanks.add(lvl1WordBank);
		alllevelBanks.add(lvl2WordBank);
		alllevelBanks.add(lvl3WordBank);
		alllevelBanks.add(lvl4WordBank);
		alllevelBanks.add(lvl5WordBank);
		alllevelBanks.add(lvl6WordBank);
		alllevelBanks.add(lvl7WordBank);
		alllevelBanks.add(lvl8WordBank);
		alllevelBanks.add(lvl9WordBank);
		alllevelBanks.add(lvl10WordBank);
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																											
		
		
	}
	
	private void initQuestionTypeimg(){
		questionTypeImages = new Array<Texture>();
		for(int i = 0; i<7;i++ ){
			Texture tx = new Texture(Gdx.files.internal("images/Space Blaster/partsOfSpeachImg/"+i+".png"));
			questionTypeImages.add(tx);
		}
	}
	
	public Texture getQuestionTexture(){
		return questionTypeImages.get(questionTypeInt);
	}
	
	public void setNextQuestion(){
		questionTypeInt = rand.nextInt(levelQuestionTypes[currentLevel]);
		question = questionType[questionTypeInt];
	}
	
	public void nextQuestion(){
		
		if(questionNumber>3){
			init();
		}else{
			System.out.print(questionType[questionTypeInt]+" ");
			randWord =getRandomWord();
			System.out.println(randWord);
			questionNumber+=1;
		}
	}
	
	public String getQuestion(){
		return question;
	}
	public String getTheRandomW(){
		return randWord;
	}
	
	public int getLevelNumber(){
		return currentLevel+1;
	}
	public String getRandomWord(){
		int q = rand.nextInt(answerSets.length);
		int w = rand.nextInt(answerSets[q].length);
		randWord =answerSets[q][w];
		return randWord;
	}
	
	public String getRandomWordFromLevelBank(){
		String word = "";
		Array<String> levelBank = alllevelBanks.get(currentLevel);
		word = levelBank.random();
		
		return word.toLowerCase();
	}
	
	public boolean checkAnswer(String chk){
		for(String word:answerSets[questionTypeInt]){
			if(chk==word){
			
				return true;
			}
		}
		return false;
		
	}
	
	public boolean checkAnswerNew(String chk){
		for(String word:allWordArrays.get(questionTypeInt)){
			System.out.println("CHECK: "+chk+" MATCH WORD: "+word);
			if(chk.equals(word.toLowerCase())){
				System.out.println(chk+" IS VALID "+word);
			
				return true;
			}
		}
		return false;
		
	}
	
	public static void main(String[] args){
		//SpaceBlasterQuestion question = new SpaceBlasterQuestion();
		
		
	}
	
	

}
