package com.games.wordgames.handlers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.audio.Sound;

public class Assets {
	
	public static String currentlyLoading;
	
	public static final AssetManager manager = new AssetManager();
	
	//ButtonAssets
	
	
	//Sounds Assets 
	public static final String masterMenuMusic = "assets/sounds/mainMenuMusic.mp3";
	
	//MasterMenu Assets assets 
	public static final String confirmExitBackgroundMM = "assets/images/mainMenu/confirmExit.png";
	public static final String mainMenuBackgroundTxMM ="assets/images/mainMenu/MainMenuGreen.png";
	public static final String wordGamesBackgroundMM = "assets/images/mainMenu/WordGamesBlue.png";
	public static final String numberGamesBackgroundMM ="assets/images/mainMenu/NumberGamesOraneg.png" ;
	public static final String logicGamesBackgroundMM = "assets/images/mainMenu/LogicGamesPurple.png";
	public static final String menuBaloonTextureMM = "assets/images/mainMenu/floatingBaloons.png";
	
	//Menu Assets 
	public static final String menuBackgroundFileName = "assets/images/global/purpleMenu.png";
	public static final String rainbowSkinFileName = "assets/skins/rainbow/skin/rainbow-ui.json";
	public static final String comicSkinFileName = "assets/skins/comic/skin/comic-ui.json";
	public static final String levelPaneSkinFileName = "assets/skins/level-plane/skin/level-plane-ui.json";
	public static final String flatEarthSkinFileName = "assets/skins/flat-earth/skin/flat-earth-ui.json";
	public static final String offWhiteBackgroundFileName = "assets/images/global/offWhiteBackground.png";
	public static final String cleanCrispySkinFileName = "assets/skins/clean-crispy/skin/clean-crispy-ui.json";
	
	
	//Bamboo Blaster Assets 
	public static final String jungleBackgroundFileNameFileName = "assets/images/Bamboo blaster/jungleBackground.png";
	public static final String bambooBlasterFileName ="assets/images/Bamboo blaster/bambooGun.png";
	public static final String crossHairFileName = "assets/images/Bamboo blaster/crossHair.png";
	public static final String coconutFileName= "assets/images/Bamboo blaster/coconut.png";
	public static final String leafTargetFileName= "assets/images/Bamboo blaster/leaf.png";
	public static final String brokenLeafFileName= "assets/images/Bamboo blaster/brokenLeaf.png";
	
	
	
	
	public static void load(){
		
		currentlyLoading = "";
		//Textures
		loadMenuAssets();

		//Skins
		
		loadSkins();
        loadGameAssets();

        loadSounds();
		
	}

