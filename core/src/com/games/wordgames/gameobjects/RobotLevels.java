package com.games.wordgames.gameobjects;

import com.badlogic.gdx.utils.Array;
import com.games.wordgames.states.RobotLogic;


public class RobotLevels {
	//private RobotLogicGroundBloc[][] mapArray;
	private Array<RobotLogicGroundBloc[][]> levelArray;
	private RobotLogicGroundBloc[][] mapArray ;
	private int level;
	public RobotLevels(){
		levelArray = new Array<RobotLogicGroundBloc[][]>();
		mapArray = new RobotLogicGroundBloc[RobotLogic.ROW][RobotLogic.COL];
		initLevels();
		level = 0;
	}
	
	private void initLevels(){
		//newTestLevel();
		initLevelOne();
		initLevelTwo();
		initLevelThree();
		initLevelFour();
		initLevelFive();
		initLevelSix();
		initLevelSeven();
		initLevelEight();
		initLevelNine();
		initLevelTen();
		initLevelEleven();
		initLevelTwelve();
		initLevelThirteen();
		initLevelFourteen();
		
	}
	
	private void initLevelOne(){
		mapArray = generateMap();
		mapArray[4][2].setDraw(true);
		mapArray[4][2].setStart();
		mapArray[4][3].setDraw(true);
		mapArray[4][4].setDraw(true);
		mapArray[4][5].setDraw(true);
		mapArray[4][5].setFinish();
		levelArray.add(mapArray);
		
	}
	
	private void initLevelTwo(){
		mapArray = generateMap();
		mapArray[1][4].setDraw(true);
		mapArray[1][4].setFinish();
		mapArray[2][4].setDraw(true);
		mapArray[3][4].setDraw(true);
		mapArray[3][4].addCoin();
		mapArray[4][1].setDraw(true);
		mapArray[4][1].setStart();
		mapArray[4][2].setDraw(true);
		mapArray[4][3].setDraw(true);
		mapArray[4][4].setDraw(true);
		levelArray.add(mapArray);
	}
	
	private void initLevelThree(){
		mapArray = generateMap();
		mapArray[1][1].setDraw(true);
		mapArray[1][2].setDraw(true);
		mapArray[1][3].setDraw(true);
		mapArray[1][3].addCoin();
		mapArray[1][4].setDraw(true);
		mapArray[1][4].addCoin();
		mapArray[1][5].setDraw(true);
		mapArray[1][6].setDraw(true);
		mapArray[2][1].setDraw(true);
		mapArray[2][6].setDraw(true);
		mapArray[3][1].setDraw(true);
		mapArray[3][1].addCoin();
		mapArray[3][4].addCoin();
		mapArray[3][6].setDraw(true);
		mapArray[3][6].addCoin();
		mapArray[4][1].setDraw(true);
		mapArray[4][1].setStart();
		mapArray[4][4].setFinish();
		mapArray[4][6].setDraw(true);
		mapArray[4][6].setFinish();
		levelArray.add(mapArray);
		
	}
	
	private void initLevelFour(){
		mapArray = generateMap();
		mapArray[2][0].setDraw(true);
		mapArray[2][1].setDraw(true);
		mapArray[2][2].setDraw(true);
		mapArray[2][3].setDraw(true);
		mapArray[2][3].makeHole();
		mapArray[2][4].setDraw(true);
		mapArray[2][5].setDraw(true);
		mapArray[2][5].addCoin();
		mapArray[2][6].setDraw(true);
		mapArray[3][0].setDraw(true);
		mapArray[3][2].setDraw(true);
		mapArray[3][2].addCoin();
		mapArray[3][3].setDraw(true);
		mapArray[3][3].makeHole();
		mapArray[3][4].setDraw(true);
		mapArray[3][6].setDraw(true);
		mapArray[3][6].addCoin();
		mapArray[4][0].setDraw(true);
		mapArray[4][0].setStart();
		mapArray[4][2].setDraw(true);
		mapArray[4][3].setDraw(true);
		mapArray[4][3].addCoin();
		mapArray[4][4].setDraw(true);
		mapArray[4][6].setDraw(true);
		mapArray[4][7].setDraw(true);
		mapArray[4][7].setFinish();
		levelArray.add(mapArray);
	}
	
