package com.quadrolord.epicbattle.screen;

import com.quadrolord.epicbattle.EpicBattle;
import com.quadrolord.epicbattle.screen.campaigns.CampaignsList;

/**
 * Created by Quadrowin on 13.01.2016.
 */
public class CampaignSelectScreen extends AbstractScreen {

    public CampaignSelectScreen(EpicBattle adapter) {
        super(adapter);
        initFitViewport();

        new CampaignsList(this, mGame.getCampaignManager().getCampaigns());
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
