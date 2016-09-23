package com.quadrolord.epicbattle.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.ejge.utils.AbstractAuthService;
import com.quadrolord.ejge.utils.StorageValueListener;
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

        final AbstractAuthService authService = mAdapter.getPlatformServices().getAuthService();

        mBgStage.loadImage(this, "Bg/menu/settings-bg.jpg");

        BackButton.create(this, MainMenuScreen.class);

        authService.goOffline();

        final CheckBox cbSounds = new CheckBox("", RM.getCheckBoxStyle());
        cbSounds.setBounds(20, 500, SES.BUTTON_HEIGHT, SES.BUTTON_HEIGHT);
        authService.addValueListener(
                this,
                "settings/sound_enabled",
                new StorageValueListener() {

                    @Override
                    public void onDataChange(Object value) {
                        Gdx.app.log("sound value", value == null ? "null" : value.toString());
                        cbSounds.setChecked( value instanceof Boolean && ((Boolean) value).booleanValue() );
                    }
                }
        );
        mStage.addActor(cbSounds);
        cbSounds.addListener(new ClickListener() {

            @Override
            public void clicked (InputEvent event, float x, float y) {
                authService.saveValue(
                        "settings/sound_enabled",
                        new Boolean(cbSounds.isChecked())
                );
            }

        });

        Label lblSounds = new Label("Sound effects", RM.getLabelStyle());
        lblSounds.setBounds(120, 500, 200, SES.BUTTON_HEIGHT);
        mStage.addActor(lblSounds);

        final CheckBox cbMusic = new CheckBox("", RM.getCheckBoxStyle());
        cbMusic.setBounds(20, 400, SES.BUTTON_HEIGHT, SES.BUTTON_HEIGHT);
        authService.addValueListener(
                this,
                "settings/music_enabled",
                new StorageValueListener() {

                    @Override
                    public void onDataChange(Object value) {
                        cbMusic.setChecked( value instanceof Boolean && ((Boolean) value).booleanValue() );
                    }
                }
        );
        mStage.addActor(cbMusic);
        cbMusic.addListener(new ClickListener() {

            public void clicked (InputEvent event, float x, float y) {
                mAdapter.getPlatformServices().getAuthService().saveValue(
                        "settings/music_enabled",
                        new Boolean(cbMusic.isChecked())
                );
            }

        });

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

    @Override
    public void dispose () {
        mAdapter.getPlatformServices().getAuthService().removeValueListener(this);
    }

}
