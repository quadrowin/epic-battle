package com.quadrolord.epicbattle.view.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.BulletState;
import com.quadrolord.epicbattle.view.wheel.AttackAnimationDrawable;
import com.quadrolord.epicbattle.view.wheel.DeadAnimationDrawable;
import com.quadrolord.epicbattle.view.wheel.WalkAnimationDrawable;

/**
 * Created by Goorus on 28.07.2016.
 */
abstract public class AbstractWheelView extends AbstractBulletView {

    private Texture mWheelTexture;

    public AbstractWheelView(AbstractBullet bullet, AbstractScreen screen) {
        super(bullet, screen);
    }

    @Override
    protected void initAnimations(AbstractScreen screen) {
        mAnimations.put(
                BulletState.RUN,
                new WalkAnimationDrawable(getWheelTexture(screen), getWidth(), getHeight())
        );

        mAnimations.put(
                BulletState.ATTACK_PREPARE,
                new AttackAnimationDrawable(getWheelTexture(screen), getWidth(), getHeight())
        );

        mAnimations.put(
                BulletState.DEATH,
                new DeadAnimationDrawable(getWheelTexture(screen), getWidth(), getHeight())
        );
    }

    protected Texture getWheelTexture(AbstractScreen screen) {
        if (null == mWheelTexture) {
            mWheelTexture = screen.getTextures().get(getWheelTextureFile());
        }
        return mWheelTexture;
    }

    abstract protected String getWheelTextureFile();

}

