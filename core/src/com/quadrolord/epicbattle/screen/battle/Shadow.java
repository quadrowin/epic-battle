package com.quadrolord.epicbattle.screen.battle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.view.BulletUnitView;

/**
 * Created by morph on 15.01.2016.
 */
public class Shadow extends Group {
    BulletUnitView mUnitView;
    Image mShadow;

    public Shadow(BulletUnitView unitView, AbstractScreen screen) {
        mUnitView = unitView;
        mShadow = new Image(new Texture("shadow.png"));

        mShadow.setWidth(Math.round(unitView.getWidth() * 2));
        mShadow.setHeight(16);
        mShadow.setY(-unitView.getHeight() - mShadow.getHeight());
        mShadow.setX(getRealX());

        mUnitView.addActor(mShadow);
    }

    public void act(float delta) {
        mShadow.setX(getRealX());
    }

    private float getRealX() {
        return mUnitView.getOriginX() - mUnitView.getWidth() / 2 - mShadow.getWidth() / 2;
    }
}
