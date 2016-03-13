package com.quadrolord.epicbattle.screen;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.epicbattle.EpicBattle;
import com.quadrolord.epicbattle.screen.campaigns.CampaignsList;

/**
 * Created by Quadrowin on 13.01.2016.
 */
public class CampaignSelectScreen extends AbstractScreen {

    public CampaignSelectScreen(EpicBattle adapter) {
        super(adapter);
        initFitViewport();

        new CampaignsList(this, mGame.getBattleGame().getCampaignManager().getCampaigns());

        TextButton btnToMyTown = new TextButton("Enter your city", mSkin.get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnToMyTown.setBounds(210, 190, 180, 50);
        mStage.addActor(btnToMyTown);
        btnToMyTown.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                mAdapter.switchToScreen(MyTownScreen.class);
            }

        });
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
