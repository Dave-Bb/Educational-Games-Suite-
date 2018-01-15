//This class manages the different states of the game
//A simple stack is used to keep track of each game state

package com.games.wordgames.handlers;

import java.util.Stack;

import com.games.wordgames.main.Game;
import com.games.wordgames.states.GameState;
import com.games.wordgames.states.LoadingScreen;
import com.games.wordgames.states.MasterMenu;
import com.games.wordgames.states.RobotLogic;
import com.games.wordgames.states.SpaceBlaster;
import com.games.wordgames.states.SpeedSums;
import com.games.wordgames.states.BambooBlaster;
import com.games.wordgames.states.BalloonTyper;

public class GameStateManager {
	private Game game;
	
	private Stack<GameState> gameStates;

	public static final int LOADING_SCREEN = 0;
	public static final int MASTER_MENU = 2;
	public static final int FALLING_LETTERS = 3;
	public static final int SPEED_SUMS = 4;
	public static final int BAMBOO_BLASTER = 5;
	public static final int SPACE_BLASTER = 6;
	public static final int ROBOT_LOGIC = 7;

	public GameStateManager(Game game){
		this.game = game;
		gameStates = new Stack<GameState>();
		pushState(LOADING_SCREEN );
	}
	
	public Game game(){return game;}
	
	public void update(float delta)
	{
		gameStates.peek().update(delta);
	}
	
	public void render(){
		gameStates.peek().render();
		
	}
	
	private GameState getState(int state){
		if(state==FALLING_LETTERS) {
			return new BalloonTyper(this);
		}if(state==SPEED_SUMS){
			return new SpeedSums(this);
		}if(state==BAMBOO_BLASTER){
			return new BambooBlaster(this);
		}if(state==SPACE_BLASTER){
			return new SpaceBlaster(this);
		}if(state==MASTER_MENU){
			return new MasterMenu(this);
		}if(state==ROBOT_LOGIC){
			return new RobotLogic(this);
		}if(state==LOADING_SCREEN){
			return new LoadingScreen(this);
		}
		return null;
		
	}
	
	public void setState(int state){
		popState();
		pushState(state);
	}
	
	public void pushState(int state){
		gameStates.push(getState(state));
	}
	
	public void popState(){
		GameState g = gameStates.pop();
		g.dispose();
	}

}
