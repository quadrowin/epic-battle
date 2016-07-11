package com.quadrolord.epicbattle.view.worker;

import com.badlogic.gdx.graphics.Texture;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
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

        mScaleWidth = 0.25f;
        mScaleHeight = 0.25f;
        mUnitY = 150;
    }

    @Override
    protected SpriteAnimationDrawable getRunningAnimation(AbstractScreen screen) {
        return new WalkAnimationDrawable(getWheelTexture(screen));
    }

    @Override
    protected SpriteAnimationDrawable getAttackingAnimation(AbstractScreen screen) {
        return new AttackAnimationDrawable(getWheelTexture(screen));
    }

    @Override
    protected SpriteAnimationDrawable getDeadAnimation(AbstractScreen screen) {
        return new DeadAnimationDrawable(getWheelTexture(screen));
    }

    private Texture getWheelTexture(AbstractScreen screen) {
        if (null == mWheelTexture) {
            mWheelTexture = screen.getTextures().get("wheel/wheel001.png");
        }
        return mWheelTexture;
    }
}