	private void initLevelFive(){
		mapArray = generateMap();
		mapArray[0][1].makeHole();
		mapArray[0][4].setDraw(true);
		mapArray[0][5].setDraw(true);
		mapArray[0][5].addCoin();
		mapArray[0][6].setDraw(true);
		mapArray[1][0].setDraw(true);
		mapArray[1][1].setDraw(true);
		mapArray[1][1].addCoin();
		mapArray[1][2].setDraw(true);
		mapArray[1][4].setDraw(true);
		mapArray[1][6].setDraw(true);
		mapArray[2][0].setDraw(true);
		mapArray[2][2].setDraw(true);
		mapArray[2][3].setDraw(true);
		mapArray[2][4].setDraw(true);
		mapArray[2][5].setDraw(true);
		mapArray[2][6].setDraw(true);
		mapArray[2][6].addCoin();
		mapArray[3][0].setDraw(true);
		mapArray[3][6].setDraw(true);
		mapArray[4][0].setDraw(true);
		mapArray[4][0].setStart();
		mapArray[4][1].setDraw(true);
		mapArray[4][1].makeHole();
		mapArray[4][2].setDraw(true);
		mapArray[4][2].setFinish();
		mapArray[4][3].setDraw(true);
		mapArray[4][4].setDraw(true);
		mapArray[4][4].addCoin();
		mapArray[4][5].setDraw(true);
		mapArray[4][6].setDraw(true);
		levelArray.add(mapArray);
	}
	
	private void initLevelSix(){
		mapArray = generateMap();
		mapArray[0][0].setDraw(true);
		mapArray[0][1].setDraw(true);
		mapArray[0][1].addCoin();
		mapArray[0][2].setDraw(true);
		mapArray[0][2].addCoin();
		mapArray[0][3].setDraw(true);
		mapArray[0][3].addCoin();
		mapArray[0][4].setDraw(true);
		mapArray[0][4].addCoin();
		mapArray[0][5].setDraw(true);
		mapArray[1][0].setDraw(true);
		mapArray[1][0].addCoin();
		mapArray[1][1].setDraw(true);
		mapArray[1][1].makeHole();
		mapArray[1][2].setDraw(true);
		mapArray[1][2].makeHole();
		mapArray[1][3].setDraw(true);
		mapArray[1][3].makeHole();
		mapArray[1][4].setDraw(true);
		mapArray[1][4].makeHole();
		mapArray[1][5].setDraw(true);
		mapArray[2][0].setDraw(true);
		mapArray[2][1].setDraw(true);
		mapArray[2][2].setDraw(true);
		mapArray[2][2].addCoin();
		mapArray[2][3].setDraw(true);
		mapArray[2][3].makeHole();
		mapArray[2][4].setDraw(true);
		mapArray[2][5].setDraw(true);
		mapArray[2][6].setDraw(true);
		mapArray[2][6].addCoin();
		mapArray[2][7].setDraw(true);
		mapArray[3][0].setDraw(true);
		mapArray[3][0].addCoin();
		mapArray[3][1].setDraw(true);
		mapArray[3][2].setDraw(true);
		mapArray[3][2].addCoin();
		mapArray[3][3].setDraw(true);
		mapArray[3][4].setDraw(true);
		mapArray[3][5].setDraw(true);
		mapArray[3][6].setDraw(true);
		mapArray[3][7].setDraw(true);
		mapArray[3][7].addCoin();
		mapArray[4][0].setDraw(true);
		mapArray[4][0].setFinish();
		mapArray[4][3].setDraw(true);
		mapArray[4][3].setStart();
		levelArray.add(mapArray);

	}
	
	private void initLevelSeven(){
		mapArray = generateMap();
		mapArray[1][3].setDraw(true);
		mapArray[2][0].setStart();
		mapArray[2][3].setDraw(true);
		mapArray[2][3].addCoin();
		mapArray[3][0].setDraw(true);
		mapArray[3][0].setStart();
		mapArray[3][1].setDraw(true);
		mapArray[3][2].setDraw(true);
		mapArray[3][3].setDraw(true);
		mapArray[4][3].setDraw(true);
		mapArray[4][4].setDraw(true);
		mapArray[4][4].setFinish();
		levelArray.add(mapArray);
	}
	