	private static void loadMenuAssets(){
		//Log in screen
		manager.load("assets/images/global/offWhiteBackground.png", Texture.class);
		manager.load("assets/images/mainMenu/confirmExit.png", Texture.class);
		manager.load("assets/images/mainMenu/MainMenuGreen.png", Texture.class);
		manager.load("assets/images/mainMenu/WordGamesBlue.png", Texture.class);
		manager.load("assets/images/mainMenu/NumberGamesOraneg.png" , Texture.class);
		manager.load("assets/images/mainMenu/LogicGamesPurple.png", Texture.class);
		manager.load("assets/images/mainMenu/floatingBaloons.png", Texture.class);

		//button textures
		currentlyLoading = "Button Set Textures";
		manager.load("assets/images/global/musicIcon/soundOn.png", Texture.class);
		manager.load("assets/images/global/musicIcon/soundOff.png", Texture.class);
		manager.load("assets/images/global/purpleMenu.png",Texture.class);
		manager.load("assets/images/global/logInBackground.png", Texture.class);
		manager.load("assets/images/global/logIn/leftPointingArrow.png", Texture.class);
		manager.load("assets/images/global/logIn/leftDownPointingArrow.png", Texture.class);
		manager.load("assets/images/global/logIn/rightPointingArrow.png", Texture.class);
		manager.load("assets/images/global/logIn/rightMouseClickHelpIcon.png", Texture.class);
		currentlyLoading = "Log In Screen Textures";
		//Log in Image Buttons
		manager.load("assets/images/mainMenu/ButtonIcons/backUp.png", Texture.class);
		manager.load("assets/images/mainMenu/ButtonIcons/backDOWN.png", Texture.class);
		manager.load("assets/images/mainMenu/ButtonIcons/playUp.png", Texture.class);
		manager.load("assets/images/mainMenu/ButtonIcons/playDown.png", Texture.class);
		currentlyLoading = "Log In Screen ImageButtons";
		manager.load("assets/imageButton/exitButtonIcon.png", Texture.class);
		manager.load("assets/imageButton/exitButtonIcon.png", Texture.class);
		manager.load("assets/imageButton/settingsIcon.png", Texture.class);
		manager.load("assets/imageButton/settingsIcon.png", Texture.class);

		//MasterMenuImageButtons

		manager.load("assets/images/mainMenu/ButtonIcons/bambooBlasterUP.png", Texture.class);
		manager.load("assets/images/mainMenu/ButtonIcons/bambooBlasterDOWN.png", Texture.class);
		currentlyLoading = "Game menu ImageButtons";
		manager.load( "assets/images/mainMenu/ButtonIcons/speedSumsUP.png", Texture.class);
		manager.load( "assets/images/mainMenu/ButtonIcons/speedSumsDOWN.png", Texture.class);
		manager.load("assets/images/mainMenu/ButtonIcons/spaceBlasterUP.png", Texture.class);
		manager.load( "assets/images/mainMenu/ButtonIcons/spaceBlasterDown.png", Texture.class);
		manager.load( "assets/images/mainMenu/ButtonIcons/balloonGameIconUP.png", Texture.class);
		manager.load("assets/images/mainMenu/ButtonIcons/balloonGameIconDOWN.png", Texture.class);
		manager.load( "assets/images/mainMenu/ButtonIcons/numberGamesUP.png", Texture.class);
		manager.load("assets/images/mainMenu/ButtonIcons/numberGamesDOWN.png", Texture.class);
		manager.load("assets/images/mainMenu/ButtonIcons/wordGamesUP.png", Texture.class);
		manager.load("assets/images/mainMenu/ButtonIcons/wordGamesDOWN.png", Texture.class);
		manager.load("assets/images/mainMenu/ButtonIcons/logicGamesUP.png", Texture.class);
		manager.load("assets/images/mainMenu/ButtonIcons/logicGamesDOWN.png", Texture.class);
		manager.load("assets/images/mainMenu/ButtonIcons/robotRulesUP.png", Texture.class);
		manager.load("assets/images/mainMenu/ButtonIcons/robotRulesDOWN.png", Texture.class);
		manager.load("assets/images/mainMenu/ButtonIcons/yesUp.png", Texture.class);
		manager.load("assets/images/mainMenu/ButtonIcons/yesDown.png", Texture.class);
		manager.load("assets/images/mainMenu/ButtonIcons/noUp.png", Texture.class);
		manager.load("assets/images/mainMenu/ButtonIcons/noDown.png", Texture.class);

		manager.load("assets/imageButton/textField1.png", Texture.class);
		manager.load("assets/imageButton/textField1.png", Texture.class);
		currentlyLoading = "Game menu TextField";
		manager.load("assets/images/global/musicIcon/soundOn.png", Texture.class);
		manager.load("assets/images/global/musicIcon/soundOff.png", Texture.class);



		manager.load("assets/sounds/mainMenuMusic.mp3", Sound.class);
	}

