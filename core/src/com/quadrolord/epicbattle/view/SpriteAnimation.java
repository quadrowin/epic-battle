package com.quadrolord.epicbattle.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Quadrowin on 10.01.2016.
 */
public class SpriteAnimation implements Drawable {

    private Animation mAnim;
    private String mResource;
    private int mWidth;
    private int mHeight;
    private int mFramesCount;

    private boolean mIsLooped;
    private int mPaddingX;
    private int mPaddingY;

    private float mFrame = 0;

    public SpriteAnimation(
            Skin skin,
            String resource,
            int width,
            int height,
            int framesCount,
            int paddingX,
            int paddingY,
            boolean isLooped
    ) {
        mResource = resource;
        mWidth = width;
        mHeight = height;
        mFramesCount = framesCount;
        mPaddingX = paddingX;
        mPaddingY = paddingY;
        mIsLooped = isLooped;

        mAnim = getAnimation(skin);
    }

    public void act(float delta) {
        mFrame += delta;

        if (mFrame >= mFramesCount && !mIsLooped) {
            // remove();
        }
    }

    public TextureRegion getTexture() {
        return mAnim.getKeyFrame(mFrame, mIsLooped);
    }

    private Animation getAnimation(Skin skin) {
        if (skin.has(mResource, Animation.class)) {
            return skin.get(mResource, Animation.class);
        }

        Array<TextureRegion> frames = new Array<TextureRegion>();

        Texture texture = new Texture(mResource);
        TextureRegion region = new TextureRegion(texture);

        int width = mWidth + 2 * mPaddingX - 1;

        for (int i = 0; i < mFramesCount; i++) {
            frames.add(new TextureRegion(region, i * width, mPaddingY, width, mHeight));
        }

        Animation anim = new Animation(0.1f, frames);
        skin.add(mResource, anim);

        return anim;
    }

    public void reset() {
        mFrame = 0;
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
