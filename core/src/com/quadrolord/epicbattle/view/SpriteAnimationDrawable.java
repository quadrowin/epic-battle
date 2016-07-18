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

    /**
     * Смещение по X для анимаций, зависящих от перемещения.
     */
    private float mDeltaX = 0;

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
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        batch.draw(getTexture(), 0, 0, mWidth, mHeight);
    }

    public boolean isAnimationFinished(float stateTime) {
        return mAnim.isAnimationFinished(stateTime);
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

    public float getHeight() {
        return mHeight;
    }

    public float getTime() {
        return mTime;
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

}
