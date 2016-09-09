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
import com.quadrolord.epicbattle.screen.SES;
import com.quadrolord.epicbattle.screen.menu.component.BackButton;
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

        BackButton.create(this, MainMenuScreen.class);

        CheckBox cbSounds = new CheckBox("", RM.getCheckBoxStyle());
        cbSounds.setBounds(20, 500, SES.BUTTON_HEIGHT, SES.BUTTON_HEIGHT);
        mStage.addActor(cbSounds);

        Label lblSounds = new Label("Sound effects", RM.getLabelStyle());
        lblSounds.setBounds(120, 500, 200, SES.BUTTON_HEIGHT);
        mStage.addActor(lblSounds);

        CheckBox cbMusic = new CheckBox("", RM.getCheckBoxStyle());
        cbMusic.setBounds(20, 400, SES.BUTTON_HEIGHT, SES.BUTTON_HEIGHT);
        mStage.addActor(cbMusic);

        Label lblMusic = new Label("Music", RM.getLabelStyle());
        lblMusic.setBounds(120, 400, 200, SES.BUTTON_HEIGHT);
        mStage.addActor(lblMusic);

        TextButton btnGooglePlayLink = new TextButton("GooglePlay", RM.getTextButtonStyle());
        btnGooglePlayLink.setBounds(SES.buttonRight(), 20, SES.BUTTON_WIDTH, SES.BUTTON_HEIGHT);
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
