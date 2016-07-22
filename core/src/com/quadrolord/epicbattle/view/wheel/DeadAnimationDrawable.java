package com.quadrolord.epicbattle.view.wheel;

import com.badlogic.gdx.Gdx;
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

    public DeadAnimationDrawable(Animation animation, float width, float height, boolean isLooped) throws Exception {
        super(animation, width, height, isLooped);
        throw new Exception("Not implemented");
    }

    public DeadAnimationDrawable(Texture texture, float width, float height) {
        super(null, width, height, true);
        mTexture = texture;
        mTextureRegion = new TextureRegion(mTexture);
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        float halfWidth = getWidth() / 2;
        float halfHeight = getHeight() / 2;
        float scale = Math.max(0, 1 - 0.5f * getTime() / mLength);
        Gdx.app.log("DeadAnimDraw", "time " + getTime() + " scale " + scale);
        if (scale == 0) {
            return;
        }
        batch.draw(
                mTexture,
                Math.signum(-getDeltaX()) * getTime() * 30,                 // x
                getHeight() * (float)Math.abs(Math.sin(getTime() * 3)),    // y
                halfWidth, halfHeight,      // originX, originY (центр колеса)
                getWidth(), getHeight(),    // width, height
                scale, scale,               // scaleX, scaleY
                -3.14f * getDeltaX() + getTime() * 10,
                0, 0,                   // srcX, srcY
                mTexture.getWidth(),    // srcWidth
                mTexture.getHeight(),   // srcHeight
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

}
