package com.quadrolord.epicbattle.screen.levels;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.epicbattle.screen.AbstractScreen;
import com.quadrolord.epicbattle.screen.CampaignSelectScreen;

/**
 * Created by Quadrowin on 13.01.2016.
 */
public class LeaveCampaignButton {

    public LeaveCampaignButton(final AbstractScreen screen) {
        TextButton btnLevel = new TextButton("Leave campaign", screen.getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnLevel.setBounds(
                210,
                190,
                180,
                50
        );
        screen.getStage().addActor(btnLevel);
        btnLevel.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.getAdapter().switchToScreen(CampaignSelectScreen.class);
            }

        });
    }

}
