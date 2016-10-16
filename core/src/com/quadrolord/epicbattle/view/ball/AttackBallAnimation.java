package com.quadrolord.epicbattle.view.ball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.quadrolord.epicbattle.RM;
import com.quadrolord.epicbattle.logic.tower.UnitTime;
import com.quadrolord.epicbattle.view.bullet.AbstractBallView;
import com.quadrolord.epicbattle.view.bullet.AbstractBulletView;
import com.quadrolord.epicbattle.view.visualization.BallAttackEffect;

/**
 * Created by Quadrowin on 11.07.2016.
 */
public class AttackBallAnimation extends AbstractBallAnimation {

    private static final String TAG = "AttackBallAnimation";

    private Animation mEffect;

    private boolean mFinishEffectCreated = false;
    private Texture mBallTexture;
    private Texture mContentTexture;
    private Texture mEnergyTexture;

    private TextureRegion mTextureRegion;

    private float[] mTempSquareVertices;

    public AttackBallAnimation(Texture ballTexture, Texture contentTexture, AbstractBulletView actor) {
        super(actor);

        mBallTexture = ballTexture;
        mContentTexture = contentTexture;
        mEnergyTexture = RM.getTextures().get("balls/content/energy.png");
        mTextureRegion = new TextureRegion(mBallTexture);

        mEffect = getAnimationResource();

        float clr = Color.toFloatBits(255, 255, 255, 255);
        mTempSquareVertices = new float[] {
                0, 0, clr, 0, 0,
                1, 0, clr, 1, 0,
                1, 1, clr, 1, 1,
                0, 1, clr, 0, 1,
        };
    }

    public Animation getAnimationResource() {
        String[][] sources = {
//                {"balls/flash/gif-anim", "_delay-0.04s.gif", "28"},
//                {"balls/flash/gif-anim2", "_delay-0.1s.gif", "56"},
                {"balls/flash/gif-anim3", "_delay-0.04s.gif", "28"},
        };

        int animIndex = (int)(Math.random() * sources.length);

        String dir = sources[animIndex][0];
        if (RM.getSkin().has(dir, Animation.class)) {
            return RM.getSkin().get(dir, Animation.class);
        }

        Array<TextureRegion> frames = new Array<TextureRegion>();
        int framesCount = Integer.valueOf(sources[animIndex][2]);
        for (int i = 0; i < 28; i++) {
//            String textureFile = dir + "/frame_" + (i < 10 ? "0" + i : i) + "_delay-0.04s.gif";
            String textureFile = dir + "/frame_" + (i < 10 ? "0" + i : i) + sources[animIndex][1];
            Texture texture = RM.getTextures().get(textureFile);
            TextureRegion region = new TextureRegion(texture);
            frames.add(region);
        }
        frames.reverse();

        Animation anim = new Animation(1 / 28, frames);
        RM.getSkin().add(dir, anim);

        return anim;
    }

