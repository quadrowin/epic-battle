package com.quadrolord.epicbattle.screen.campaigns;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.epicbattle.logic.campaign.AbstractCampaign;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.screen.LevelSelectScreen;

/**
 * Created by Quadrowin on 13.01.2016.
 */
public class CampaignsList extends Group {

    public CampaignsList(final AbstractScreen screen, final AbstractCampaign[] campaigns) {
        for (int i = 0; i < campaigns.length; i++) {
            final AbstractCampaign campaign = campaigns[i];
            TextButton btnLevel = new TextButton(campaign.getName(), screen.getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
            btnLevel.setBounds(
                    10,
                    10 + i * 60,
                    180,
                    50
            );
            screen.getStage().addActor(btnLevel);
            btnLevel.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    LevelSelectScreen levelsScreen = new LevelSelectScreen(screen.getAdapter(), campaign);
                    screen.getAdapter().switchToScreen(levelsScreen, true);
                }

            });
        }
    }

}