	private void initLevelEight(){
		mapArray = generateMap();
		mapArray[0][0].setDraw(true);
		mapArray[0][0].setStart();
		mapArray[0][1].setDraw(true);
		mapArray[0][2].setDraw(true);
		mapArray[0][3].setDraw(true);
		mapArray[0][3].addCoin();
		mapArray[0][4].setDraw(true);
		mapArray[0][5].setDraw(true);
		mapArray[0][5].makeHole();
		mapArray[0][6].setDraw(true);
		mapArray[0][6].makeHole();
		mapArray[0][7].setDraw(true);
		mapArray[0][7].setFinish();
		mapArray[1][2].setDraw(true);
		mapArray[1][3].setDraw(true);
		mapArray[1][7].setDraw(true);
		mapArray[2][0].setDraw(true);
		mapArray[2][1].setDraw(true);
		mapArray[2][1].addCoin();
		mapArray[2][2].setDraw(true);
		mapArray[2][3].setDraw(true);
		mapArray[2][3].makeHole();
		mapArray[2][4].setDraw(true);
		mapArray[2][5].setDraw(true);
		mapArray[2][5].addCoin();
		mapArray[2][6].setDraw(true);
		mapArray[2][7].setDraw(true);
		mapArray[3][0].setDraw(true);
		mapArray[3][1].setDraw(true);
		mapArray[3][1].makeHole();
		mapArray[3][2].setDraw(true);
		mapArray[3][2].addCoin();
		mapArray[3][3].setDraw(true);
		mapArray[3][6].setDraw(true);
		mapArray[3][7].setDraw(true);
		mapArray[3][7].addCoin();
		mapArray[4][0].setDraw(true);
		mapArray[4][1].setDraw(true);
		mapArray[4][1].addCoin();
		mapArray[4][2].setDraw(true);
		mapArray[4][3].setDraw(true);
		mapArray[4][4].setDraw(true);
		mapArray[4][4].addCoin();
		mapArray[4][5].setDraw(true);
		mapArray[4][6].setDraw(true);
		mapArray[4][7].setDraw(true);
		levelArray.add(mapArray);

		
	}
	private void initLevelNine(){
		mapArray = generateMap();
		mapArray[0][0].setDraw(true);
		mapArray[0][1].setDraw(true);
		mapArray[0][1].addCoin();
		mapArray[0][2].setDraw(true);
		mapArray[0][3].setDraw(true);
		mapArray[0][4].setDraw(true);
		mapArray[0][5].setDraw(true);
		mapArray[0][5].addCoin();
		mapArray[0][6].setDraw(true);
		mapArray[0][7].setDraw(true);
		mapArray[0][7].setFinish();
		mapArray[1][0].setDraw(true);
		mapArray[1][2].setDraw(true);
		mapArray[1][4].setDraw(true);
		mapArray[1][7].setDraw(true);
		mapArray[1][7].makeHole();
		mapArray[2][0].setDraw(true);
		mapArray[2][0].addCoin();
		mapArray[2][2].setDraw(true);
		mapArray[2][2].addCoin();
		mapArray[2][4].setDraw(true);
		mapArray[2][5].setDraw(true);
		mapArray[2][6].setDraw(true);
		mapArray[2][6].addCoin();
		mapArray[2][7].setDraw(true);
		mapArray[3][0].setDraw(true);
		mapArray[3][2].setDraw(true);
		mapArray[3][4].setDraw(true);
		mapArray[3][7].setDraw(true);
		mapArray[4][0].setDraw(true);
		mapArray[4][0].setStart();
		mapArray[4][1].setDraw(true);
		mapArray[4][2].setDraw(true);
		mapArray[4][3].setDraw(true);
		mapArray[4][3].addCoin();
		mapArray[4][4].setDraw(true);
		mapArray[4][5].setDraw(true);
		mapArray[4][6].setDraw(true);
		mapArray[4][7].setDraw(true);
		levelArray.add(mapArray);
		
	}

