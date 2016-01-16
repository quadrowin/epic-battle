package com.quadrolord.epicbattle.screen;

import com.quadrolord.epicbattle.EpicBattle;

/**
 * Created by Quadrowin on 16.01.2016.
 */
public class MyTownScreen extends AbstractScreen {

    public MyTownScreen(EpicBattle adapter) {
        super(adapter);
        initFitViewport();

    }

    @Override
    public void draw(float delta) {
        mStage.act(delta);
        mStage.draw();
    }

    @Override
    public void update(float delta) {

    }
}
