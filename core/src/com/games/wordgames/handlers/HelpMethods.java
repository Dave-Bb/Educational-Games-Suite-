//VArious static helper methods used to creating Scene2D image buttons
// as well as fonts and string arrays from .txt files

package com.games.wordgames.handlers;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.games.wordgames.main.Game;

public class HelpMethods {
	
	//public static final float ASP = 1.778f;

	public static ImageButton createImageButton(String fileLocation, String downLocation){
	    //This returns a Scene2D Image button
        //The first argument is the unhighlight image
        //The second is used when mouse is over the button
		 TextureRegionDrawable drawable =getTextureRegionDrawable(fileLocation);
		 ImageButton imageButton = new ImageButton(drawable); //Set the button up
		 imageButton.getStyle().imageUp = drawable;
		 imageButton.getStyle().imageOver = getTextureRegionDrawable(downLocation);
		
		 return imageButton;
	}
	
	public static ImageButton createImageButtonToggleSwitch(String fileLocation, String downLocation){
	    //Creates a Scene2D image button with two images
        //One image for the UP position, and one for the DOWN
        //Images used must be loaded in Asset manager before hand NB
        TextureRegionDrawable drawable =getTextureRegionDrawable(fileLocation);
        ImageButton imageButton = new ImageButton(drawable); //Set the button up
        imageButton.getStyle().imageUp = drawable;
        imageButton.getStyle().checked = getTextureRegionDrawable(downLocation);
		 //imageButton.getStyle().checkedOffsetX = 4f;

		 return imageButton;
	}

    public static ImageButton createImageButtonToggleSwitchHightlight(String upLocation, String downLocation,String selectedLocation){
        //Creates a Scene2D image button with two images
        //One image for the UP position, and one for the DOWN
        //Images used must be loaded in Asset manager before hand NB
        TextureRegionDrawable drawable =getTextureRegionDrawable(upLocation);
        ImageButton imageButton = new ImageButton(drawable); //Set the button up
        imageButton.getStyle().imageUp = drawable;
        imageButton.getStyle().imageOver = getTextureRegionDrawable(downLocation);
        imageButton.getStyle().checked = getTextureRegionDrawable(selectedLocation);
        //imageButton.getStyle().checkedOffsetX = 4f;

        return imageButton;
    }

	public static TextureRegionDrawable getTextureRegionDrawable(String fileLocation){
	    //Creates a Texture Region Drawable, for use in image buttons.
        //Used with assetManager only
		Texture texture = Assets.manager.get(fileLocation, Texture.class);
		 TextureRegion textureReagon = new TextureRegion(texture);
		 TextureRegionDrawable drawable = new TextureRegionDrawable(textureReagon);

		 return drawable;
	}

    public static TextureRegionDrawable getTextureRegionDrawableDirect(String fileLocation){
        //Creates a Texture Region Drawable, for use in image buttons.
        //Used with direct file path, not asset manager
        Texture texture =  new Texture(Gdx.files.internal(fileLocation));
        TextureRegion textureReagon = new TextureRegion(texture);
        TextureRegionDrawable drawable = new TextureRegionDrawable(textureReagon);
        return drawable;
    }
	
	public static BitmapFont createFont(String fileLocation, int size){
	    //Creates a new BitmapFont object using FreeTypeFontGenerator
        //Arguments are file path and size
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fileLocation));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = size;
		BitmapFont newFont = generator.generateFont(parameter); // font size 12 pixels
		newFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		return newFont;
	}

	//The below methods are used to get the relative mouse position
	//Possibly not used. WILL CONFIRM
	public static float getAspectMousePosX(int x){
		Rectangle viewPort = Game.getViewPort();
		return (x*(Game.V_WIDTH/viewPort.width))-viewPort.x;
	}

	public static float getAspectMousePosY(int y){
		Rectangle viewPort = Game.getViewPort();
		return ((Gdx.graphics.getHeight()-y)*((Game.V_WIDTH/viewPort.width)));
	}
	
	public static float getAspectMousePosY(float y){
		Rectangle viewPort = Game.getViewPort();
		return ((Gdx.graphics.getHeight()-y)*((Game.V_WIDTH/viewPort.width)));
	}

	public static Vector2 scaleMouse(int x, int y) {
		Vector2 pos = new Vector2(getAspectMousePosX(x),getAspectMousePosY(y));
		return pos;
	}
	
	public static Vector3 getMousePosInGameWorld(OrthographicCamera camera, int xPos, int yPos) {
		 return camera.unproject(new Vector3(xPos, yPos, 0));
		}
	
	

	public static Array<String> getWords(String filePath){
		//This method is used to get the words from a .txt
		//File and add them to an array for use in Space Blaster
		//Each part of seach(nouns,verbs....) have their own
		//Text file
		Scanner fileIn = null;
		Array<String> words = new Array<String>();
		try {
			File file = new File(filePath);
			fileIn = new Scanner(file);
			System.out.println("Attempting to read from file in: "+file.getCanonicalPath());
			while (fileIn.hasNext()){
				String readString = fileIn.next();
				words.add(readString);
				System.out.println("READ STRING: "+readString);
			}
			fileIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//FileHandle handle =Gdx.files.internal("textFiles/spaceBlasterWords/nouns1.txt");
		return words;
	}
	
	public static void main(String[] args) throws IOException{
		//Main Method used for testing purposes for future methods
		
		
	}
	
	
}
