package com.games.wordgames.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.games.wordgames.main.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Game.TITLE;
		config.width = Game.V_WIDTH;
		config.height = Game.V_HEIGHT;
		//config.fullscreen = true;

		
		new LwjglApplication(new Game(), config);
		
		int R_WIDTH = config.width;
		int R_HEIGHT = Gdx.graphics.getHeight();
		System.out.println("The Gaming Suite - Launch");

	}
}
