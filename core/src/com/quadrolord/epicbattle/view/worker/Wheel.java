package com.quadrolord.epicbattle.view.worker;

import com.badlogic.gdx.graphics.Texture;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.BulletState;
import com.quadrolord.epicbattle.view.BulletUnitView;
import com.quadrolord.epicbattle.view.SpriteAnimationDrawable;
import com.quadrolord.epicbattle.view.wheel.AttackAnimationDrawable;
import com.quadrolord.epicbattle.view.wheel.DeadAnimationDrawable;
import com.quadrolord.epicbattle.view.wheel.WalkAnimationDrawable;

/**
 * Created by Quadrowin on 10.07.2016.
 */
public class Wheel extends BulletUnitView {

    private Texture mWheelTexture;

    public Wheel(AbstractBullet bullet, AbstractScreen screen) {
        super(bullet, screen);
    }

    @Override
    protected void initAnimations(AbstractScreen screen) {
        mAnimations.put(
                BulletState.RUN,
                new WalkAnimationDrawable(getWheelTexture(screen), getWidth(), getHeight())
        );

        mAnimations.put(
                BulletState.ATTACK,
                new AttackAnimationDrawable(getWheelTexture(screen), getWidth(), getHeight())
        );

        mAnimations.put(
                BulletState.DEATH,
                new DeadAnimationDrawable(getWheelTexture(screen), getWidth(), getHeight())
        );
    }

    private Texture getWheelTexture(AbstractScreen screen) {
        if (null == mWheelTexture) {
            mWheelTexture = screen.getTextures().get("wheel/wheel001.png");
        }
        return mWheelTexture;
    }
}
