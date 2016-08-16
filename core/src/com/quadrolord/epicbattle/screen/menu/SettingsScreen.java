package com.quadrolord.epicbattle.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
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
