package com.quadrolord.epicbattle.view.bullet.wheels;

import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.bullet.worker.BulletState;
import com.quadrolord.epicbattle.view.bullet.AbstractBulletView;
import com.quadrolord.epicbattle.view.bullet.AbstractWheelView;

/**
 * Created by morph on 11.01.2016.
 */
public class Simple extends AbstractWheelView {

    public Simple(AbstractBullet bullet, AbstractScreen screen) {
        super(bullet, screen);
    }

    @Override
    protected String getWheelTextureFile() {
        return "wheel/simple.png";
    }

}
