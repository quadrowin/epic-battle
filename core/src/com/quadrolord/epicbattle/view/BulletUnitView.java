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
        screen.addStageBounds(this, 10, 10, 20, 20);
        mWrapper.setBounds(0, 0, getWidth(), getHeight());
        this.addActor(mWrapper);
    }

    @Override
    public void act(float delta) {
        setX(mBullet.Position);
    }

}
