package com.quadrolord.epicbattle.view.bullet.ninja;

import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.BulletState;
import com.quadrolord.epicbattle.view.bullet.AbstractBulletView;
import com.quadrolord.epicbattle.view.bullet.AbstractNinjaView;

/**
 * Created by Quadrowin on 20.01.2016.
 */
public class Girl extends AbstractNinjaView {

    public Girl(AbstractBullet bullet, AbstractScreen screen) {
        super(bullet, screen);
    }

    @Override
    protected void initAnimations(AbstractScreen screen) {
        mAnimations.put(
                BulletState.RUN,
                screen.getSpriteAnimationLoader().createDrawable(
                    screen.getSkin(),
                    "animation/ninja_girl/run.png",
                    71,
                    99,
                    10,
                    0.1f,
                    2,
                    2,
                    true
                )
        );

        mAnimations.put(
                BulletState.ATTACK_PREPARE,
                screen.getSpriteAnimationLoader().createDrawable(
                    screen.getSkin(),
                    "animation/ninja_girl/attack.png",
                    92,
                    99,
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
                    "animation/ninja_girl/dead.png",
                    96,
                    99,
                    10,
                    0.1f,
                    2,
                    2,
                    false
                )
        );
    }

}
