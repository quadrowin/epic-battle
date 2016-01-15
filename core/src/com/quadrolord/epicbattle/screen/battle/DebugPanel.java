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
                40,
                40
        );
        stage.addActor(btnSaveProfile);
        btnSaveProfile.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("", "pause click");
                screen.getGame().getProfileManager().saveProfile();
            }

        });
    }

}
