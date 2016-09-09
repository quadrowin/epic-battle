package com.quadrolord.epicbattle.screen.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.epicbattle.EpicBattle;
import com.quadrolord.epicbattle.RM;
import com.quadrolord.epicbattle.logic.campaign.AbstractCampaign;
import com.quadrolord.epicbattle.logic.tower.BattleGame;
import com.quadrolord.epicbattle.screen.SES;
import com.quadrolord.epicbattle.screen.menu.campaigns.CampaignSliderContent;
import com.quadrolord.epicbattle.screen.menu.component.BackButton;
import com.quadrolord.epicbattle.screen.slider.SliderList;
import com.quadrolord.epicbattle.screen.slider.SliderListener;

/**
 * Created by Quadrowin on 13.01.2016.
 */
public class CampaignSelectScreen extends com.quadrolord.ejge.view.AbstractScreen {

    private Label mCampName;

    public CampaignSelectScreen(EpicBattle adapter) {
        super(adapter);
        initFitViewport();

        mCampName = new Label("", RM.getLabelStyle());
        mCampName.setBounds(SES.SCREEN_BORDER, 500, 380, 30);
        getStage().addActor(mCampName);

        BackButton.create(this, MainMenuScreen.class);

        CampaignSliderContent csc = new CampaignSliderContent(this, get(BattleGame.class).getCampaignManager());
        csc.setSliderListener(new SliderListener<AbstractCampaign>() {

            @Override
            public void onSelect(TextButton btn, AbstractCampaign data) {
                mCampName.setText( data.getName() );
//                mCuDescription.setText( skill.getDescription() );
            }

        });
        final SliderList sl = new SliderList(this, csc);
        sl.triggerCurrentButtonClick();

        TextButton btnToUpgrade = new TextButton("Upgrade units", RM.getTextButtonStyle());
        btnToUpgrade.setBounds(SES.buttonRight(), 350, SES.BUTTON_WIDTH, SES.BUTTON_HEIGHT);
        mStage.addActor(btnToUpgrade);
        btnToUpgrade.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                mAdapter.switchToScreen(UnitsUpgradingScreen.class);
            }

        });

        TextButton btnToCampaign = new TextButton("Select campaign", RM.getTextButtonStyle());
        btnToCampaign.setBounds(SES.buttonRight(), SES.SCREEN_BORDER, SES.BUTTON_WIDTH, SES.BUTTON_HEIGHT);
        mStage.addActor(btnToCampaign);
        btnToCampaign.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                AbstractCampaign camp = (AbstractCampaign)sl.getCurrentObject();
                LevelSelectScreen levelsScreen = new LevelSelectScreen(mAdapter, camp);
                mAdapter.switchToScreen(levelsScreen, true);
            }

        });

        /*
        TextButton btnToMyTown = new TextButton("Enter your city", RM.getTextButtonStyle());
        btnToMyTown.setBounds(210, 190, 180, 50);
        mStage.addActor(btnToMyTown);
        btnToMyTown.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                mAdapter.switchToScreen(MyTownScreen.class);
            }

        });
        */
    }

    @Override
    public void draw(float delta) {
        mStage.act(delta);
        mStage.draw();
    }

    @Override
    public void update(float delta) {

    }
}
