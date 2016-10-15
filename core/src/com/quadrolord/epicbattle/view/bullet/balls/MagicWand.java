package com.quadrolord.epicbattle.view.bullet.balls;

import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.view.bullet.AbstractBallView;

/**
 * Created by Quadrowin on 10.07.2016.
 */
public class MagicWand extends AbstractBallView {

    public MagicWand(AbstractBullet bullet, AbstractScreen screen) {
        super(bullet, screen);
        setContentSize(0.7f);
    }

    @Override
    protected String getContentTextureFile() {
        return "balls/content/magic_wand.png";
    }

}
