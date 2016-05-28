package com.quadrolord.epicbattle.screen;

import com.quadrolord.epicbattle.logic.town.MyTown;
import com.quadrolord.epicbattle.screen.town.slider.BuildSlider;

/**
 * Created by Quadrowin on 20.02.2016.
 */
public class NewBuildingScreen extends com.quadrolord.ejge.view.AbstractScreen {

    private com.quadrolord.ejge.view.AbstractScreen mPausedScreen;

    public NewBuildingScreen(com.quadrolord.ejge.view.AbstractScreen pausedScreen, MyTown town) {
        super(pausedScreen.getAdapter());
        mPausedScreen = pausedScreen;
        initFitViewport();

        new BuildSlider(this, town);
    }

    @Override
    public void draw(float delta) {
        mPausedScreen.draw(0);
        mStage.act(delta);
        mStage.draw();
    }

    public com.quadrolord.ejge.view.AbstractScreen getPausedScreen() {
        return mPausedScreen;
    }

    @Override
    public void update(float delta) {
        mStage.act(delta);
    }
}
