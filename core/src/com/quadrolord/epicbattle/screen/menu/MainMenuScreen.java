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
import com.quadrolord.epicbattle.screen.slider.SliderList;
import com.quadrolord.epicbattle.screen.slider.SliderListener;
import com.quadrolord.epicbattle.screen.unitstest.UnitTestSliderContent;
import com.quadrolord.epicbattle.view.bullet.AbstractBulletView;

/**
 * Created by Goorus on 13.08.2016.
 */
public class MainMenuScreen extends AbstractScreen {

    private Texture mBg;

    private Image mBgControl;

    private Stage mBgStage;

    private Viewport mBgViewport;

    public MainMenuScreen(AbstractGameAdapter adapter) {
        super(adapter);
        initFitViewport();

        mBg = getTextures().get("Bg/menu/mbg1.jpg");
        mBgControl = new Image(mBg);
        mBgControl.setBounds(0, 0, 40, 30);

        mBgViewport = new FillViewport(40, 30);
        mBgViewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        mBgStage = new Stage(mBgViewport);
        mBgStage.addActor(mBgControl);

        mStage.setViewport( new FitViewport(800 * mPx, 600 * mPx) );
        mStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        TextButton btnPlay = new TextButton("Play", RM.getTextButtonStyle());
        btnPlay.setBounds(280, 310, 240, 80);
        mStage.addActor(btnPlay);
        btnPlay.addListener(new ClickListener() {

            public void clicked (InputEvent event, float x, float y) {
                mAdapter.switchToScreen(CampaignSelectScreen.class);
            }

        });

        TextButton btnSettings = new TextButton("Settings", RM.getTextButtonStyle());
        btnSettings.setBounds(280, 210, 240, 80);
        mStage.addActor(btnSettings);
    }

    @Override
    public void draw(float delta) {
        mBgStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        mBgStage.draw();
        mStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        mStage.draw();
    }

    @Override
    public void update(float delta) {
        mBgStage.act(delta);
        mStage.act(delta);
    }

    @Override
    public void resize(int width, int height) {
        mBgStage.getViewport().update(width, height, true);
        mStage.getViewport().update(width, height, true);
    }

}
