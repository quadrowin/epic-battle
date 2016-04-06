package com.quadrolord.epicbattle.screen.town.item;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Created by Quadrowin on 06.04.2016.
 */
public class ProgressSquare extends Group {

    private float mCooldownColor = new Color(0x7f7f7f77).toFloatBits();

    private float mProgress = 0;

    private Texture mTexture;

    public ProgressSquare(Texture texture) {
        mTexture = texture;
    }

    public float getProgress() {
        return mProgress;
    }

    public void setProgress(float progress) {
        mProgress = progress;
    }

    public void setTexture(Texture texture) {
        mTexture = texture;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        applyTransform(batch, computeTransform());
        drawProgress(batch, parentAlpha);
        drawChildren(batch, parentAlpha);
        resetTransform(batch);
    }

    private void drawProgress(Batch batch, float parentAlpha) {
        if (mProgress >= 1) {
            return;
        }

        float part = mProgress;

        float cdColor = mCooldownColor;
        float w = getWidth();
        float h = getHeight();

        float[] leftPart;
        if (part < 0.5f) {
            leftPart = new float[] {
                    0, 0,
                    cdColor, 0, 0,

                    w / 2, 0,
                    cdColor, 0, 0,

                    w / 2, h,
                    cdColor, 0, 0,

                    0, h,
                    cdColor, 0, 0,
            };
            float[] rightPart;
            if (part < 0.25f) {
                rightPart = new float[] {
                        // правый верхний
                        w / 2, h / 2,
                        cdColor, 0, 0,

                        part < 0.125f ? w / 2 + w / 2 * part * 8 : w,
                        part < 0.125f ? h : h - h / 2 * (part - 0.125f) * 8,
                        cdColor, 0, 0,

                        w,
                        part < 0.125f ? h : h - h / 2 * (part - 0.125f) * 8,
                        cdColor, 0, 0,

                        w, h / 2,
                        cdColor, 0, 0,

                        // правый нижний
                        w, h / 2,
                        cdColor, 0, 0,

                        w, 0,
                        cdColor, 0, 0,

                        w / 2, 0,
                        cdColor, 0, 0,

                        w / 2, h / 2,
                        cdColor, 0, 0,
                };
            } else {
                rightPart = new float[] {
                        part < 0.375f ? w : w - w / 2 * (part - 0.375f) * 8,
                        part < 0.375f ? h / 2 - h / 2 * (part - 0.25f) * 8 : 0,
                        cdColor, 0, 0,

                        part < 0.375f ? w : w - w / 2 * (part - 0.375f) * 8,
                        0,
                        cdColor, 0, 0,

                        w / 2, 0,
                        cdColor, 0, 0,

                        w / 2, h / 2,
                        cdColor, 0, 0,
                };
            }
            batch.draw(mTexture, leftPart, 0, leftPart.length);
            batch.draw(mTexture, rightPart, 0, rightPart.length);
        } else {
            if (part < 0.75f) {
                leftPart = new float[] {
                        // левый верхний
                        0, h / 2,
                        cdColor, 0, 0,

                        0, h,
                        cdColor, 0, 0,

                        w / 2, h,
                        cdColor, 0, 0,

                        w / 2, h / 2,
                        cdColor, 0, 0,

                        // левый нижний
                        w / 2, h / 2,
                        cdColor, 0, 0,

                        part < 0.625f ? w / 2 - w / 2 * (part - 0.5f) * 8 : 0,
                        part < 0.625f ? 0 : h / 2 * (part - 0.625f) * 8,
                        cdColor, 0, 0,

                        0,
                        part < 0.625f ? 0 : h / 2 * (part - 0.625f) * 8,
                        cdColor, 0, 0,

                        0, h / 2,
                        cdColor, 0, 0,
                };
            } else {
                // левый верхний
                leftPart = new float[] {
                        w / 2, h / 2,
                        cdColor, 0, 0,

                        part < 0.875f ? 0 : w / 2 * (part - 0.875f) * 8,
                        part < 0.875f ? h / 2 + h / 2 * (part - 0.75f) * 8 : h,
                        cdColor, 0, 0,

                        part < 0.875f ? 0 : w / 2 * (part - 0.875f) * 8,
                        h,
                        cdColor, 0, 0,

                        w / 2, h,
                        cdColor, 0, 0,
                };
            }
            batch.draw(mTexture, leftPart, 0, leftPart.length);
        }
    }

}
