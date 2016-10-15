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
            switch (bulletState) {
                case ATTACK_PREPARE:
                    switchAnimation(BulletState.ATTACK_PREPARE);
                    break;
                case ATTACK_FINISH:
                    switchAnimation(BulletState.IDLE);
                    break;
                case DEATH:
                    switchAnimation(BulletState.DEATH);
                    break;
                default:
                    switchAnimation(BulletState.RUN);
            }
            mLastState = bulletState;
        }

        // Для текущей анимации
        mAnimation.getAnimation().setTime( mBullet.getTime().stateTime );
        mAnimation.getAnimation().setTimePart( mBullet.getTime().statePart );
        switch (bulletState) {
            case FOLD_BACK:
                float dy = (float)Math.sin(3.14f * mBullet.getTime().statePart);
                setY(mBullet.getY() + dy * 10);
                break;
            case ATTACK_PREPARE:
                setY(mBullet.getY());
                break;
            case DEATH:
                setY(mBullet.getY());
                break;
            default:
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

    private void switchAnimation(BulletState state) {
        float originalAnimDeltaX = mAnimation.getAnimation() == null
                ? 0
                : mAnimation.getAnimation().getDeltaX();
        SpriteAnimationDrawable newAnim = getAnimation(state);
        mAnimation.setAnimationLooped(newAnim);
        newAnim.setDeltaX(originalAnimDeltaX);
        newAnim.setStartDeltaX(originalAnimDeltaX);
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
