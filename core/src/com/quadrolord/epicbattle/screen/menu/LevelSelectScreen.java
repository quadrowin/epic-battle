package com.quadrolord.epicbattle.screen.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.ejge.AbstractGameAdapter;
import com.quadrolord.epicbattle.RM;
import com.quadrolord.epicbattle.logic.campaign.AbstractCampaign;
import com.quadrolord.epicbattle.logic.campaign.Level;
import com.quadrolord.epicbattle.logic.tower.BattleGame;
import com.quadrolord.epicbattle.screen.BattleScreen;
import com.quadrolord.epicbattle.screen.SES;
import com.quadrolord.epicbattle.screen.menu.campaigns.LevelsList;
import com.quadrolord.epicbattle.screen.menu.campaigns.CampaignSliderContent;
import com.quadrolord.epicbattle.screen.menu.campaigns.LevelsSliderContent;
import com.quadrolord.epicbattle.screen.menu.component.BackButton;
import com.quadrolord.epicbattle.screen.slider.SliderList;
import com.quadrolord.epicbattle.screen.slider.SliderListener;

/**
 * Created by Quadrowin on 13.01.2016.
 */
public class LevelSelectScreen extends com.quadrolord.ejge.view.AbstractScreen {

    private Label mCampName;

    private AbstractCampaign mCampaign;

    public LevelSelectScreen(AbstractGameAdapter adapter, AbstractCampaign campaign) {
        super(adapter);
        initFitViewport();
        mCampaign = campaign;

//        new LevelsList(this, mCampaign.getLevels());

        mCampName = new Label("", RM.getLabelStyle());
        mCampName.setBounds(SES.SCREEN_BORDER, 500, 380, 30);
        getStage().addActor(mCampName);

        TextButton btnBack = BackButton.create(this, CampaignSelectScreen.class);
        btnBack.setText("Leave campaign");

        LevelsSliderContent lsc = new LevelsSliderContent(this, mCampaign);
        lsc.setSliderListener(new SliderListener<Level>() {

            @Override
            public void onSelect(TextButton btn, Level data) {
                mCampName.setText( data.getName() );
//                mCuDescription.setText( skill.getDescription() );
            }

        });
        final SliderList sl = new SliderList(this, lsc);
        sl.triggerCurrentButtonClick();

        TextButton btnStartLevel = new TextButton("Start level", RM.getTextButtonStyle());
        btnStartLevel.setBounds(SES.buttonRight(), SES.SCREEN_BORDER, SES.BUTTON_WIDTH, SES.BUTTON_HEIGHT);
        mStage.addActor(btnStartLevel);
        btnStartLevel.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Level level = (Level)sl.getCurrentObject();
                BattleScreen gameScreen = new BattleScreen(mAdapter, level);
                mAdapter.switchToScreen(gameScreen, true);
            }

        });
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
