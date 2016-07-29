package com.quadrolord.epicbattle.view.bullet;

import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.bullet.worker.BulletState;
import com.quadrolord.epicbattle.view.bullet.AbstractBulletView;

/**
 * Created by morph on 11.01.2016.
 */
public class Forks extends AbstractWheelView {

    public Forks(AbstractBullet bullet, AbstractScreen screen) {
        super(bullet, screen);
    }

    @Override
    protected String getWheelTextureFile() {
        return "wheel/sedan.png";
    }

}
