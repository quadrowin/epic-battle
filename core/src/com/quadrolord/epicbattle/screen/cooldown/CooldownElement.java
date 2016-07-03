package com.quadrolord.epicbattle.screen.cooldown;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.quadrolord.epicbattle.logic.skill.SkillItem;

/**
 * Created by Quadrowin on 03.07.2016.
 */
public class CooldownElement {

    private float mCooldownColor = new Color(0x7f7f7f77).toFloatBits();

    private Texture mWhiteTexture;

    public void initWhiteTexture(Skin skin) {
        Pixmap white = new Pixmap(1, 5, Pixmap.Format.RGBA8888);
        white.setColor(Color.WHITE);
        white.fill();
        mWhiteTexture = new Texture(white);
        skin.add("white", mWhiteTexture);
    }

    public void setWhiteTexture(Texture texture) {
        mWhiteTexture = texture;
    }

    public void draw(Batch batch, Actor actor, SkillItem skill) {
        if (!skill.isInCooldown()) {
            return;
        }
        float constructionTime = skill.getTower().getCooldownLength(skill);
        float timeDelta = constructionTime - skill.getTower().getCooldownRest(skill);
        float part = timeDelta / constructionTime;

        float cdColor = mCooldownColor;
        float w = actor.getWidth();
        float h = actor.getHeight();

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
            batch.draw(mWhiteTexture, leftPart, 0, leftPart.length);
            batch.draw(mWhiteTexture, rightPart, 0, rightPart.length);
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
            batch.draw(mWhiteTexture, leftPart, 0, leftPart.length);
        }
    }

}
