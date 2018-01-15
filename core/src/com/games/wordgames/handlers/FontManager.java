//This class is just to manage all the different fonts
//It is a singleton with static variables for each game state to use


package com.games.wordgames.handlers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class FontManager {
    private static FontManager instance = null;
    public static int protectME;

    public static  BitmapFont fontLarge;
    public static BitmapFont fontMedium;
    public static BitmapFont fontSmall;
    public static BitmapFont digitalTimerFont;
    public static BitmapFont comicFont;
    public static BitmapFont jungleFont;
    public static BitmapFont slackeyFont;
    public static BitmapFont toolTipFont;
    public static BitmapFont toolTipFontLarge;
    public static BitmapFont sansRegularFont;
    public static BitmapFont circusFontMedium;
    public static BitmapFont circusFontLarge;
    public static BitmapFont curcusFontSmall;
    public static BitmapFont spaceFontMedium;
    public static BitmapFont spaceFontLarge;
    public static BitmapFont slackeyFontMedium;
    public static BitmapFont slackeyFontSmall;
    public static BitmapFont hidiSpeedSmall;
    public static BitmapFont digitalTimerFontLarge;
    public static BitmapFont hidiSpeedLarge;
    public static BitmapFont hidiSpeedMedium;
    public static BitmapFont jungleFontMedium;
    public static BitmapFont firaSansMedium;

    public static Color purpleColour;

    private FontManager() {
        loadFonts();
    }

    public static FontManager getInstance() {
        if(instance == null) {
            instance = new FontManager();
        }
        return instance;
    }

    private void loadFonts(){
        protectME = 1;
        fontLarge = HelpMethods.createFont("assets/fonts/DroidSans-Bold.ttf", 56);
        fontLarge.getData().setScale(0.91f);


        fontMedium = HelpMethods.createFont("assets/fonts/DroidSans-Bold.ttf", 32);
        fontMedium.getData().setScale(0.8f);

        fontSmall = HelpMethods.createFont("assets/fonts/DroidSans-Bold.ttf", 24);
        fontSmall.getData().setScale(0.8f);

        digitalTimerFont = HelpMethods.createFont("assets/fonts/Digital Dismay.otf", 48);
        digitalTimerFontLarge = HelpMethods.createFont("assets/fonts/Digital Dismay.otf", 92);

        comicFont = HelpMethods.createFont("assets/fonts/AGENTORANGE.ttf", 60);
        comicFont.setColor(0, 0, 0, 0.8f);

        jungleFont = HelpMethods.createFont("assets/fonts/JungleFever.ttf", 32);
        jungleFont.setColor(0, 0, 0, 0.8f);



        jungleFontMedium = HelpMethods.createFont("assets/fonts/JungleFever.ttf", 42);
        jungleFontMedium.setColor(1, 1, 1, 0.8f);

        purpleColour = new Color(100,99,176,1);

        slackeyFont = HelpMethods.createFont("assets/fonts/Slackey.ttf", 120);//slackeyFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        slackeyFontMedium = HelpMethods.createFont("assets/fonts/Slackey.ttf", 86);
        slackeyFontSmall = HelpMethods.createFont("assets/fonts/Slackey.ttf", 32);
        slackeyFontSmall.setColor(1,1,1,1);

        toolTipFont = HelpMethods.createFont("assets/fonts/scb.ttf", 28);
        toolTipFont.setColor(0,0,0,1);

        toolTipFontLarge = HelpMethods.createFont("assets/fonts/scb.ttf", 40);
        toolTipFontLarge.setColor(0,0,0,1);

        sansRegularFont = HelpMethods.createFont("assets/fonts/SansRegular.ttf", 45);

        sansRegularFont.setColor(0,0,0,1);

        circusFontMedium = HelpMethods.createFont("assets/fonts/edmunds.ttf", 38);
        circusFontMedium.setColor(0,0,0,1);

        circusFontLarge = HelpMethods.createFont("assets/fonts/edmunds.ttf", 100);
        circusFontLarge.setColor(0,0,0,1);

        curcusFontSmall = HelpMethods.createFont("assets/fonts/edmunds.ttf", 28);
        curcusFontSmall.setColor(0,0,0,1);

        spaceFontMedium = HelpMethods.createFont("assets/fonts/nasalization-rg.ttf", 48);
        spaceFontLarge= HelpMethods.createFont("assets/fonts/nasalization-rg.ttf", 72);

        hidiSpeedSmall = HelpMethods.createFont("assets/fonts/hemiSpeed.ttf", 36);
        hidiSpeedSmall.setColor(1,1,1,1);

        hidiSpeedMedium = HelpMethods.createFont("assets/fonts/hemiSpeed.ttf", 42);
        hidiSpeedMedium.setColor(0,0,0,1);

        hidiSpeedLarge= HelpMethods.createFont("assets/fonts/hemiSpeed.ttf", 92);
        hidiSpeedLarge.setColor(0,0,0,1);

        firaSansMedium = HelpMethods.createFont("assets/fonts/fira-sans.book.ttf",106);
        firaSansMedium.setColor(0,0,0,1);


    }
}
