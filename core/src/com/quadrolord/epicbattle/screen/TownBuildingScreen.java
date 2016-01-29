package com.quadrolord.epicbattle.screen;

/**
 * Created by Quadrowin on 29.01.2016.
 */
public class TownBuildingScreen extends AbstractScreen {

    private AbstractScreen mParentScreen;

    public TownBuildingScreen(AbstractScreen parentScreen) {
        super(parentScreen.getAdapter());
        mParentScreen = parentScreen;


    }


    @Override
    public void draw(float delta) {
        mParentScreen.draw(0);

        mStage.act(delta);
        mStage.draw();
    }

    @Override
    public void update(float delta) {

    }

}
