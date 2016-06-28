package com.quadrolord.epicbattle.screen;

import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.EpicBattle;
import com.quadrolord.epicbattle.screen.slider.SliderList;
import com.quadrolord.epicbattle.screen.upgrading.UpgradingSliderContent;

/**
 * Created by Quadrowin on 11.06.2016.
 */
public class UnitsUpgradingScreen extends AbstractScreen {

    public UnitsUpgradingScreen(EpicBattle adapter) {
        super(adapter);
        initFitViewport();

        new SliderList(this, new UpgradingSliderContent(this));
    }

    @Override
    public void draw(float delta) {
        mStage.act(delta);
        mStage.draw();
    }

    @Override
    public void update(float delta) {
        mStage.act(delta);
    }

}
