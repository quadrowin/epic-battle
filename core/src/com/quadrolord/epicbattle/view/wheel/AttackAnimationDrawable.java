package com.quadrolord.epicbattle.view.wheel;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.quadrolord.epicbattle.view.SpriteAnimationDrawable;

/**
 * Created by Quadrowin on 11.07.2016.
 */
public class AttackAnimationDrawable extends SpriteAnimationDrawable {

    private Texture mTexture;
    private TextureRegion mTextureRegion;

    public AttackAnimationDrawable(Animation animation, float width, float height, boolean isLooped) throws Exception {
        super(animation, width, height, isLooped);
        throw new Exception("Not implemented");
    }

    public AttackAnimationDrawable(Texture texture, float width, float height) {
        super(null, width, height, true);
        mTexture = texture;
        mTextureRegion = new TextureRegion(mTexture);
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        float halfWidth = getWidth() / 2;
        float halfHeight = getHeight() / 2;
        batch.draw(
                mTexture,
                0,     // x
                getHeight() * (float)Math.abs(Math.sin(3.14 * getTime())),    // y
                halfWidth, halfHeight,  // originX, originY (центр колеса)
                getWidth(), getHeight(), // width, height
                1f, 1f,         // scaleX, scaleY
                -3.14f * getDeltaX(),
                0, 0,           // srcX, srcY
                mTexture.getWidth(), mTexture.getHeight(),  // srcWidth, srcHeight
                false, false    // flipX, flipY
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

}
