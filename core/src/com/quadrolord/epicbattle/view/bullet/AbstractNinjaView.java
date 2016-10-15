package com.quadrolord.epicbattle.view.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ArrayMap;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.BulletState;
import com.quadrolord.epicbattle.screen.battle.Shadow;
import com.quadrolord.epicbattle.view.SpriteAnimationActor;
import com.quadrolord.epicbattle.view.SpriteAnimationDrawable;
import com.quadrolord.epicbattle.view.wheel.AttackAnimationDrawable;
import com.quadrolord.epicbattle.view.wheel.DeadAnimationDrawable;
import com.quadrolord.epicbattle.view.wheel.WalkAnimationDrawable;

/**
 * Created by Goorus on 28.07.2016.
 */
abstract public class AbstractNinjaView extends AbstractBulletView {

    private SpriteAnimationActor mAnimation;

    protected ArrayMap<BulletState, SpriteAnimationDrawable> mAnimations = new ArrayMap<BulletState, SpriteAnimationDrawable>();

    private BulletState mLastState = BulletState.IDLE;

    public AbstractNinjaView(AbstractBullet bullet, AbstractScreen screen) {
        super(bullet, screen);

        setBounds(
                bullet.getX() - bullet.getWidth() * .5f, bullet.getY(),
                bullet.getWidth(), bullet.getHeight()
        );

        mAnimation = new SpriteAnimationActor();
        mAnimation.setAnimationLooped(getAnimation(BulletState.RUN));
        updateBounds();
        new Shadow(this, screen);

        addActor(mAnimation);
    }

    @Override
    public void act(float delta) {
        AbstractBullet bullet = getBullet();
        BulletState bulletState = bullet.getState();

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
        mAnimation.getAnimation().setTime( bullet.getTime().stateTime );
        mAnimation.getAnimation().setTimePart( bullet.getTime().statePart );
        switch (bulletState) {
            case FOLD_BACK:
                float dy = (float)Math.sin(3.14f * bullet.getTime().statePart);
                setY(bullet.getY() + dy * 10);
                break;
            case ATTACK_PREPARE:
                setY(bullet.getY());
                break;
            case DEATH:
                setY(bullet.getY());
                break;
            default:
                setY(bullet.getY());
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
        AbstractBullet bullet = getBullet();
        mAnimation.getAnimation().setDirection(bullet.getDirection());
        float originalX = getX();
        float scale = bullet.getHeight() / mAnimation.getAnimation().getHeight();
        setScale(scale);
        setX(bullet.getX() - bullet.getWidth() * .5f);
        mAnimation.getAnimation().incDeltaX(getX() - originalX);
    }

    abstract protected void initAnimations(AbstractScreen screen);

}

