package com.quadrolord.epicbattle.view.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.ArrayMap;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.BulletState;
import com.quadrolord.epicbattle.screen.battle.Shadow;
import com.quadrolord.epicbattle.view.SpriteAnimationActor;
import com.quadrolord.epicbattle.view.SpriteAnimationDrawable;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public abstract class AbstractBulletView extends Group {

    private AbstractBullet mBullet;

    private SpriteAnimationActor mAnimation;

    private BulletState mLastState = BulletState.IDLE;

    private int mLastDirection;

    protected ArrayMap<BulletState, SpriteAnimationDrawable> mAnimations = new ArrayMap<BulletState, SpriteAnimationDrawable>();

    protected abstract void initAnimations(AbstractScreen screen);

    public AbstractBulletView(AbstractBullet bullet, AbstractScreen screen) {
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
        updateBounds();
        new Shadow(this, screen);

        addActor(mAnimation);
    }

    @Override
    public void act(float delta) {
        BulletState bulletState = mBullet.getState();

        // Переключение анимации
        if (bulletState != mLastState) {
            Gdx.app.log("st", "st " + bulletState.name());
            float originalAnimDeltaX = mAnimation.getAnimation() == null
                    ? 0
                    : mAnimation.getAnimation().getDeltaX();
            switch (bulletState) {
                case ATTACK_PREPARE:
                    mAnimation.setAnimationLooped(getAnimation(BulletState.ATTACK_PREPARE));
                    break;
                case ATTACK_FINISH:
                    mAnimation.setAnimation(getAnimation(BulletState.IDLE));
                    break;
                case DEATH:
                    mAnimation.setAnimation(getAnimation(BulletState.DEATH));
                    break;
                default:
                    mAnimation.setAnimationLooped(getAnimation(BulletState.RUN));
            }
            mLastState = bulletState;
            mAnimation.getAnimation().setDeltaX(originalAnimDeltaX);
        }

        // Для текущей анимации
        switch (bulletState) {
            case FOLD_BACK:
                mAnimation.getAnimation().setTime( mBullet.getStateTime() );
                float dy = (float)Math.sin(3.14f * mBullet.getStatePart());
                setY(mBullet.getY() + dy * 10);
                break;
            case ATTACK_PREPARE:
                mAnimation.getAnimation().setTimePart( mBullet.getStatePart() );
                setY(mBullet.getY());
                break;
            case DEATH:
                mAnimation.getAnimation().setTime( mBullet.getStateTime() );
                setY(mBullet.getY());
                break;
            default:
                mAnimation.getAnimation().setTime( mBullet.getStateTime() );
                setY(mBullet.getY());
        }

        updateBounds();

        super.act(delta);
    }

    public SpriteAnimationDrawable getAnimation(BulletState state) {
        SpriteAnimationDrawable anim = mAnimations.get(state);
        if (anim == null) {
            Gdx.app.log("buv", "st " + state.name());
            anim = mAnimations.firstValue();
        }
        return anim;
    }

    public void updateBounds() {
        mAnimation.getAnimation().setDirection(mBullet.getDirection());
        float originalX = getX();
        float scale = mBullet.getHeight() / mAnimation.getAnimation().getHeight();
        setScale(scale);
        setX(mBullet.getX() - mBullet.getWidth() * .5f);
        mAnimation.getAnimation().incDeltaX(getX() - originalX);
    }

}
