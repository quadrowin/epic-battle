package com.quadrolord.epicbattle.screen.battle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.view.BulletUnitView;

/**
 * Created by morph on 15.01.2016.
 */
public class Shadow {
    BulletUnitView mUnitView;
    Image mShadow;

    public Shadow(BulletUnitView unitView, AbstractScreen screen) {
        mUnitView = unitView;
        mShadow = new Image(screen.getTextures().get("shadow.png"));

        mShadow.setWidth(unitView.getWidth() * .75f);
        mShadow.setHeight(Math.max(15, unitView.getHeight() * .1f));
        mShadow.setY(-mShadow.getHeight());
        mShadow.setX((mUnitView.getWidth() - mShadow.getWidth()) / 2);

        mUnitView.addActor(mShadow);
    }

}