	private void initLevelTen(){
		mapArray = generateMap();
		mapArray[0][0].setDraw(true);
		mapArray[0][0].setFinish();
		mapArray[0][1].setDraw(true);
		mapArray[0][1].makeHole();
		mapArray[0][2].setDraw(true);
		mapArray[0][3].setDraw(true);
		mapArray[0][4].setDraw(true);
		mapArray[0][4].addCoin();
		mapArray[0][5].setDraw(true);
		mapArray[0][5].addCoin();
		mapArray[0][6].setDraw(true);
		mapArray[0][6].addCoin();
		mapArray[0][7].setDraw(true);
		mapArray[1][0].setDraw(true);
		mapArray[1][1].setDraw(true);
		mapArray[1][1].makeHole();
		mapArray[1][2].setDraw(true);
		mapArray[1][2].addCoin();
		mapArray[1][3].setDraw(true);
		mapArray[1][3].addCoin();
		mapArray[2][0].setDraw(true);
		mapArray[2][0].addCoin();
		mapArray[2][1].setDraw(true);
		mapArray[2][1].makeHole();
		mapArray[2][2].setDraw(true);
		mapArray[2][3].setDraw(true);
		mapArray[2][4].setDraw(true);
		mapArray[2][5].setDraw(true);
		mapArray[2][5].addCoin();
		mapArray[2][6].setDraw(true);
		mapArray[2][7].setDraw(true);
		mapArray[2][7].setStart();
		mapArray[3][0].setDraw(true);
		mapArray[3][0].addCoin();
		mapArray[3][1].setDraw(true);
		mapArray[3][1].makeHole();
		mapArray[3][2].setDraw(true);
		mapArray[3][2].addCoin();
		mapArray[4][0].setDraw(true);
		mapArray[4][1].setDraw(true);
		mapArray[4][1].addCoin();
		mapArray[4][2].setDraw(true);
		mapArray[4][3].setDraw(true);
		mapArray[4][4].setDraw(true);
		mapArray[4][4].addCoin();
		mapArray[4][5].setDraw(true);
		mapArray[4][5].addCoin();
		mapArray[4][6].setDraw(true);
		mapArray[4][6].addCoin();
		mapArray[4][7].setDraw(true);
		levelArray.add(mapArray);

	
	}
	
	private void initLevelEleven(){
		mapArray = generateMap();
		mapArray[0][0].setDraw(true);
		mapArray[0][1].setDraw(true);
		mapArray[0][1].addCoin();
		mapArray[0][2].setDraw(true);
		mapArray[0][3].setDraw(true);
		mapArray[0][4].setDraw(true);
		mapArray[0][5].setDraw(true);
		mapArray[0][5].setFinish();
		mapArray[0][6].setDraw(true);
		mapArray[0][7].setDraw(true);
		mapArray[1][2].setDraw(true);
		mapArray[1][3].setDraw(true);
		mapArray[1][3].makeHole();
		mapArray[1][4].setDraw(true);
		mapArray[1][4].addCoin();
		mapArray[1][5].setDraw(true);
		mapArray[1][5].makeHole();
		mapArray[1][6].setDraw(true);
		mapArray[1][6].addCoin();
		mapArray[2][0].setDraw(true);
		mapArray[2][0].setStart();
		mapArray[2][1].setDraw(true);
		mapArray[2][2].setDraw(true);
		mapArray[2][3].setDraw(true);
		mapArray[2][3].makeHole();
		mapArray[2][4].setDraw(true);
		mapArray[2][5].setDraw(true);
		mapArray[2][6].setDraw(true);
		mapArray[3][2].setDraw(true);
		mapArray[3][3].setDraw(true);
		mapArray[3][4].setDraw(true);
		mapArray[3][5].setDraw(true);
		mapArray[3][5].addCoin();
		mapArray[3][6].setDraw(true);
		mapArray[3][6].addCoin();
		mapArray[3][7].setDraw(true);
		mapArray[3][7].makeHole();
		mapArray[4][2].setDraw(true);
		mapArray[4][3].setDraw(true);
		mapArray[4][3].addCoin();
		mapArray[4][4].setDraw(true);
		mapArray[4][5].setDraw(true);
		mapArray[4][5].makeHole();
		mapArray[4][6].setDraw(true);
		mapArray[4][7].setDraw(true);
		mapArray[4][7].makeHole();
		levelArray.add(mapArray);
		
	}
	
