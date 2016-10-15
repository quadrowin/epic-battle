package com.quadrolord.epicbattle.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
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
import com.quadrolord.epicbattle.logic.tower.BattleGame;
import com.quadrolord.epicbattle.screen.SES;
import com.quadrolord.epicbattle.screen.slider.SliderList;
import com.quadrolord.epicbattle.screen.slider.SliderListener;
import com.quadrolord.epicbattle.screen.unitstest.UnitTestSliderContent;
import com.quadrolord.epicbattle.view.bullet.AbstractBulletView;

/**
 * Created by Goorus on 20.07.2016.
 */
public class UnitsTestScreen extends AbstractScreen {

    private final String TAG = "UnitsTestScreen";

    private Label mCuName;

    private Label mCuState;

    private MockBullet mBullet;

    private Group mBulletWrapper;

    private AbstractBulletView mCurrentView;

    private float mUnitScale = 2;

    private int mCurrentStateIndex = 0;

    private BulletState[] mSwitchingStates = new BulletState[]{
            BulletState.WALK,
            BulletState.ATTACK_PREPARE,
            BulletState.IDLE,
            BulletState.DEATH,
    };

    public UnitsTestScreen(AbstractGameAdapter adapter) {
        super(adapter);
        initFitViewport();

        mBullet = new MockBullet(get(BattleGame.class));
        mBullet.setDirection(AbstractBullet.DIRECTION_RIGHT);
        mBullet.setY(0);
        mBullet.setX(SES.SCREEN_WIDTH / 2);

        mBulletWrapper = new Group();
        mBulletWrapper.setBounds(
                0, SES.SCREEN_HEIGHT / 2,
                SES.SCREEN_WIDTH / mUnitScale,
                SES.SCREEN_HEIGHT / 2 / mUnitScale
        );
        mBulletWrapper.setScale(mUnitScale);
        mStage.addActor(mBulletWrapper);

        mCuName = new Label("", mSkin.get("default-label-style", Label.LabelStyle.class));
        mCuName.setBounds(
                SES.SCREEN_BORDER, SES.SCREEN_HEIGHT - SES.BUTTON_HEIGHT,
                380, 30
        );
        getStage().addActor(mCuName);

        mCuState = new Label("", mSkin.get("default-label-style", Label.LabelStyle.class));
        mCuState.setBounds(
                SES.SCREEN_BORDER, SES.SCREEN_HEIGHT - SES.BUTTON_HEIGHT * 2,
                380, 30
        );
        getStage().addActor(mCuState);

        final UnitTestSliderContent usc = new UnitTestSliderContent(this);
        usc.setSliderListener(new SliderListener<AbstractLogic>() {

            @Override
            public void onSelect(TextButton btn, AbstractLogic data) {
                setUnitView(data);
            }

        });

        final SliderList sl = new SliderList(this, usc);
        sl.triggerCurrentButtonClick();

        mBulletWrapper.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (event.getButton() == 0) {
                    if (mCurrentView != null) {
                        switchAnimation();
                    }
                } else if (event.getButton() == 2){
                    mBullet.setDirection(
                            mBullet.getDirection() == AbstractBullet.DIRECTION_LEFT
                                    ? AbstractBullet.DIRECTION_RIGHT
                                    : AbstractBullet.DIRECTION_LEFT
                    );
                }

            }

        });
    }

    private void setUnitView(AbstractLogic logic) {
        float previousWidth = mBullet.getWidth();
        mBullet.setLogic(logic);
        mCuName.setText( logic.getTitle() );
        Class<? extends AbstractBulletView> viewClass = logic.getViewClass();

        mBullet.setX(mBullet.getX() + (previousWidth - mBullet.getWidth()) / 2);

        if (mCurrentView != null) {
            mCurrentView.remove();
        }

        try {
            mCurrentView = viewClass.getConstructor(AbstractBullet.class, com.quadrolord.ejge.view.AbstractScreen.class).newInstance(mBullet, this);
        } catch (Exception e) {
            Gdx.app.error("uts", "view create " + viewClass.getName(), e.getCause());
            mCurrentView = new AbstractBulletView(mBullet, this) {

                @Override
                protected void initAnimations(com.quadrolord.ejge.view.AbstractScreen screen) {

                }

            };
        }

        Gdx.app.log(TAG, "bullet view size: " + mBullet.getWidth() + "x" + mBullet.getHeight());
        mCurrentView.setSize(mBullet.getWidth(), mBullet.getHeight());
        mBulletWrapper.addActor(mCurrentView);

        setStateAnimation(mCurrentStateIndex);
    }

    private void setStateAnimation(int stateIndex) {
        mCurrentStateIndex = stateIndex;
        BulletState newState = mSwitchingStates[mCurrentStateIndex];
        Gdx.app.log("uts", "new state " + newState.name());
        float dur = 3;
        mBullet.setState(newState, dur);
        mCuState.setText(newState.name());
    }

    private void switchAnimation() {
        setStateAnimation( (mCurrentStateIndex + 1) % mSwitchingStates.length );
    }

    @Override
    public void update(float delta) {
        mBullet.act(delta);
        mBullet.moveX(delta * mBullet.getDirection(), mBulletWrapper.getWidth());
        mStage.act();
//        mCurrentView.setX(mBullet.getX() - mBullet.getWidth() * .5f - delta * 10);
    }

}
