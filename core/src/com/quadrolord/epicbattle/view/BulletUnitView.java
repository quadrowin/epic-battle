package com.quadrolord.epicbattle.view;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.ArrayMap;
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

    protected ArrayMap<BulletState, SpriteAnimationDrawable> mAnimations = new ArrayMap<BulletState, SpriteAnimationDrawable>();

    protected abstract void initAnimations(AbstractScreen screen);

    public BulletUnitView(AbstractBullet bullet, AbstractScreen screen) {
        mBullet = bullet;
        mBullet.setViewObject(this);

        setBounds(
                mBullet.getX() - bullet.getWidth() * .5f, mBullet.getY(),
                bullet.getWidth(), bullet.getHeight()
        );
        screen.getStage().addActor(this);

        initAnimations(screen);

        mAnimation = new SpriteAnimationActor();
        mAnimation.setAnimationLooped(getAnimation(BulletState.RUN));

        new Shadow(this, screen);

        addActor(mAnimation);
    }

    @Override
    public void act(float delta) {
        SpriteAnimationDrawable runAnim = getAnimation(BulletState.RUN);
        SpriteAnimationDrawable attAnim = getAnimation(BulletState.ATTACK);

        if (mBullet.getState() == BulletState.FOLD_BACK) {
            float dy = (float)Math.sin(3.14f * mBullet.getStatePart());
            setY(mBullet.getY() + dy * 10);
            if (mAnimation.getAnimation() != runAnim) {
                mAnimation.setAnimationLooped(runAnim);
            }
        } else if (mBullet.isRunning()) {
            setY(mBullet.getY());
            if (mAnimation.getAnimation() != runAnim) {
                mAnimation.setAnimationLooped(runAnim);
            }
        }

        if (mAnimation.getAnimation() == attAnim) {
            attAnim.setTime( mBullet.getStatePart() );
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

    public SpriteAnimationDrawable getAnimation(BulletState state) {
        SpriteAnimationDrawable anim = mAnimations.get(state);
        if (anim == null) {
            anim = mAnimations.firstValue();
        }
        return anim;
    }

    public void startAttackAnimation() {
        SpriteAnimationDrawable anim = getAnimation(BulletState.ATTACK);
        mAnimation.setAnimationLooped(anim);
    }

    public void startDeadAnimation() {
        final Actor self = this;
        mAnimation.setAnimationCallback(
                getAnimation(BulletState.DEATH),
                new Runnable() {
                    @Override
                    public void run() {
                        self.remove();
                    }
                }
        );
    }

}
