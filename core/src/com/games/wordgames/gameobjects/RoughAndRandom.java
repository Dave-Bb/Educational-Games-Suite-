package com.games.wordgames.gameobjects;

import com.badlogic.gdx.utils.Array;

public class RoughAndRandom {
	
	public static void main(String[] args){
		
		
		Array<Integer> bigList = new Array<Integer>();
		
		for(int i = 0; i<46;i++){
			bigList.add(i);
		}
		int pages =bigList.size/10;
		
		
		System.out.println(pages);
		System.out.println("Draw Page 5");
		pages = 1;
		for(int i = 0;i<10;i++){
			if(i+(pages*10)<=bigList.size-1){
				System.out.println(bigList.get(i+(pages*10)));
			}else{
				
			}
			
		}
	}

}
