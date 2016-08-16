package com.quadrolord.epicbattle.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.quadrolord.ejge.AbstractGameAdapter;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.RM;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.bullet.worker.BulletState;
import com.quadrolord.epicbattle.logic.bullet.worker.MockBullet;
import com.quadrolord.epicbattle.logic.tower.BattleGame;
import com.quadrolord.epicbattle.screen.CampaignSelectScreen;
import com.quadrolord.epicbattle.screen.menu.component.BackgroundStage;
import com.quadrolord.epicbattle.screen.slider.SliderList;
import com.quadrolord.epicbattle.screen.slider.SliderListener;
import com.quadrolord.epicbattle.screen.unitstest.UnitTestSliderContent;
import com.quadrolord.epicbattle.view.bullet.AbstractBulletView;

/**
 * Created by Goorus on 13.08.2016.
 */
public class MainMenuScreen extends AbstractScreen {

    private Image mLogo;

    private BackgroundStage mBgStage = new BackgroundStage();

    public MainMenuScreen(AbstractGameAdapter adapter) {
        super(adapter);
        initFitViewport();

        mBgStage.loadImage(this, "Bg/menu/mbg1.jpg");

        mStage.setViewport( new FitViewport(800 * mPx, 600 * mPx) );
        mStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        mLogo = new Image(getTextures().get("ui/ewb-logo-1.png"));
        mLogo.setBounds(0, 400, 400, 200);
        mStage.addActor(mLogo);

        TextButton btnPlay = new TextButton("Play", RM.getTextButtonStyle());
        btnPlay.setBounds(270, 310, 260, 80);
        mStage.addActor(btnPlay);
        btnPlay.addListener(new ClickListener() {

            public void clicked (InputEvent event, float x, float y) {
                mAdapter.switchToScreen(CampaignSelectScreen.class);
            }

        });

        final AbstractScreen thisScreen = this;
        TextButton btnSettings = new TextButton("Settings", RM.getTextButtonStyle());
        btnSettings.setBounds(270, 210, 260, 80);
        mStage.addActor(btnSettings);
        btnSettings.addListener(new ClickListener() {

            public void clicked (InputEvent event, float x, float y) {
                mAdapter.switchToChildScreen(SettingsScreen.class, thisScreen);
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
    public void resize(int width, int height) {
        mStage.getViewport().update(width, height, true);
    }

}
