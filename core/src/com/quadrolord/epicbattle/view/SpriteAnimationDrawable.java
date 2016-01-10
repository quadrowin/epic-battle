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
    private int mWidth;
    private int mHeight;

    private boolean mIsLooped;

    private float mTime = 0;

    public SpriteAnimationDrawable(
            Animation animation,
            int width,
            int height,
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
        batch.draw(
                getTexture(),
                (width - mWidth) / 2, (height - mHeight) / 2, mWidth, mHeight
        );
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
}
