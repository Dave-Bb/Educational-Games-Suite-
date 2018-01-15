/*
This class is used to create an exit confirmation when needed
This includes a table, buttons and a way to check if the exit has been actioned
 */
package com.games.wordgames.handlers;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ExitConfirmation {
	
	private Table exitConfirmTable;
	private ImageButton yesButton;
	private ImageButton noButton;
	private boolean yesExit;
	private boolean noExit;
	private boolean isOn;
	
	
	public ExitConfirmation(){
		
		initButtons();
		yesExit = false;
		noExit = false;
		
		exitConfirmTable = new Table();
		exitConfirmTable.setPosition(400, 250);
		exitConfirmTable.add(yesButton).pad(30);
		exitConfirmTable.add(noButton).pad(30);

	}
	
	public Table getTable(){
		return exitConfirmTable;
	}
	
	public boolean clickYes(){
		return yesExit;
	}
	
	public boolean clickNo(){
		return noExit;
	}
	
	public void reset(){
		noExit = false;
		yesExit = false;
		setExitOff();
	}
	
	private void initButtons(){
		String yesUp = "assets/images/mainMenu/ButtonIcons/yesUp.png";
		String yesDown= "assets/images/mainMenu/ButtonIcons/yesDown.png";
		yesButton= HelpMethods.createImageButton(yesUp,yesDown);
		
		String noUp = "assets/images/mainMenu/ButtonIcons/noUp.png";
		String noDown= "assets/images/mainMenu/ButtonIcons/noDown.png";
		noButton= HelpMethods.createImageButton(noUp,noDown);
		
		
		
		yesButton.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				yesExit = true;
				return false;
			}
		});
		
		noButton.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				noExit = true;
				return false;
			}
		});
	}

	public void setExitOn(){
		isOn = true;
	}

	public void setExitOff(){
		isOn = false;
	}

	public boolean displayExitConfirmation(){
		return isOn;
	}
	

}
