package com.quadrolord.epicbattle.screen;

import com.quadrolord.epicbattle.EpicBattle;
import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.screen.campaigns.CampaignsList;

/**
 * Created by Quadrowin on 13.01.2016.
 */
public class CampaignSelectScreen extends AbstractScreen {

    public CampaignSelectScreen(EpicBattle adapter, Game game) {
        super(adapter, game);
        initFitViewport();

        new CampaignsList(this, game.getCampaignManager().getCampaigns());
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
