package com.quadrolord.epicbattle.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Created by Quadrowin on 10.01.2016.
 */
public class SpriteAnimationActor extends Group {

    private SpriteAnimationDrawable mAnimation;

    private Runnable mOnFinish;

    private float mTime;

    @Override
    public void act(float delta) {
        mTime += delta;
        if (mAnimation.isAnimationFinished(mTime) && mOnFinish != null) {
            Runnable cb = mOnFinish;
            mOnFinish = null;
            cb.run();
        }
        mAnimation.setTime(mTime);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        applyTransform(batch, computeTransform());
        mAnimation.draw(batch, 0, 0, getWidth(), getHeight());
        drawChildren(batch, parentAlpha);
        resetTransform(batch);
    }

    public SpriteAnimationDrawable getAnimation() {
        return mAnimation;
    }

    public void reset() {
        mTime = 0;
    }

    public void setAnimationCallback(SpriteAnimationDrawable anim, Runnable onFinish) {
        mAnimation = anim;
        mOnFinish = onFinish;
        mTime = 0;
    }

    public void setAnimationLooped(SpriteAnimationDrawable anim) {
        mAnimation = anim;
        mTime = 0;
    }

}
