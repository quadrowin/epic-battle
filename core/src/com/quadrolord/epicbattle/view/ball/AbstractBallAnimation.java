package com.quadrolord.epicbattle.view.ball;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.quadrolord.epicbattle.view.bullet.AbstractBulletView;

/**
 * Created by Goorus on 15.10.2016.
 */
abstract public class AbstractBallAnimation implements AnimationWithContent {

    private AbstractBulletView mActor;

    private float mContentSize = 0.8f;

    public AbstractBallAnimation(AbstractBulletView actor) {
        mActor = actor;
    }

    abstract public void draw(Batch batch, float width, float height);

    public AbstractBulletView getActor() {
        return mActor;
    }

    public float getContentSize() {
        return mContentSize;
    }

    @Override
    public void setContentSize(float scale) {
        mContentSize = scale;
    }

    public boolean isAnimationFinished() {
        return mActor.getBullet().getTime().statePart >= 1;
    }

}
