package com.quadrolord.epicbattle.view;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.BulletState;
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

    public BulletUnitView(AbstractBullet bullet, AbstractScreen screen) {
        mBullet = bullet;
        mBullet.setViewObject(this);

        setBounds(
                mBullet.getX() - bullet.getWidth() * .5f, mBullet.getY(),
                bullet.getWidth(), bullet.getHeight()
        );
        screen.getStage().addActor(this);

        mRunningAnim = getRunningAnimation(screen);
        mAttackingAnim = getAttackingAnimation(screen);
        mDeadAnim = getDeadAnimation(screen);

        mAnimation = new SpriteAnimationActor();
        mAnimation.setAnimationLooped(mRunningAnim);

        new Shadow(this, screen);

        addActor(mAnimation);
    }

    @Override
    public void act(float delta) {
        if (mBullet.getState() == BulletState.FOLD_BACK) {
            float dy = (float)Math.sin(3.14f * mBullet.getStatePart());
            setY(mBullet.getY() + dy * 10);
            if (mAnimation.getAnimation() != mRunningAnim) {
                mAnimation.setAnimationLooped(mRunningAnim);
            }
        } else if (mBullet.isRunning()) {
            setY(mBullet.getY());
            if (mAnimation.getAnimation() != mRunningAnim) {
                mAnimation.setAnimationLooped(mRunningAnim);
            }
        }

        if (mAnimation.getAnimation() == mAttackingAnim) {
            mAttackingAnim.setTime( mBullet.getStatePart() );
        }


        float originalX = getX();
        float scale = mBullet.getHeight() / mAnimation.getAnimation().getHeight();
        if (mBullet.getDirection() > 0) {
            setX(mBullet.getX() - mBullet.getWidth() * .5f);
            setScale(scale);
        } else {
            setX(mBullet.getX() + mBullet.getWidth() * .5f);
            setScale(-scale, scale);
        }

        mAnimation.getAnimation().incDeltaX(getX() - originalX);

        super.act(delta);
    }

    public void startAttackAnimation() {
        mAnimation.setAnimationLooped(mAttackingAnim);
        mAttackingAnim.setDeltaX( mAnimation.getAnimation().getDeltaX() );
    }

    public void startDeadAnimation() {
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

}
