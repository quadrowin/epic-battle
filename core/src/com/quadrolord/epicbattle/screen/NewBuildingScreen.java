package com.quadrolord.epicbattle.screen;

import com.quadrolord.epicbattle.logic.town.MyTown;
import com.quadrolord.epicbattle.screen.town.BuildSlider;

/**
 * Created by Quadrowin on 20.02.2016.
 */
public class NewBuildingScreen extends AbstractScreen {

    private AbstractScreen mPausedScreen;

    public NewBuildingScreen(AbstractScreen pausedScreen, MyTown town) {
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

    public AbstractScreen getPausedScreen() {
        return mPausedScreen;
    }

    @Override
    public void update(float delta) {
        mStage.act(delta);
    }
}
