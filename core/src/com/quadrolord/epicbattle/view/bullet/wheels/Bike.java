package com.quadrolord.epicbattle.view.bullet.wheels;

import com.badlogic.gdx.graphics.Texture;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.BulletState;
import com.quadrolord.epicbattle.view.bullet.AbstractWheelView;
import com.quadrolord.epicbattle.view.wheel.AttackAnimationDrawable;
import com.quadrolord.epicbattle.view.wheel.DeadAnimationDrawable;
import com.quadrolord.epicbattle.view.wheel.WalkAnimationDrawable;

/**
 * Created by Quadrowin on 10.07.2016.
 */
public class Bike extends AbstractWheelView {

    public Bike(AbstractBullet bullet, AbstractScreen screen) {
        super(bullet, screen);
    }

    protected String getWheelTextureFile() {
        return "wheel/bike.png";
    }

}
