package com.quadrolord.epicbattle.view;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.screen.AbstractScreen;
import com.quadrolord.epicbattle.screen.battle.BleedAnimation;
import com.quadrolord.epicbattle.screen.battle.Shadow;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public abstract class BulletUnitView extends Group {

    private AbstractBullet mBullet;

    private SpriteAnimationActor mAnimation;

    protected SpriteAnimationDrawable mRunningAnim;
    protected SpriteAnimationDrawable mAttackingAnim;
    protected SpriteAnimationDrawable mDeadAnim;

    protected abstract SpriteAnimationDrawable getRunningAnimation(AbstractScreen screen);
    protected abstract SpriteAnimationDrawable getAttackingAnimation(AbstractScreen screen);
    protected abstract SpriteAnimationDrawable getDeadAnimation(AbstractScreen screen);

    protected float mScaleWidth = 1.0f;
    protected float mScaleHeight = 1.0f;
    protected float mUnitY = 60.0f;

    protected float mRealWidth;
    protected float mRealHeight;

    private Shadow mShadow;

    public BulletUnitView(AbstractBullet bullet, AbstractScreen screen) {
        mBullet = bullet;
        mBullet.setViewObject(this);

        setBounds(mBullet.getX(), getUnitY(), bullet.getWidth(), bullet.getWidth());
        screen.getStage().addActor(this);

        mRunningAnim = getRunningAnimation(screen);
        mAttackingAnim = getAttackingAnimation(screen);
        mDeadAnim = getDeadAnimation(screen);

        mAnimation = new SpriteAnimationActor();
        mAnimation.setAnimationLooped(mRunningAnim);

        mRealWidth = mAnimation.getAnimation().getTexture().getRegionWidth();
        mRealHeight = mAnimation.getAnimation().getTexture().getRegionHeight();

        mShadow = new Shadow(this, screen);

        addActor(mAnimation);
    }

    @Override
    public void act(float delta) {
        if (mBullet.getVelocity() > 0) {
            setX(mBullet.getX());
            setScale(getScaleWidth(), getScaleHeight());
        } else {
            setX(mBullet.getX() + mBullet.getWidth());
            setScale(-getScaleWidth(), getScaleHeight());
        }

        if (mBullet.isRunning()) {
            if (mAnimation.getAnimation() != mRunningAnim) {
                mAnimation.setAnimationLooped(mRunningAnim);
            }
        }

        super.act(delta);
    }

    public float getRealWidth() {
        return mRealWidth * getScaleX();
    }

    public float getRealHeight() {
        return mRealHeight * getScaleY();
    }

    public void startAttackAnimation() {
        mAnimation.setAnimationLooped(mAttackingAnim);
    }

    public void startDeadAnimation() {
        mShadow.remove();

        final Actor self = this;
        mAnimation.setAnimationCallback(
                mDeadAnim,
                new Runnable() {
                    @Override
                    public void run() {
                        self.remove();
                    }
                }
        );
    }

    protected float getScaleWidth() {
        return mScaleWidth;
    }

    protected float getScaleHeight() {
        return mScaleHeight;
    }

    protected float getUnitY() {
        return mUnitY;
    }
}
