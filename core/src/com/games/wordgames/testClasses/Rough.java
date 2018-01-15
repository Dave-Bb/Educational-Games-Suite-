package com.games.wordgames.testClasses;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;



public class Rough {
	
	static Array<String> isolatedWords = new Array<String>();
	static Array<String> charArrayList = new Array<String>();
	
	public static void addWord(char[] isolateCharacters){
		
	}
	public static void main(String[] args) {
		String path = "/wordgames-core/assets/textFiles/spaceBlasterWords/verbs1.txt";
		String verbs = "run play eat sleep was"
				+ " where am is are work sleep "
				+ "walk sing cry ran sit cut";
		

		Array<String> words = new Array<String>();
		// FileHandle handle = Gdx.files.internal("textFiles/spaceBlasterWords/nouns1.txt");
		 // String text = handle.readString();
		  String wordsArray[] = verbs.split(" ");
		  for(String word : wordsArray) {
		      words.add(word);
		  }
		  
		  for(String w:words){
			  System.out.println(w);
		  }
	}

}

/*
String httpRaw = new String(Files.readAllBytes(Paths.get("/home/dave/Documents/Java MESS/http2.txt")));
System.out.println(httpRaw);
char[] stringChar = httpRaw.toCharArray();
int i;
String word = "";
for(i = 0;i<stringChar.length;i++){
	
		if(stringChar[i]=='>'){
			word = "";
			//System.out.println("OPEN");
			int wordLen = 1;
			boolean cont = true;
			while(cont){
				if((i+wordLen)<stringChar.length){
					if(stringChar[i+wordLen]!='<'){
						//System.out.print(stringChar[i+wordLen]);
						
						word+=stringChar[i+wordLen];
						wordLen+=1;
					}else{
						
						cont = false;
					}
					
				}else{
					cont = false;
				}
			}
				isolatedWords.add(word);
			}//System.out.println();
 
}

for(String w: isolatedWords){
	if(w!=""){
		System.out.println(w);
	}
	
}
*/