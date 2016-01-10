package com.quadrolord.epicbattle.screen.battle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private GameUnit mUnit;
    private boolean mIsLooped;
    private int mPaddingX;
    private int mPaddingY;

    private float mFrame = 0;

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
            boolean isLooped
    ) {
        mResource = resource;
        mWidth = width;
        mHeight = height;
        mFramesCount = framesCount;
        mUnit = unit;
        mPaddingX = paddingX;
        mPaddingY = paddingY;
        mIsLooped = isLooped;

        mAnim = getAnimation(skin);

        stage.addActor(this);
    }

    @Override
    public void act(float delta) {
        mFrame += delta;

        if (mFrame >= mFramesCount && !mIsLooped) {
            remove();
        }
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {

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
}
