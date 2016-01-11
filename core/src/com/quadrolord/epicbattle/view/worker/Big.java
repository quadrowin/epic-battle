package com.quadrolord.epicbattle.view.worker;

import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.screen.AbstractScreen;
import com.quadrolord.epicbattle.view.BulletUnitView;
import com.quadrolord.epicbattle.view.SpriteAnimationDrawable;

/**
 * Created by morph on 11.01.2016.
 */
public class Big extends BulletUnitView {

    public Big(AbstractBullet bullet, AbstractScreen screen) {
        super(bullet, screen);

        mScaleWidth = 1.0f;
        mScaleHeight = 1.0f;
    }

    @Override
    protected SpriteAnimationDrawable getRunningAnimation(AbstractScreen screen) {
        return screen.getSpriteAnimationLoader().createDrawable(
                screen.getSkin(),
                "animation_ninja/ninja_run.png",
                70,
                89,
                10,
                0.1f,
                2,
                2,
                true
        );
    }

    @Override
    protected SpriteAnimationDrawable getAttackingAnimation(AbstractScreen screen) {
        return screen.getSpriteAnimationLoader().createDrawable(
                screen.getSkin(),
                "animation_ninja/ninja_attack.png",
                95,
                89,
                10,
                0.1f,
                2,
                2,
                true
        );
    }

    @Override
    protected SpriteAnimationDrawable getDeadAnimation(AbstractScreen screen) {
        return screen.getSpriteAnimationLoader().createDrawable(
                screen.getSkin(),
                "animation_ninja/ninja_dead.png",
                85,
                89,
                10,
                0.1f,
                2,
                2,
                false
        );
    }

    protected float getUnitY() {
        return 80.0f;
    }
}
