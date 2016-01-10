package com.quadrolord.epicbattle.screen.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.quadrolord.epicbattle.logic.GameUnit;

/**
 * Created by Quadrowin on 10.01.2016.
 */
public class SpriteAnimation extends Group {

    private Animation mAnim;
    private String mResource;
    private int mWidth;
    private int mHeight;
    private int mFramesCount;
    private boolean mFlipX;
    private GameUnit mUnit;
    private boolean mIsLooped;
    private int mPaddingX;
    private int mPaddingY;

    private int mFrame = 0;

    public SpriteAnimation(
            GameUnit unit,
            Skin skin,
            Stage stage,
            String resource,
            int width,
            int height,
            int framesCount,
            int paddingX,
            int paddingY,
            boolean flipX,
            boolean isLooped
    ) {
        mResource = resource;
        mWidth = width;
        mHeight = height;
        mFramesCount = framesCount;
        mFlipX = flipX;
        mUnit = unit;
        mPaddingX = paddingX;
        mPaddingY = paddingY;
        mIsLooped = isLooped;

        mAnim = getAnimation(skin);

        stage.addActor(this);
    }

    @Override
    public void act(float delta) {
        ++mFrame;

        if (mFrame >= mFramesCount) {
            if (!mIsLooped) {
                remove();
            } else {
                mFrame = 0;
            }
        }
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {

    }

    public TextureRegion getTexture() {
        return mAnim.getKeyFrames()[mFrame];
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

        Animation anim = new Animation(0.25f, frames);
        skin.add(mResource, anim);

        return anim;
    }

    public void reset() {
        mFrame = 0;
    }
}