    @Override
    public void draw(Batch batch, float width, float height) {
        UnitTime time = getActor().getBullet().getTime();
        float contentSize = getContentSize();
        float direction = getActor().getBullet().getDirection();

        float halfWidth = width / 2;
        float halfHeight = height / 2;

        float dy = 0;
//        dy = getHeight() * (float)Math.abs(Math.sin(3.14 * getTime()));

        // крутящаяся энергия
        batch.setColor(1, 0, 0, 0.5f);
        batch.draw(
                mEnergyTexture,
                -halfWidth, dy,                      // x, y
                halfWidth, halfHeight,      // originX, originY (центр колеса)
                width, height,    // width, height
                1f, 1f,                     // scaleX, scaleY
                -direction * time.existsTime * 110,      // rotation
                0, 0,                       // srcX, srcY
                mEnergyTexture.getWidth(),  // srcWidth
                mEnergyTexture.getHeight(), // srcHeight
                direction < 0, false                // flipX, flipY
        );
        batch.setColor(1, 1, 1, 1f);

        // содержимое
        batch.draw(
                mContentTexture,
                -halfWidth + (width * (1 - contentSize)) / 2,          // x
                (height * (1 - contentSize)) / 2 + dy,    // y
                halfWidth, halfHeight,      // originX, originY (центр колеса)
                width * contentSize,  // width
                height * contentSize, // height
                1f, 1f,                     // scaleX, scaleY
                (float)Math.sin(time.existsTime * 3) * 10,       // rotation - покачивание
                0, 0,                       // srcX, srcY
                mContentTexture.getWidth(), // srcWidth
                mContentTexture.getHeight(),// srcHeight
                false, false                // flipX, flipY
        );

        // оболочка шара
        batch.draw(
                mBallTexture,
                -halfWidth, dy,    // x, y
                halfWidth, halfHeight,  // originX, originY (центр колеса)
                width, height, // width, height
                1f, 1f,         // scaleX, scaleY
                0,
                0, 0,           // srcX, srcY
                mBallTexture.getWidth(), mBallTexture.getHeight(),  // srcWidth, srcHeight
                false, false    // flipX, flipY
        );


        float et = time.statePart * 3 % 5;
        float animDuration = 0.5f;
        int animFrameIndex = (int)((time.statePart % animDuration) / animDuration * mEffect.getKeyFrames().length);
//        Gdx.app.log(TAG, "anim frame " + animFrameIndex + " by " + getTimePart());

        if (et < 2) {
            float es = et / 2;
            batch.setColor(1, 1, 1, es);
            if (getActor().getBullet().getDirection() < 0) {
                batch.draw(
                        mEffect.getKeyFrames()[animFrameIndex],
                        width * (1 - es) / 2 - (es - 1) * width / 2 - halfWidth,
                        height * (1 - es) / 2,
                        width * es, height * es
                );
            } else {
                float drawX = width * (1 + es) / 2 + (es - 1) * width / 2 - halfWidth;
                float drawY = height * (1 - es) / 2;
                float drawW = -width * es;
                float drawH = height * es;
                mTempSquareVertices[0] = drawX;
                mTempSquareVertices[1] = drawY + drawH;
                mTempSquareVertices[5] = drawX + drawW;
                mTempSquareVertices[6] = drawY + drawH;
                mTempSquareVertices[10] = drawX + drawW;
                mTempSquareVertices[11] = drawY;
                mTempSquareVertices[15] = drawX;
                mTempSquareVertices[16] = drawY;

                if (es < 0.5f) {
                    mTempSquareVertices[6] += height * es / 2;
                    mTempSquareVertices[11] += -height * es / 2;
                } else {
                    mTempSquareVertices[6] += height / 4 - height * (es - 0.5f) / 2;
                    mTempSquareVertices[11] += -height / 4 + height * (es - 0.5f) / 2;
                }

                batch.draw(mEffect.getKeyFrames()[animFrameIndex].getTexture(), mTempSquareVertices, 0, mTempSquareVertices.length);
//                batch.draw(
//                        mEffect.getKeyFrames()[animFrameIndex],
//                        getWidth() * (1 + es) / 2 + (es - 1) * getWidth() / 2, getHeight() * (1 - es) / 2,
//                        -getWidth() * es, getHeight() * es
//                );
            }
            mFinishEffectCreated = false;
        } else if (et < 2) {
            et -= 1;
            float es;
            if (et < 0.25f) {
                es = 1.00f - (et - 0.00f) / 0.25f * 0.50f;
            } else if (et < 0.5f) {
                es = 0.50f + (et - 0.25f);
            } else if (et < 0.75f) {
                es = 0.75f - (et - 0.50f);
            } else {
                es = 0.50f + (et - 0.75f) / 0.25f * 0.50f;
            }
            batch.setColor(1, 1, 1, es);
            if (getActor().getBullet().getDirection() < 0) {
                batch.draw(
                        mEffect.getKeyFrames()[animFrameIndex],
                        width * (1 - es) / 2, height * (1 - es) / 2,
                        width * es, height * es
                );
            } else {
                batch.draw(
                        mEffect.getKeyFrames()[animFrameIndex],
                        width * (1 + es) / 2, height * (1 - es) / 2,
                        -width * es, height * es
                );
            }
            mFinishEffectCreated = false;
        } else if (!mFinishEffectCreated) {
            mFinishEffectCreated = true;
            et -= 2;

            float dx = et * halfWidth * 2;
            float clr = Color.toFloatBits(255, 255, 255, 255);

            float ss = et * halfHeight;
            float textureY = 0; // 0 or 1

            /**
             * +-----+-----+
             * |     |  3  |
             * |     |     |
             * | 2   |     |
             * +-----+-----+
             * | 1   |   4 |
             * |     |     |
             * |     |     |
             * +-----+-----+
             * all from center
             */
            float[] ff = new float[] {
                    // 1
                    dx + halfWidth, halfHeight,
                    clr, 0.5f, 0.5f,
                    dx + halfWidth * (1 - 0.75f * et), 0 + ss,
                    clr, 0.5f, 1 - textureY,
                    dx + 0, 0 + ss,
                    clr, 0.0f, 1 - textureY,
                    dx + halfWidth * (0 + 0.75f * et), halfHeight,
                    clr, 0.0f, 0.5f,

                    // 2
                    dx + halfWidth, halfHeight,
                    clr, 0.5f, 0.5f,
                    dx + halfWidth * (0 + 0.75f * et), halfHeight,
                    clr, 0.0f, 0.5f,
                    dx + 0, halfHeight * 2 - ss,
                    clr, 0.0f, textureY,
                    dx + halfWidth * (1 - 0.75f * et), halfHeight * 2 - ss,
                    clr, 0.5f, textureY,

                    // 3
                    dx + halfWidth, halfHeight,
                    clr, 0.5f, 0.5f,
                    dx + halfWidth * (1 - 0.75f * et), halfHeight * 2 - ss,
                    clr, 0.5f, textureY,
                    dx + halfWidth * (2 - 1.50f * et), halfHeight * 2 - ss,
                    clr, 1.0f, textureY,
                    dx + halfWidth * (2 - 0.75f * et), halfHeight,
                    clr, 1.0f, 0.5f,

                    // 4
                    dx + halfWidth, halfHeight,
                    clr, 0.5f, 0.5f,
                    dx + halfWidth * (2 - 0.75f * et), halfHeight,
                    clr, 1.0f, 0.5f,
                    dx + halfWidth * (2 - 1.50f * et), 0 + ss,
                    clr, 1.0f, 1 - textureY,
                    dx + halfWidth * (1 - 0.75f * et), 0 + ss,
                    clr, 0.5f, 1 - textureY,
            };

            // необходимо сразу нарисовать, чтобы не было пропуска кадра
            BallAttackEffect ef = BallAttackEffect.create(getActor(), mEffect, animFrameIndex, ff, getActor().getBullet().getDirection(), width, height);
            ef.act(0);
            ef.draw(batch, 1);
        }

        batch.setColor(1, 1, 1, 1);
    }

}
