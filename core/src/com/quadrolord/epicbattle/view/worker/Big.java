package com.quadrolord.epicbattle.view.worker;

import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.bullet.worker.BulletState;
import com.quadrolord.epicbattle.view.BulletUnitView;
import com.quadrolord.epicbattle.view.SpriteAnimationDrawable;

/**
 * Created by morph on 11.01.2016.
 */
public class Big extends BulletUnitView {

    public Big(AbstractBullet bullet, AbstractScreen screen) {
        super(bullet, screen);
    }

    @Override
    protected void initAnimations(AbstractScreen screen) {
        mAnimations.put(
                BulletState.RUN,
                screen.getSpriteAnimationLoader().createDrawable(
                    screen.getSkin(),
                    "animation/ninja_a/ninja_run.png",
                    70,
                    89,
                    10,
                    0.1f,
                    2,
                    2,
                    true
                )
        );

        mAnimations.put(
                BulletState.ATTACK,
                screen.getSpriteAnimationLoader().createDrawable(
                    screen.getSkin(),
                    "animation/ninja_a/ninja_attack.png",
                    95,
                    89,
                    10,
                    0.1f,
                    2,
                    2,
                    true
                )
        );

        mAnimations.put(
                BulletState.DEATH,
                screen.getSpriteAnimationLoader().createDrawable(
                    screen.getSkin(),
                    "animation/ninja_a/ninja_dead.png",
                    85,
                    89,
                    10,
                    0.1f,
                    2,
                    2,
                    false
                )
        );
    }

}
