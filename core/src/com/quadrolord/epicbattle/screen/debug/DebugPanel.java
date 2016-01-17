package com.quadrolord.epicbattle.screen.debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Created by Quadrowin on 15.01.2016.
 */
public class DebugPanel extends Group {

    private boolean mOpened = false;

    private Group mPanel;

    private Actor mToggleButton;

    public DebugPanel(final AbstractScreen screen, Stage stage) {

        Texture texture = new Texture("ui/panel-64.png");
        NinePatch ninePatch = new NinePatch(
                texture,
                16, 16, 16, 16
        );
        Drawable drBg = new NinePatchDrawable(ninePatch);

        mPanel = new TextButton("", new TextButton.TextButtonStyle(drBg, drBg, null, screen.getSkin().getFont("default")));
        mPanel.setBounds(30, 30, 340, 240);
        mPanel.setVisible(mOpened);
        stage.addActor(mPanel);

        TextButton btnSaveProfile = new TextButton("Save Profile", screen.getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnSaveProfile.setBounds(
                10,
                10,
                100,
                40
        );
        mPanel.addActor(btnSaveProfile);
        btnSaveProfile.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("debug panel", "click Save Profile");
                screen.getGame().getProfileManager().saveProfile();
            }

        });


        TextButton btnLoadProfile = new TextButton("Load Profile", screen.getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnLoadProfile.setBounds(
                110,
                10,
                100,
                40
        );
        mPanel.addActor(btnLoadProfile);
        btnLoadProfile.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("debug panel", "click Load Profile");
                screen.getGame().getProfileManager().getProfile();
            }

        });

        mToggleButton = new TextButton("debug", screen.getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
        mToggleButton.setBounds(
                250,
                250,
                90,
                40
        );
        stage.addActor(mToggleButton);
        mToggleButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                mOpened = !mOpened;
                mPanel.setVisible(mOpened);
            }

        });
    }

}
