package com.quadrolord.epicbattle.view;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class BulletUnitView extends Group {

    private AbstractBullet mBullet;

    private ImageButton mWrapper;

    public BulletUnitView(AbstractBullet bullet, AbstractScreen screen) {
        mBullet = bullet;
        mBullet.setViewObject(this);

        setBounds(mBullet.getX(), 20, 20, 20);
        screen.getStage().addActor(this);

        mWrapper = new ImageButton(screen.getSkin().getDrawable("test-texture"));
        mWrapper.setBounds(0, 0, getWidth(), getHeight());
        addActor(mWrapper);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setX(mBullet.getX());
    }

}