	private void initLevelTwelve(){
		mapArray = generateMap();
		mapArray[0][0].setDraw(true);
		mapArray[0][0].setStart();
		mapArray[0][1].setDraw(true);
		mapArray[0][2].setDraw(true);
		mapArray[0][2].addCoin();
		mapArray[0][3].setDraw(true);
		mapArray[0][3].addCoin();
		mapArray[0][4].setDraw(true);
		mapArray[0][5].setDraw(true);
		mapArray[1][2].setDraw(true);
		mapArray[1][5].setDraw(true);
		mapArray[2][0].setDraw(true);
		mapArray[2][0].setFinish();
		mapArray[2][1].setDraw(true);
		mapArray[2][1].makeHole();
		mapArray[2][2].setDraw(true);
		mapArray[2][2].addCoin();
		mapArray[2][5].setDraw(true);
		mapArray[2][6].setDraw(true);
		mapArray[2][7].setDraw(true);
		mapArray[3][0].setDraw(true);
		mapArray[3][2].setDraw(true);
		mapArray[3][3].setDraw(true);
		mapArray[3][4].setDraw(true);
		mapArray[3][5].setDraw(true);
		mapArray[3][6].setDraw(true);
		mapArray[3][6].addCoin();
		mapArray[3][7].setDraw(true);
		mapArray[4][0].setDraw(true);
		mapArray[4][1].setDraw(true);
		mapArray[4][1].addCoin();
		mapArray[4][2].setDraw(true);
		mapArray[4][3].setDraw(true);
		mapArray[4][3].addCoin();
		mapArray[4][4].setDraw(true);
		mapArray[4][4].makeHole();
		mapArray[4][5].setDraw(true);
		mapArray[4][6].setDraw(true);
		mapArray[4][7].setDraw(true);
		levelArray.add(mapArray);
	}
	
	private void initLevelThirteen(){
		mapArray = generateMap();
		mapArray[0][3].setDraw(true);
		mapArray[0][4].setDraw(true);
		mapArray[0][4].addCoin();
		mapArray[0][5].setDraw(true);
		mapArray[1][0].setDraw(true);
		mapArray[1][1].setDraw(true);
		mapArray[1][1].addCoin();
		mapArray[1][2].setDraw(true);
		mapArray[1][3].setDraw(true);
		mapArray[1][4].setDraw(true);
		mapArray[1][4].makeHole();
		mapArray[1][5].setDraw(true);
		mapArray[2][2].setDraw(true);
		mapArray[2][3].setDraw(true);
		mapArray[2][3].makeHole();
		mapArray[2][4].setDraw(true);
		mapArray[2][5].setDraw(true);
		mapArray[2][6].setDraw(true);
		mapArray[2][6].addCoin();
		mapArray[2][7].setDraw(true);
		mapArray[3][0].setDraw(true);
		mapArray[3][1].setDraw(true);
		mapArray[3][2].setDraw(true);
		mapArray[3][3].setDraw(true);
		mapArray[3][3].makeHole();
		mapArray[3][4].setDraw(true);
		mapArray[3][5].setDraw(true);
		mapArray[3][5].makeHole();
		mapArray[3][6].setDraw(true);
		mapArray[3][7].setDraw(true);
		mapArray[3][7].makeHole();
		mapArray[4][0].setDraw(true);
		mapArray[4][0].setStart();
		mapArray[4][2].setDraw(true);
		mapArray[4][3].setDraw(true);
		mapArray[4][3].addCoin();
		mapArray[4][4].setDraw(true);
		mapArray[4][5].setDraw(true);
		mapArray[4][5].addCoin();
		mapArray[4][6].setDraw(true);
		mapArray[4][7].setDraw(true);
		mapArray[4][7].setFinish();
		levelArray.add(mapArray);
		
	}
	
