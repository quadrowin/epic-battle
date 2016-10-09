package com.quadrolord.epicbattle.view.visualization;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Goorus on 09.10.2016.
 */

public class BallAttackEffect extends Group {

    private Animation mAnimation;

    private float mAnimationDeltaTime;

    private float[] mCurrentVertices;

    private float mDirection;

    private float mExistsTime = 0;

    private float mMaxExistsTime = 1;

    private BallAttackEffect() {

    }

    public static BallAttackEffect create(Actor parent, Animation animation, float animationDeltaTime, float[] vertices, float direction) {
        BallAttackEffect ef = new BallAttackEffect();
        ef.mAnimation = animation;
        ef.mAnimationDeltaTime = animationDeltaTime;
        ef.mCurrentVertices = vertices;
        ef.mDirection = direction;
        ef.setBounds(parent.getX(), parent.getY(), parent.getWidth(), parent.getHeight());
        parent.getParent().addActor(ef);
        return ef;
    }

    @Override
    public void act(float delta) {
        mExistsTime += delta;
        float et = mExistsTime / mMaxExistsTime;

        if (et > 1) {
            remove();
            return;
        }

        float halfWidth = getWidth() / 2;
        float halfHeight = getHeight() / 2;

        float dx = et * halfWidth * 2 * mDirection;
        if (mDirection < 0) {
            dx += halfWidth * 2;
        }

        et = Math.min(1, et);
        float ss = et * halfHeight;

        float min_hh = halfHeight;// halfHeight / 4; // min half height

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
        // 1
        mCurrentVertices[ 0] = dx + halfWidth * mDirection;
        mCurrentVertices[ 1] = halfHeight;
        mCurrentVertices[ 5] = dx + halfWidth * (1 - 0.75f * et) * mDirection;
        mCurrentVertices[ 6] = 0 + Math.min(halfHeight - min_hh, ss);
        mCurrentVertices[10] = dx + 0;
        mCurrentVertices[11] = 0 + Math.min(halfHeight - min_hh, ss);
        mCurrentVertices[15] = dx + halfWidth * (0 + 0.75f * et) * mDirection;
        mCurrentVertices[16] = halfHeight;

        // 2
        mCurrentVertices[20] = dx + halfWidth * mDirection;
        mCurrentVertices[21] = halfHeight;
        mCurrentVertices[25] = dx + halfWidth * (0 + 0.75f * et) * mDirection;
        mCurrentVertices[26] = halfHeight;
        mCurrentVertices[30] = dx + 0;
        mCurrentVertices[31] = halfHeight + Math.max(min_hh, halfHeight - ss);
        mCurrentVertices[35] = dx + halfWidth * (1 - 0.75f * et) * mDirection;
        mCurrentVertices[36] = halfHeight + Math.max(min_hh, halfHeight - ss);

        // 3
        mCurrentVertices[40] = dx + halfWidth * mDirection;
        mCurrentVertices[41] = halfHeight;
        mCurrentVertices[45] = dx + halfWidth * (1 - 0.75f * et) * mDirection;
        mCurrentVertices[46] = halfHeight + Math.max(min_hh, halfHeight - ss);
        mCurrentVertices[50] = dx + halfWidth * (2 - 1.50f * et) * mDirection;
        mCurrentVertices[51] = halfHeight + Math.max(min_hh, halfHeight - ss);
        mCurrentVertices[55] = dx + halfWidth * (2 - 0.75f * et) * mDirection;
        mCurrentVertices[56] = halfHeight;

        // 4
        mCurrentVertices[60] = dx + halfWidth * mDirection;
        mCurrentVertices[61] = halfHeight;
        mCurrentVertices[65] = dx + halfWidth * (2 - 0.75f * et) * mDirection;
        mCurrentVertices[66] = halfHeight;
        mCurrentVertices[70] = dx + halfWidth * (2 - 1.50f * et) * mDirection;
        mCurrentVertices[71] = 0 + Math.min(halfHeight - min_hh, ss);
        mCurrentVertices[75] = dx + halfWidth * (1 - 0.75f * et) * mDirection;
        mCurrentVertices[76] = 0 + Math.min(halfHeight - min_hh, ss);

        float alpha = Math.max(0, 1 - mExistsTime / mMaxExistsTime);
        float clr = Color.toFloatBits(1f, 1f, 1f, alpha);
        for (int i = 0; i < 16; i++) {
            mCurrentVertices[i * 5 + 2] = clr;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        applyTransform(batch, computeTransform());
        drawChildren(batch, parentAlpha);
        drawThisEffect(batch);
        resetTransform(batch);
    }

    public void drawThisEffect(Batch batch) {
        batch.draw(
                mAnimation.getKeyFrame(mAnimationDeltaTime + mExistsTime / mMaxExistsTime, true).getTexture(),
                mCurrentVertices,
                0, mCurrentVertices.length
        );
    }

}
