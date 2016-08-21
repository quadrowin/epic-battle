package com.quadrolord.epicbattle.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.ejge.AbstractGameAdapter;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.RM;
import com.quadrolord.epicbattle.screen.menu.component.BackgroundStage;

/**
 * Created by Goorus on 16.08.2016.
 */
public class SettingsScreen extends AbstractScreen {

    private BackgroundStage mBgStage = new BackgroundStage();

    public SettingsScreen(AbstractScreen parentScreen) {
        super(parentScreen);
        initFitViewport();

        mBgStage.loadImage(this, "Bg/menu/settings-bg.jpg");

        TextButton btnSettings = new TextButton("Back", RM.getTextButtonStyle());
        btnSettings.setBounds(20, 20, 240, 80);
        mStage.addActor(btnSettings);
        btnSettings.addListener(new ClickListener() {

            public void clicked (InputEvent event, float x, float y) {
                mAdapter.switchToScreen(mParentScreen, true);
            }

        });

        CheckBox cbSounds = new CheckBox("", RM.getCheckBoxStyle());
        cbSounds.setBounds(20, 500, 60, 60);
        mStage.addActor(cbSounds);

        Label lblSounds = new Label("Sound effects", RM.getLabelStyle());
        lblSounds.setBounds(100, 500, 200, 60);
        mStage.addActor(lblSounds);

        CheckBox cbMusic = new CheckBox("", RM.getCheckBoxStyle());
        cbMusic.setBounds(20, 400, 60, 60);
        mStage.addActor(cbMusic);

        Label lblMusic = new Label("Music", RM.getLabelStyle());
        lblMusic.setBounds(100, 400, 200, 60);
        mStage.addActor(lblMusic);

        TextButton btnGooglePlayLink = new TextButton("GooglePlay", RM.getTextButtonStyle());
        btnGooglePlayLink.setBounds(540, 20, 240, 80);
        mStage.addActor(btnGooglePlayLink);
        btnGooglePlayLink.addListener(new ClickListener() {

            public void clicked (InputEvent event, float x, float y) {

            }

        });
    }

    @Override
    public void draw(float delta) {
        mBgStage.draw();
        mStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        mStage.draw();
    }

    @Override
    public void update(float delta) {
        mStage.act(delta);
    }

}
