package com.quadrolord.epicbattle.screen;

import com.quadrolord.epicbattle.EpicBattle;
import com.quadrolord.epicbattle.logic.campaign.AbstractCampaign;
import com.quadrolord.epicbattle.screen.levels.LeaveCampaignButton;
import com.quadrolord.epicbattle.screen.levels.LevelsList;

/**
 * Created by Quadrowin on 13.01.2016.
 */
public class LevelSelectScreen extends AbstractScreen {

    private AbstractCampaign mCampaign;

    public LevelSelectScreen(EpicBattle adapter, AbstractCampaign campaign) {
        super(adapter);
        initFitViewport();
        mCampaign = campaign;

        new LevelsList(this, mCampaign.getLevels());

        new LeaveCampaignButton(this);
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
