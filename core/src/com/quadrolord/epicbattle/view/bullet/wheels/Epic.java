package com.quadrolord.epicbattle.view.bullet.wheels;

import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.view.bullet.AbstractWheelView;

/**
 * Created by Goorus on 28.07.2016.
 */
public class Epic extends AbstractWheelView {

    public Epic(AbstractBullet bullet, AbstractScreen screen) {
        super(bullet, screen);
    }

    @Override
    protected String getWheelTextureFile() {
        return "wheel/epic.png";
    }
}
