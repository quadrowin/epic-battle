package com.quadrolord.epicbattle.view.bullet.balls;

import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.BulletState;
import com.quadrolord.epicbattle.view.ball.WalkAnimationDrawable;
import com.quadrolord.epicbattle.view.bullet.AbstractBallView;

/**
 * Created by Goorus on 27.09.2016.
 */

public class Dice extends AbstractBallView {

    public Dice(AbstractBullet bullet, AbstractScreen screen) {
        super(bullet, screen);
        ((WalkAnimationDrawable)getAnimation(BulletState.RUN)).setContentSize(0.6f);
    }

    @Override
    protected String getContentTextureFile() {
        return "balls/content/dice.png";
    }

}
