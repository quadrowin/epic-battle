package com.quadrolord.epicbattle.view.ball;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.quadrolord.epicbattle.RM;
import com.quadrolord.epicbattle.view.SpriteAnimationDrawable;

/**
 * Created by Quadrowin on 10.07.2016.
 */
public class WalkAnimationDrawable extends SpriteAnimationDrawable {

    private Texture mBallTexture;
    private Texture mContentTexture;
    private Texture mEnergyTexture;
    private float mContentSize = 0.8f;
    private TextureRegion mTextureRegion;

    public WalkAnimationDrawable(Animation animation, float width, float height, boolean isLooped) throws Exception {
        super(animation, width, height, isLooped);
        throw new Exception("Not implemented");
    }

    public WalkAnimationDrawable(Texture ballTexture, Texture contentTexture, float width, float height) {
        super(null, width, height, true);
        mEnergyTexture = RM.getTextures().get("balls/content/energy.png");
        mBallTexture = ballTexture;
        mContentTexture = contentTexture;
        mTextureRegion = new TextureRegion(mBallTexture);
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        float halfWidth = getWidth() / 2;
        float halfHeight = getHeight() / 2;

        // крутящаяся энергия
        batch.draw(
                mEnergyTexture,
                0, 0,
                halfWidth, halfHeight,      // originX, originY (центр колеса)
                getWidth(), getHeight(),    // width, height
                1f, 1f,                     // scaleX, scaleY
                -getDeltaX() * 3.14f,       // rotation
                0, 0,                       // srcX, srcY
                mEnergyTexture.getWidth(),  // srcWidth
                mEnergyTexture.getHeight(), // srcHeight
                false, false                // flipX, flipY
        );

        // содержимое
        batch.draw(
                mContentTexture,
                (getWidth() * (1 - mContentSize)) / 2,  // x
                (getHeight() * (1 - mContentSize)) / 2, // y
                halfWidth, halfHeight,      // originX, originY (центр колеса)
                getWidth() * mContentSize,  // width
                getHeight() * mContentSize, // height
                1f, 1f,                     // scaleX, scaleY
                (float)Math.sin(-getDeltaX() * 0.05) * 10,       // rotation - покачивание
                0, 0,                       // srcX, srcY
                mContentTexture.getWidth(), // srcWidth
                mContentTexture.getHeight(),// srcHeight
                false, false                // flipX, flipY
        );

        // оболочка шара
        batch.draw(
                mBallTexture,
                0, 0,
                halfWidth, halfHeight,      // originX, originY (центр колеса)
                getWidth(), getHeight(),    // width, height
                1f, 1f,                     // scaleX, scaleY
                0,                          // rotation
                0, 0,                       // srcX, srcY
                mBallTexture.getWidth(),    // srcWidth
                mBallTexture.getHeight(),   // srcHeight
                false, false                // flipX, flipY
        );

    }

    @Override
    public boolean isAnimationFinished() {
        return false;
    }

    @Override
    public float getBaseDuration() {
        return 1;
    }

    @Override
    public TextureRegion getTexture() {
        return mTextureRegion;
    }

    public void setContentSize(float size)
    {
        mContentSize = size;
    }

}
