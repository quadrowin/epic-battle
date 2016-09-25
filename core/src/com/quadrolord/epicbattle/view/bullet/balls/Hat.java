package com.quadrolord.epicbattle.view.bullet.balls;

import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.view.bullet.AbstractBallView;
import com.quadrolord.epicbattle.view.bullet.AbstractWheelView;

/**
 * Created by morph on 11.01.2016.
 */
public class Hat extends AbstractBallView {

    public Hat(AbstractBullet bullet, AbstractScreen screen) {
        super(bullet, screen);
    }

    @Override
    protected String getContentTextureFile() {
        return "balls/content/hat.png";
    }

}