    private static void loadGameAssets(){
		
		//RobotRules Assets
		loadRobotRulesAssets();

		//Bamboo Blaster Textures 
		currentlyLoading = "Bamboo Blaster Textures";
		manager.load(menuBackgroundFileName, Texture.class);

		manager.load(jungleBackgroundFileNameFileName, Texture.class);
		manager.load(crossHairFileName, Texture.class);
		manager.load(coconutFileName, Texture.class);
		manager.load(leafTargetFileName, Texture.class);
		manager.load(brokenLeafFileName, Texture.class);
		manager.load(bambooBlasterFileName, Texture.class);
		
		//Master Menu Textures
		currentlyLoading = "Global Menu Textures";

		//button textures 
		currentlyLoading = "Button Set Textures";

		//SpeedSums Textures
		currentlyLoading = "Speed Sums Textures";
		manager.load("assets/images/speedSums/imageButtons/easyUp.png", Texture.class);
		manager.load("assets/images/speedSums/imageButtons/easyDown.png", Texture.class);
		manager.load("assets/images/speedSums/imageButtons/mediumUp.png", Texture.class);
		manager.load("assets/images/speedSums/imageButtons/mediumDown.png", Texture.class);
		manager.load("assets/images/speedSums/imageButtons/hardUp.png", Texture.class);
		manager.load("assets/images/speedSums/imageButtons/hardDown.png", Texture.class);
		manager.load("assets/images/speedSums/imageButtons/yesUp.png", Texture.class);
		manager.load("assets/images/speedSums/imageButtons/yesDown.png", Texture.class);
		manager.load("assets/images/speedSums/imageButtons/noUp.png", Texture.class);
		manager.load("assets/images/speedSums/imageButtons/noDown.png", Texture.class);
		manager.load("assets/images/speedSums/imageButtons/restartUp.png",Texture.class);
		manager.load("assets/images/speedSums/imageButtons/restartDown.png", Texture.class);
		manager.load("assets/images/speedSums/imageButtons/checkUp.png",Texture.class);
		manager.load("assets/images/speedSums/imageButtons/checkDown.png", Texture.class);
		manager.load("assets/images/speedSums/imageButtons/levelSelectUp.png",Texture.class);
		manager.load("assets/images/speedSums/imageButtons/levelSelectDown.png", Texture.class);
		manager.load("assets/images/speedSums/imageButtons/exitUp.png",Texture.class);
		manager.load("assets/images/speedSums/imageButtons/exitDown.png", Texture.class);
		manager.load("assets/images/speedSums/speedSumsBackground.png", Texture.class);
		manager.load("assets/images/speedSums/imageButtons/correctTx.png", Texture.class);
		manager.load("assets/images/speedSums/imageButtons/inCorrectTx.png", Texture.class);
		manager.load("assets/images/speedSums/imageButtons/tryAgainTx.png", Texture.class);
		
		//Space Blaster
		currentlyLoading = "Space Blaster Textures";
		manager.load("assets/images/Space Blaster/spaceBlasterLogo.png", Texture.class);
		manager.load("assets/images/Space Blaster/starBackground.jpg", Texture.class);
		manager.load("assets/images/Space Blaster/crossHair.png", Texture.class);
		manager.load("assets/images/Space Blaster/lazerBeamGreen.png", Texture.class);
		manager.load("assets/images/Space Blaster/meteor.png", Texture.class);
		manager.load("assets/images/Space Blaster/cockpit.png", Texture.class);
		manager.load("assets/images/Space Blaster/leftGun.png", Texture.class);
		manager.load("assets/images/Space Blaster/rightGun.png", Texture.class);
		manager.load("assets/images/Space Blaster/ship.png", Texture.class);
		for(int i = 0; i<6;i++){
			manager.load("assets/images/Space Blaster/fuelTank/"+i+".png", Texture.class);
		}
		
		//Falling Letters
		currentlyLoading = "Baloon Game Textures";
		manager.load("assets/images/fallingLetters/Keyboard.png", Texture.class);
		manager.load("assets/images/fallingLetters/b3.png", Texture.class);
		manager.load("assets/images/fallingLetters/carnivalBackgroundHR.png", Texture.class);
		manager.load("assets/images/fallingLetters/gameOverCloud.png", Texture.class);
		manager.load("assets/images/fallingLetters/baloon.png", Texture.class);
		manager.load("assets/images/fallingLetters/carnivalBackgroundGameOverHR.png", Texture.class);
		manager.load("assets/images/fallingLetters/carnivalBackgroundSplash.png", Texture.class);
		
		for(int i = 0; i<5;i++){
			manager.load("assets/images/fallingLetters/baloonPop/"+i+".png", Texture.class);
		}

	}

