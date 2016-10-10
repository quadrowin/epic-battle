package com.quadrolord.epicbattle.view;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Created by Quadrowin on 10.01.2016.
 */
public class SpriteAnimationDrawable implements Drawable {

    private Animation mAnim;
    private float mWidth;
    private float mHeight;

    private boolean mIsLooped;

    private float mTime = 0;
    private float mTimePart = 0;

    /**
     * Смещение по X для анимаций, зависящих от перемещения.
     */
    private float mDeltaX = 0;

    /**
     * Направление: +-1
     * При изменении напрвления на все представление накладывается масштабирование "-1",
     * поэтому вращение в анимации будет иметь значение отличное от mDeltaX.
     */
    private float mDirection = 1;

    public SpriteAnimationDrawable(
            Animation animation,
            float width,
            float height,
            boolean isLooped
    ) {
        mAnim = animation;
        mWidth = width;
        mHeight = height;
        mIsLooped = isLooped;
    }

    public TextureRegion getTexture() {
        return mAnim.getKeyFrame(mTime, mIsLooped);
    }

    public Animation getAnimation() {
        return mAnim;
    }

    public void setTime(float time) {
        mTime = time;
        mTimePart = 0;
        if (mAnim != null) {
            float duration = mAnim.getAnimationDuration();
            if (duration > 0) {
                double steps = Math.floor(mTime / duration);
                mTimePart = (float)(mTime - duration * steps) / duration;
            }
        }
    }

    public void setTimePart(float time) {
        mTimePart = time;
        mTime = time;
        if (mAnim != null) {
            float duration = mAnim.getAnimationDuration();
            if (duration > 0) {
                mTime = time * duration;
            }
        }
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        batch.draw(getTexture(), 0, 0, mWidth, mHeight);
    }

    public boolean isAnimationFinished() {
        return mAnim.isAnimationFinished(mTime);
    }

    @Override
    public float getLeftWidth() {
        return 0;
    }

    @Override
    public void setLeftWidth(float leftWidth) {

    }

    @Override
    public float getRightWidth() {
        return 0;
    }

    @Override
    public void setRightWidth(float rightWidth) {

    }

    @Override
    public float getTopHeight() {
        return 0;
    }

    @Override
    public void setTopHeight(float topHeight) {

    }

    @Override
    public float getBottomHeight() {
        return 0;
    }

    @Override
    public void setBottomHeight(float bottomHeight) {

    }

    @Override
    public float getMinWidth() {
        return 0;
    }

    @Override
    public void setMinWidth(float minWidth) {

    }

    @Override
    public float getMinHeight() {
        return 0;
    }

    @Override
    public void setMinHeight(float minHeight) {

    }

    public float getDeltaX() {
        return mDeltaX;
    }

    public float getDirection() {
        return mDirection;
    }

    public float getHeight() {
        return mHeight;
    }

    public float getTime() {
        return mTime;
    }

    public float getTimePart() {
        return mTimePart;
    }

    public float getWidth() {
        return mWidth;
    }

    public void incDeltaX(float dx) {
        mDeltaX += dx;
    }

    public void setDeltaX(float dx) {
        mDeltaX = dx;
    }

    public void setDirection(float dir) {
        mDirection = dir > 0 ? 1 : -1;
    }

    public float getBaseDuration() {
        return mAnim.getAnimationDuration();
    }

}
