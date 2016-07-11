package com.quadrolord.epicbattle.view.wheel;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.quadrolord.epicbattle.view.SpriteAnimationDrawable;

/**
 * Created by Quadrowin on 11.07.2016.
 */
public class DeadAnimationDrawable extends SpriteAnimationDrawable {

    private Texture mTexture;
    private TextureRegion mTextureRegion;
    private float mLength = 1;

    public DeadAnimationDrawable(Animation animation, int width, int height, boolean isLooped) throws Exception {
        super(animation, width, height, isLooped);
        throw new Exception("Not implemented");
    }

    public DeadAnimationDrawable(Texture texture) {
        super(null, texture.getWidth(), texture.getHeight(), true);
        mTexture = texture;
        mTextureRegion = new TextureRegion(mTexture);
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        float halfWidth = getWidth() / 2;
        float halfHeight = getHeight() / 2;
        batch.draw(
                mTexture,
                -halfWidth + Math.signum(getDeltaX()) * getTime() * 30,                 // x
                -halfHeight + getHeight() * (float)Math.abs(Math.sin(getTime() * 3)),   // y
                halfWidth, halfHeight,      // originX, originY (центр колеса)
                getWidth(), getHeight(),    // width, height
                1 - 0.5f * getTime() / mLength, // scaleX
                1 - 0.5f * getTime() / mLength, // scaleY
                -3.14f * getDeltaX() + getTime() * 10,
                0, 0,                   // srcX, srcY
                mTexture.getWidth(),    // srcWidth
                mTexture.getHeight(),   // srcHeight
                false, false    // flipX, flipY
        );
    }

    @Override
    public boolean isAnimationFinished(float stateTime) {
        return stateTime >= mLength;
    }

    @Override
    public TextureRegion getTexture() {
        return mTextureRegion;
    }

}