	private static void loadRobotRulesAssets(){
		//Robot Rules
		currentlyLoading = "Robot Rules Textures";
		manager.load("assets/images/RobotLogic/startBlock.png", Texture.class);
		manager.load("assets/images/RobotLogic/BlueBackgorund.png", Texture.class);
		manager.load("assets/images/RobotLogic/path.png", Texture.class);
		manager.load("assets/images/RobotLogic/pathHole.png", Texture.class);
		manager.load("assets/images/RobotLogic/finishLine.png", Texture.class);
		manager.load("assets/images/RobotLogic/coin.png", Texture.class);

		manager.load("assets/images/RobotLogic/run.png", Texture.class);
		manager.load("assets/images/RobotLogic/resetArrow.png", Texture.class);
		manager.load("assets/images/RobotLogic/clearMap.png", Texture.class);
		manager.load("assets/images/RobotLogic/nextLevelArrow.png", Texture.class);
		manager.load("assets/images/RobotLogic/arrows/rightArrow.png", Texture.class);
		manager.load("assets/images/RobotLogic/arrows/leftArrow.png", Texture.class);
		manager.load("assets/images/RobotLogic/arrows/upArrow.png", Texture.class);
		manager.load("assets/images/RobotLogic/arrows/downArrow.png", Texture.class);
		manager.load("assets/images/RobotLogic/arrows/turnAroundArrow.png", Texture.class);
		manager.load("assets/images/RobotLogic/arrows/directionSelector.png", Texture.class);
		manager.load("assets/images/RobotLogic/robot/robotDown.png", Texture.class);
		manager.load("assets/images/RobotLogic/robot/robotUp.png", Texture.class);
		manager.load("assets/images/RobotLogic/robot/robotLeft.png", Texture.class);
		manager.load("assets/images/RobotLogic/robot/robotRight.png", Texture.class);

		//Image Buttons
		manager.load("assets/images/RobotLogic/imageButtons/upUp.png",Texture.class);
		manager.load("assets/images/RobotLogic/imageButtons/upDown.png",Texture.class);
		manager.load("assets/images/RobotLogic/imageButtons/downUp.png",Texture.class);
		manager.load("assets/images/RobotLogic/imageButtons/downDown.png",Texture.class);
		manager.load("assets/images/RobotLogic/imageButtons/leftUp.png",Texture.class);
		manager.load("assets/images/RobotLogic/imageButtons/leftDown.png",Texture.class);
		manager.load("assets/images/RobotLogic/imageButtons/rightUp.png",Texture.class);
		manager.load("assets/images/RobotLogic/imageButtons/rightDown.png",Texture.class);

		manager.load("assets/images/RobotLogic/imageButtons/upSelected.png",Texture.class);
		manager.load("assets/images/RobotLogic/imageButtons/downSelected.png",Texture.class);
		manager.load("assets/images/RobotLogic/imageButtons/leftSelected.png",Texture.class);
		manager.load("assets/images/RobotLogic/imageButtons/rightSelected.png",Texture.class);

		manager.load("assets/images/RobotLogic/imageButtons/playUp.png",Texture.class);
		manager.load("assets/images/RobotLogic/imageButtons/playDown.png",Texture.class);
		manager.load("assets/images/RobotLogic/imageButtons/playSelected.png",Texture.class);

		manager.load("assets/images/RobotLogic/imageButtons/stopUp.png",Texture.class);
		manager.load("assets/images/RobotLogic/imageButtons/stopDown.png",Texture.class);
		manager.load("assets/images/RobotLogic/imageButtons/stopSelected.png",Texture.class);

		manager.load("assets/images/RobotLogic/imageButtons/resetUp.png",Texture.class);
		manager.load("assets/images/RobotLogic/imageButtons/resetDown.png",Texture.class);
		manager.load("assets/images/RobotLogic/imageButtons/resetSelected.png",Texture.class);

		manager.load("assets/images/RobotLogic/imageButtons/nextLevelUp.png",Texture.class);
		manager.load("assets/images/RobotLogic/imageButtons/nextLevelDown.png",Texture.class);




	}

    private static void loadSounds(){
		

		currentlyLoading = "Game Sounds";
		//Speed Sums
		manager.load("assets/sounds/3SecondCountdown.mp3", Sound.class);
		manager.load("assets/sounds/5SecondCountdown.mp3", Sound.class);
		
		//Bamboo Blaster
		currentlyLoading = "Bamboo Blaster Sounds";
		manager.load("assets/sounds/bambooblasterSoundBlaster.mp3", Sound.class);
		manager.load("assets/sounds/bambooblasterSoundPass.mp3", Sound.class);
		manager.load("assets/sounds/bambooblasterSoundFail.mp3", Sound.class);
		manager.load("assets/sounds/bambooblasterSoundHit.mp3", Sound.class);
		manager.load("assets/sounds/bambooblasterSoundCheck.mp3", Sound.class);
		manager.load("assets/sounds/bambooblasterSoundMusic.mp3", Sound.class);
		
		//Space Blaster
		currentlyLoading = "Space Blaster Sounds";
		manager.load("assets/sounds/lazerBlast.mp3", Sound.class);
		manager.load("assets/sounds/correctAnswer.mp3", Sound.class);
		manager.load("assets/sounds/warningSound.mp3", Sound.class);
		manager.load("assets/sounds/gameOverSound.mp3", Sound.class);
		//manager.load("sounds/sounds/lazerGun.mp3", Sound.class);
		manager.load("assets/sounds/spaceBlasterTheme.mp3", Sound.class);
		
		//Falling letters
		currentlyLoading = "Falling letters Sounds";
		manager.load("assets/sounds/baloon/circusMusicQuick.mp3",Sound.class);
		manager.load("assets/sounds/shortPop.mp3",Sound.class);
		manager.load("assets/sounds/gameOver.mp3",Sound.class);
	}
	
	private static void loadSkins(){
		currentlyLoading = "Global Skins";
		manager.load(rainbowSkinFileName, Skin.class);
		manager.load(comicSkinFileName, Skin.class);
		manager.load(levelPaneSkinFileName, Skin.class);
		manager.load(flatEarthSkinFileName, Skin.class);
		manager.load(cleanCrispySkinFileName, Skin.class);
		
		currentlyLoading = "Compleate";
	}
	
	public static String getCurrentLoadItem(){
		return currentlyLoading;
	}
	
	public static void dispose(){
		manager.dispose();
	}
}
