package com.quadrolord.epicbattle.screen.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.EpicBattle;
import com.quadrolord.epicbattle.RM;
import com.quadrolord.epicbattle.logic.profile.ProfileManager;
import com.quadrolord.epicbattle.logic.skill.AbstractSkillEntity;
import com.quadrolord.epicbattle.logic.tower.BattleGame;
import com.quadrolord.epicbattle.screen.menu.component.BackButton;
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

        mCuName = new Label("", RM.getLabelStyle());
        mCuName.setBounds(10, 270, 380, 30);
        getStage().addActor(mCuName);

        mCuDescription = new Label("", RM.getLabelStyle());
        mCuDescription.setBounds(10, 200, 380, 70);
        mCuDescription.setAlignment(Align.topLeft);
        getStage().addActor(mCuDescription);

        mProfileExperience = new Label("" + get(ProfileManager.class).getProfile().getExperience(), RM.getLabelStyle());
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
        TextButton btnUpgrade = new TextButton("Upgrade", RM.getTextButtonStyle());
        btnUpgrade.setBounds(530, 10, 260, 80);
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
        BackButton.create(this, CampaignSelectScreen.class);
    }

}
