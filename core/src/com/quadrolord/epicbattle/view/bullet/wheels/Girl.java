package com.quadrolord.epicbattle.view.bullet.wheels;

import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.bullet.worker.BulletState;
import com.quadrolord.epicbattle.view.bullet.AbstractBulletView;
import com.quadrolord.epicbattle.view.bullet.AbstractWheelView;

/**
 * Created by Quadrowin on 20.01.2016.
 */
public class Girl extends AbstractWheelView {

    public Girl(AbstractBullet bullet, AbstractScreen screen) {
        super(bullet, screen);
    }

    @Override
    protected String getWheelTextureFile() {
        return "wheel/some_new.png";
    }

}
