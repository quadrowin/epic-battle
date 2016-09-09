package com.quadrolord.epicbattle.screen;

/**
 * Screen Element Size
 * Created by Goorus on 09.09.2016.
 */
public class SES {


    public static final int BUTTON_HEIGHT = 80;

    public static final int BUTTON_WIDTH = 260;

    public static final int SCREEN_BORDER = 10;

    public static final int SCREEN_HEIGHT = 600;

    public static final int SCREEN_WIDTH = 800;

    public static final int F = 2;

    /**
     * @return X coordinate for button on the right of the screen
     */
    public static int buttonRight() {
        return SCREEN_WIDTH - BUTTON_WIDTH - SCREEN_BORDER;
    }

}
