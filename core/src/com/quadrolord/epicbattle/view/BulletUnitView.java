package com.quadrolord.epicbattle.view;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.quadrolord.epicbattle.logic.BulletUnit;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class BulletUnitView extends Group {

    private BulletUnit mBullet;

    private ImageButton mWrapper;

    public BulletUnitView(BulletUnit bullet, AbstractScreen screen) {
        mBullet = bullet;
        mWrapper = new ImageButton(screen.getSkin().getDrawable("test-texture"));
        setBounds(mBullet.getPosition(), 20, 20, 20);
        screen.getStage().addActor(this);
        mWrapper.setBounds(0, 0, getWidth(), getHeight());
        this.addActor(mWrapper);
    }

    @Override
    public void act(float delta) {
        if (mBullet.isDied()) {
            this.removeActor(mWrapper);
        } else {
            super.act(delta);
            setX(mBullet.getPosition());
        }
    }

}
