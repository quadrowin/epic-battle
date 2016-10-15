package com.quadrolord.epicbattle.view.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.ArrayMap;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.BulletState;
import com.quadrolord.epicbattle.screen.battle.Shadow;
import com.quadrolord.epicbattle.view.SpriteAnimationActor;
import com.quadrolord.epicbattle.view.SpriteAnimationDrawable;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public abstract class AbstractBulletView extends Group {

    private AbstractBullet mBullet;

    protected abstract void initAnimations(AbstractScreen screen);

    public AbstractBulletView(AbstractBullet bullet, AbstractScreen screen) {
        mBullet = bullet;
        mBullet.setViewObject(this);
        screen.getStage().addActor(this);
        initAnimations(screen);
    }

    public AbstractBullet getBullet() {
        return mBullet;
    }


}
