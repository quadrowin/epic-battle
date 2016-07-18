package com.quadrolord.epicbattle.view.worker;

import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.view.BulletUnitView;
import com.quadrolord.epicbattle.view.SpriteAnimationDrawable;

/**
 * Created by Quadrowin on 20.01.2016.
 */
public class Girl extends BulletUnitView {

    public Girl(AbstractBullet bullet, AbstractScreen screen) {
        super(bullet, screen);
    }

    @Override
    protected SpriteAnimationDrawable getRunningAnimation(AbstractScreen screen) {
        return screen.getSpriteAnimationLoader().createDrawable(
                screen.getSkin(),
                "animation/ninja_girl/run.png",
                72,
                99,
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
                "animation/ninja_girl/attack.png",
                92,
                99,
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
                "animation/ninja_girl/dead.png",
                96,
                99,
                10,
                0.1f,
                2,
                2,
                false
        );
    }
}
