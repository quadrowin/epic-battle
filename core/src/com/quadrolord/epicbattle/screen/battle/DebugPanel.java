package com.quadrolord.epicbattle.screen.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Created by Quadrowin on 15.01.2016.
 */
public class DebugPanel extends Group {

    public DebugPanel(final AbstractScreen screen, Stage stage) {
        TextButton btnSaveProfile = new TextButton("Save Profile", screen.getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnSaveProfile.setBounds(
                300,
                250,
                80,
                40
        );
        stage.addActor(btnSaveProfile);
        btnSaveProfile.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("debug panel", "click Save Profile");
                screen.getGame().getProfileManager().saveProfile();
            }

        });


        TextButton btnLoadProfile = new TextButton("Load Profile", screen.getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnLoadProfile.setBounds(
                200,
                250,
                80,
                40
        );
        stage.addActor(btnLoadProfile);
        btnLoadProfile.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("debug panel", "click Load Profile");
                screen.getGame().getProfileManager().getProfile();
            }

        });
    }

}