	private void initLevelFourteen(){
		mapArray = generateMap();
		mapArray[0][3].setDraw(true);
		mapArray[0][3].makeHole();
		mapArray[0][4].setDraw(true);
		mapArray[0][4].setFinish();
		mapArray[0][5].setDraw(true);
		mapArray[0][6].setDraw(true);
		mapArray[0][6].addCoin();
		mapArray[0][7].setDraw(true);
		mapArray[1][2].makeHole();
		mapArray[1][3].setDraw(true);
		mapArray[1][3].setStart();
		mapArray[1][5].setDraw(true);
		mapArray[1][5].addCoin();
		mapArray[1][6].setDraw(true);
		mapArray[1][6].makeHole();
		mapArray[1][7].setDraw(true);
		mapArray[1][7].makeHole();
		mapArray[2][0].setDraw(true);
		mapArray[2][1].setDraw(true);
		mapArray[2][1].addCoin();
		mapArray[2][2].setDraw(true);
		mapArray[2][3].setDraw(true);
		mapArray[2][4].setDraw(true);
		mapArray[2][4].makeHole();
		mapArray[2][5].setDraw(true);
		mapArray[2][6].setDraw(true);
		mapArray[2][6].addCoin();
		mapArray[2][7].setDraw(true);
		mapArray[3][0].setDraw(true);
		mapArray[3][0].addCoin();
		mapArray[3][5].setDraw(true);
		mapArray[3][7].setDraw(true);
		mapArray[4][0].setDraw(true);
		mapArray[4][1].setDraw(true);
		mapArray[4][2].setDraw(true);
		mapArray[4][2].addCoin();
		mapArray[4][3].setDraw(true);
		mapArray[4][3].addCoin();
		mapArray[4][4].setDraw(true);
		mapArray[4][4].addCoin();
		mapArray[4][5].setDraw(true);
		mapArray[4][6].setDraw(true);
		mapArray[4][6].addCoin();
		mapArray[4][7].setDraw(true);
		levelArray.add(mapArray);
	}
	
	private void initLevelFifteen(){
		
	}
	
	private void newTestLevel(){
		mapArray = generateMap();
		mapArray[0][1].setDraw(true);
		mapArray[0][1].addCoin();
		mapArray[0][3].setDraw(true);
		mapArray[0][3].addCoin();
		mapArray[0][5].setDraw(true);
		mapArray[0][5].addCoin();
		mapArray[0][7].setDraw(true);
		mapArray[0][7].addCoin();
		mapArray[1][0].setDraw(true);
		mapArray[1][0].makeHole();
		mapArray[1][0].addCoin();
		mapArray[1][2].setDraw(true);
		mapArray[1][2].makeHole();
		mapArray[1][2].addCoin();
		mapArray[1][4].setDraw(true);
		mapArray[1][4].makeHole();
		mapArray[1][4].addCoin();
		mapArray[1][6].setDraw(true);
		mapArray[1][6].makeHole();
		mapArray[1][6].addCoin();
		mapArray[2][0].setDraw(true);
		mapArray[2][1].setDraw(true);
		mapArray[2][2].setDraw(true);
		mapArray[2][4].setDraw(true);
		mapArray[2][6].setDraw(true);
		mapArray[2][7].setDraw(true);
		mapArray[3][0].setDraw(true);
		mapArray[3][0].setStart();
		mapArray[3][1].setDraw(true);
		mapArray[3][2].setDraw(true);
		mapArray[3][3].setDraw(true);
		mapArray[3][5].setDraw(true);
		mapArray[3][6].setDraw(true);
		mapArray[3][7].setDraw(true);
		mapArray[3][7].setFinish();
		mapArray[4][0].setDraw(true);
		mapArray[4][1].setDraw(true);
		mapArray[4][2].setDraw(true);
		mapArray[4][3].setDraw(true);
		mapArray[4][4].setDraw(true);
		mapArray[4][5].setDraw(true);
		mapArray[4][6].setDraw(true);
		mapArray[4][7].setDraw(true);
		levelArray.add(mapArray);
	}
	private RobotLogicGroundBloc[][] generateMap(){
		RobotLogicGroundBloc[][] blankMap = new RobotLogicGroundBloc[RobotLogic.ROW][RobotLogic.COL];
		for(int row =0;row<RobotLogic.ROW;row++){
			for(int col  = 0;col<RobotLogic.COL;col++){
				blankMap[row][col] = 
						new RobotLogicGroundBloc(140+(64*col),480-(row*64));
				//sb.draw(groundTexture,200+(64*i),380-(j*64),64,72);
			}
			
		}
		
		return blankMap;
		
	}
	
	public void nextLevel(){
		if(level<levelArray.size-1){
			level +=1;
		}else{
			level = 0;
		}
	}
	
	public int getLevel(){
		return level;
	}
	
	public RobotLogicGroundBloc[][] getMap(){
		return levelArray.get(level);
	}
	
	public RobotLogicGroundBloc[][] getMap(int i){
		return levelArray.get(i);
	}
	
}
