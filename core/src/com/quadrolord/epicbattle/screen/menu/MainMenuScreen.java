package com.quadrolord.epicbattle.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.quadrolord.ejge.AbstractGameAdapter;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.RM;
import com.quadrolord.epicbattle.screen.SES;
import com.quadrolord.epicbattle.screen.menu.component.BackgroundStage;

/**
 * Created by Goorus on 13.08.2016.
 */
public class MainMenuScreen extends AbstractScreen {

    private Image mLogo;

    private Label mBuildInfo;

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

        mBuildInfo = new Label("B " + getAdapter().getPlatformServices().getBuildTime(), RM.getLabelStyle());
        mBuildInfo.setBounds(500, 550, 100, 50);
        mStage.addActor(mBuildInfo);

        TextButton btnPlay = new TextButton("Play", RM.getTextButtonStyle());
        btnPlay.setBounds(260, 310, SES.BUTTON_WIDTH, SES.BUTTON_HEIGHT);
        mStage.addActor(btnPlay);
        btnPlay.addListener(new ClickListener() {

            public void clicked (InputEvent event, float x, float y) {
                mAdapter.switchToScreen(CampaignSelectScreen.class);
            }

        });

        final AbstractScreen thisScreen = this;
        TextButton btnSettings = new TextButton("Settings", RM.getTextButtonStyle());
        btnSettings.setBounds(260, 210, SES.BUTTON_WIDTH, SES.BUTTON_HEIGHT);
        mStage.addActor(btnSettings);
        btnSettings.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                mAdapter.switchToChildScreen(SettingsScreen.class, thisScreen);
            }

        });

        TextButton btnAuth = new TextButton("Auth", RM.getTextButtonStyle());
        btnAuth.setBounds(SES.buttonRight(), 400, SES.BUTTON_WIDTH, SES.BUTTON_HEIGHT);
        mStage.addActor(btnAuth);
        btnAuth.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                mAdapter.getPlatformServices().getAuthService().auth();
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
