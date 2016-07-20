package com.quadrolord.epicbattle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.EpicBattle;
import com.quadrolord.epicbattle.logic.profile.ProfileManager;
import com.quadrolord.epicbattle.logic.profile.ProfileSkill;
import com.quadrolord.epicbattle.logic.skill.AbstractSkillEntity;
import com.quadrolord.epicbattle.logic.tower.BattleGame;
import com.quadrolord.epicbattle.screen.slider.SliderList;
import com.quadrolord.epicbattle.screen.slider.SliderListener;
import com.quadrolord.epicbattle.screen.upgrading.UpgradingItemData;
import com.quadrolord.epicbattle.screen.upgrading.UpgradingSliderContent;

/**
 * Created by Quadrowin on 11.06.2016.
 */
public class UnitsUpgradingScreen extends AbstractScreen {

    private Label mCuName;
    private Label mCuDescription;

    private Label mProfileExperience;

    public UnitsUpgradingScreen(EpicBattle adapter) {
        super(adapter);
        initFitViewport();

        mCuName = new Label("", mSkin.get("default-label-style", Label.LabelStyle.class));
        mCuName.setBounds(10, 270, 380, 30);
        getStage().addActor(mCuName);

        mCuDescription = new Label("", mSkin.get("default-label-style", Label.LabelStyle.class));
        mCuDescription.setBounds(10, 200, 380, 70);
        mCuDescription.setAlignment(Align.topLeft);
        getStage().addActor(mCuDescription);

        mProfileExperience = new Label("" + get(ProfileManager.class).getProfile().getExperience(), mSkin.get("default-label-style", Label.LabelStyle.class));
        mProfileExperience.setBounds(10, 270, 380, 30);
        mProfileExperience.setAlignment(Align.topRight);
        getStage().addActor(mProfileExperience);

        final UpgradingSliderContent usc = new UpgradingSliderContent(this);
        usc.setSliderListener(new SliderListener<UpgradingItemData>() {

            @Override
            public void onSelect(TextButton btn, UpgradingItemData data) {
                AbstractSkillEntity skill = get(BattleGame.class).getSkillManager().get(data.profileSkill.getSkillClass());
                mCuName.setText( skill.getName() );
                mCuDescription.setText( skill.getDescription() );
            }

        });

        final SliderList sl = new SliderList(this, usc);
        sl.triggerCurrentButtonClick();

        // Кнопка апгрейда текущего юнита
        TextButton btnUpgrade = new TextButton("Upgrade", getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnUpgrade.setBounds(290, 180, 70, 40);
        mStage.addActor(btnUpgrade);
        btnUpgrade.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                UpgradingItemData data = (UpgradingItemData)sl.getCurrentObject();
                get(BattleGame.class).upgradeProfileSkill(data.profileSkill);
                usc.updateButton(data);
                mProfileExperience.setText( "" + get(ProfileManager.class).getProfile().getExperience() );
            }

        });

        // Кнопка закрытия
        TextButton btnClose = new TextButton("Back", getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnClose.setBounds(10, 10, 65, 30);
        mStage.addActor(btnClose);
        btnClose.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (mParentScreen == null) {
                    getAdapter().switchToScreen(CampaignSelectScreen.class);
                } else {
                    getAdapter().switchToScreen(getParentScreen(), true);
                }
            }

        });
    }

}
