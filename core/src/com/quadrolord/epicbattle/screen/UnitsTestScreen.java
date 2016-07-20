package com.quadrolord.epicbattle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.ejge.AbstractGameAdapter;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.bullet.worker.BulletState;
import com.quadrolord.epicbattle.logic.bullet.worker.MockBullet;
import com.quadrolord.epicbattle.logic.profile.ProfileSkill;
import com.quadrolord.epicbattle.logic.skill.AbstractBulletSkill;
import com.quadrolord.epicbattle.logic.skill.AbstractSkillEntity;
import com.quadrolord.epicbattle.logic.tower.BattleGame;
import com.quadrolord.epicbattle.screen.slider.SliderList;
import com.quadrolord.epicbattle.screen.slider.SliderListener;
import com.quadrolord.epicbattle.screen.unitstest.UnitTestSliderContent;
import com.quadrolord.epicbattle.screen.upgrading.UpgradingItemData;
import com.quadrolord.epicbattle.screen.upgrading.UpgradingSliderContent;
import com.quadrolord.epicbattle.view.BulletUnitView;
import com.quadrolord.epicbattle.view.SpriteAnimationDrawable;

/**
 * Created by Goorus on 20.07.2016.
 */
public class UnitsTestScreen extends AbstractScreen {

    private Label mCuName;

    private MockBullet mBullet;

    private BulletUnitView mCurrentView;

    private int mCurrentStateIndex = 0;

    private BulletState[] mSwitchingStates = new BulletState[]{
            BulletState.WALK,
            BulletState.ATTACK,
            BulletState.DEATH,
    };

    public UnitsTestScreen(AbstractGameAdapter adapter) {
        super(adapter);
        initFitViewport();

        mBullet = new MockBullet(get(BattleGame.class));
        mBullet.setY(200);
        mBullet.setX(200);

        mCuName = new Label("", mSkin.get("default-label-style", Label.LabelStyle.class));
        mCuName.setBounds(10, 270, 380, 30);
        getStage().addActor(mCuName);

        final UnitTestSliderContent usc = new UnitTestSliderContent(this);
        usc.setSliderListener(new SliderListener<AbstractLogic>() {

            @Override
            public void onSelect(TextButton btn, AbstractLogic data) {
                setUnitView(data);
            }

        });

        final SliderList sl = new SliderList(this, usc);
        sl.triggerCurrentButtonClick();

        mStage.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("uts", "view clicked");
                if (
                        mCurrentView != null
                        && mCurrentView.getX() <= x && x <= mCurrentView.getRight()
                        && mCurrentView.getY() <= y && y <= mCurrentView.getTop()
                ) {
                    switchAnimation();
                }
            }

        });
    }

    private void setUnitView(AbstractLogic logic) {
        mBullet.setLogic(logic);
        mCuName.setText( logic.getTitle() );
        Class<? extends BulletUnitView> viewClass = logic.getViewClass();

        if (mCurrentView != null) {
            mCurrentView.remove();
        }

        mBullet.setX((mStage.getWidth() / mPx - mBullet.getWidth()) / 2);
        mBullet.setState(mSwitchingStates[mCurrentStateIndex], 10);

        try {
            mCurrentView = viewClass.getConstructor(AbstractBullet.class, com.quadrolord.ejge.view.AbstractScreen.class).newInstance(mBullet, this);
        } catch (Exception e) {
            mCurrentView = new BulletUnitView(mBullet, this) {

                @Override
                protected void initAnimations(com.quadrolord.ejge.view.AbstractScreen screen) {

                }

            };
        }

    }

    private void switchAnimation() {
        mCurrentStateIndex = (mCurrentStateIndex + 1) % mSwitchingStates.length;
        mBullet.setState(mSwitchingStates[mCurrentStateIndex], 10);
    }

    @Override
    public void update(float delta) {
        mCurrentView.setX(mBullet.getX() - mBullet.getWidth() * .5f - delta * 10);
    }

}
