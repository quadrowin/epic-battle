package com.quadrolord.epicbattle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.EpicBattle;
import com.quadrolord.epicbattle.logic.profile.ProfileSkill;
import com.quadrolord.epicbattle.logic.skill.AbstractSkillEntity;
import com.quadrolord.epicbattle.logic.tower.BattleGame;
import com.quadrolord.epicbattle.screen.slider.SliderList;
import com.quadrolord.epicbattle.screen.upgrading.UpgradingItemSelect;
import com.quadrolord.epicbattle.screen.upgrading.UpgradingSliderContent;

/**
 * Created by Quadrowin on 11.06.2016.
 */
public class UnitsUpgradingScreen extends AbstractScreen {

    private Label mCuName;
    private Label mCuDescription;

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

        UpgradingSliderContent usc = new UpgradingSliderContent(this);
        usc.setOnSelect(new UpgradingItemSelect() {

            @Override
            public void onSelect(ProfileSkill profileSkill) {
                AbstractSkillEntity skill = get(BattleGame.class).getSkillManager().get(profileSkill.getSkillClass());
                Gdx.app.log("upg", "on select " + skill.getName());
                mCuName.setText( skill.getName() );
                mCuDescription.setText( skill.getDescription() );
            }

        });

        SliderList sl = new SliderList(this, usc);
        sl.triggerCurrentButtonClick();
    }

    @Override
    public void draw(float delta) {
        mStage.act(delta);
        mStage.draw();
    }

    @Override
    public void update(float delta) {
        mStage.act(delta);
    }

}
