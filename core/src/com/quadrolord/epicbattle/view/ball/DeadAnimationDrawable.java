package com.quadrolord.epicbattle.view.ball;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.quadrolord.epicbattle.RM;
import com.quadrolord.epicbattle.view.SpriteAnimationDrawable;

/**
 * Created by Quadrowin on 11.07.2016.
 */
public class DeadAnimationDrawable extends SpriteAnimationDrawable implements AnimationWithContent {

    private Texture mBallTexture;
    private Texture mContentTexture;
    private float mContentSize = 0.8f;
    private Texture mEnergyTexture;
    private TextureRegion mTextureRegion;
    private float mLength = 1;

    public DeadAnimationDrawable(Animation animation, float width, float height, boolean isLooped) throws Exception {
        super(animation, width, height, isLooped);
        throw new Exception("Not implemented");
    }

    public DeadAnimationDrawable(Texture ballTexture, Texture contentTexture, float width, float height) {
        super(null, width, height, true);
        mBallTexture = ballTexture;
        mContentTexture = contentTexture;
        mEnergyTexture = RM.getTextures().get("balls/content/energy.png");
        mTextureRegion = new TextureRegion(mBallTexture);
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        float halfWidth = getWidth() / 2;
        float halfHeight = getHeight() / 2;
        float scale = Math.max(0, 1 - 0.5f * getTime() / mLength);
        if (scale == 0) {
            return;
        }

        // содержимое
        batch.draw(
                mContentTexture,
                (getWidth() * (1 - mContentSize)) / 2 - getDirection() * getTime() * 30,  // x
                (getHeight() * (1 - mContentSize)) / 2 + getHeight() * (float)Math.abs(Math.sin(getTime() * 3)), // y
                halfWidth, halfHeight,      // originX, originY (центр колеса)
                getWidth() * mContentSize,  // width
                getHeight() * mContentSize, // height
                scale, scale,                     // scaleX, scaleY
                (float)Math.sin(-getDeltaX() * 0.05) * 10,       // rotation - покачивание
                0, 0,                       // srcX, srcY
                mContentTexture.getWidth(), // srcWidth
                mContentTexture.getHeight(),// srcHeight
                false, false                // flipX, flipY
        );

        // оболочка
        batch.draw(
                mBallTexture,
                -getDirection() * getTime() * 30,                           // x
                getHeight() * (float)Math.abs(Math.sin(getTime() * 3)),     // y
                halfWidth, halfHeight,      // originX, originY (центр колеса)
                getWidth(), getHeight(),    // width, height
                scale, scale,               // scaleX, scaleY
                -3.14f * getDeltaX() + getTime() * 10,
                0, 0,                   // srcX, srcY
                mBallTexture.getWidth(),    // srcWidth
                mBallTexture.getHeight(),   // srcHeight
                false, false    // flipX, flipY
        );
    }

    @Override
    public boolean isAnimationFinished() {
        return getTime() >= mLength;
    }

    @Override
    public float getBaseDuration() {
        return 3;
    }

    @Override
    public TextureRegion getTexture() {
        return mTextureRegion;
    }

    @Override
    public void setContentSize(float scale) {
        mContentSize = scale;
    }
}
