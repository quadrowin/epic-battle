package com.quadrolord.epicbattle.screen.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.logic.bullet.BulletSkill;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Created by Quadrowin on 10.01.2016.
 */
public class BulletButton extends Group {

    private BulletSkill mBulletSkill;
    private Game mGame;
    private ImageButton mFireButton;
    private ProgressBar mProgressBar;
    private Label mCost;

    private Texture mWhiteTexture;

    private float mCooldownColor = new Color(0x7f7f7f77).toFloatBits();

    public BulletButton(AbstractScreen screen, BulletSkill skill) {
        mBulletSkill = skill;
        mGame = screen.getGame();

        Gdx.app.log("BulletButton create", skill.getTitle() + " " + skill.getIcon().toString());

        Texture iconTexture = new Texture(skill.getIcon());
//        Drawable iconDrawable = new Image(iconTexture).getDrawable();
        Drawable iconDrawable = new TextureRegionDrawable(new TextureRegion(iconTexture));
        mFireButton = new ImageButton(iconDrawable);

        mFireButton.setBounds(
                0,
                0,
                40,
                40
        );

        Pixmap border = new Pixmap((int)mFireButton.getWidth() + 6, (int)mFireButton.getHeight() + 6, Pixmap.Format.RGBA8888);
        border.setColor(Color.BLUE);
        border.fill();

        Image borderImage = new Image(new Texture(border));
        borderImage.setWidth(border.getWidth());
        borderImage.setHeight(border.getHeight());
        borderImage.setPosition(-3, -3);
        borderImage.setZIndex(0);
        borderImage.toBack();

        this.addActor(borderImage);

        mFireButton.setZIndex(1);

        this.addActor(mFireButton);

        mCost = new Label(Integer.toString(skill.getCost()), screen.getSkin(), "default", Color.WHITE);
        mCost.setBounds(0, 0, mFireButton.getWidth(), mFireButton.getHeight());
        mCost.setAlignment(Align.bottom, Align.center);
        mCost.setFontScale(0.7f, 0.7f);
        mCost.setPosition(0, 5);
        mCost.setZIndex(2);

        mFireButton.addActor(mCost);

        Skin skin = screen.getSkin();

        Pixmap white = new Pixmap(1, 5, Pixmap.Format.RGBA8888);
        white.setColor(Color.WHITE);
        white.fill();
        mWhiteTexture = new Texture(white);
        skin.add("white", mWhiteTexture);

        ProgressBar.ProgressBarStyle barStyle = new ProgressBar.ProgressBarStyle(
                skin.newDrawable("white", Color.BLACK),
                skin.newDrawable("white", Color.WHITE)
        );
        barStyle.knobBefore = skin.newDrawable("white", Color.WHITE);

        mProgressBar = new ProgressBar(0, 100, 1, false, barStyle);

        mProgressBar.setPosition(5, 0);
        mProgressBar.setSize(30, 5);
        mProgressBar.setValue(mProgressBar.getMaxValue());
        mProgressBar.setZIndex(2);

        mFireButton.addActor(mProgressBar);
    }

    public Class<? extends AbstractBullet> getBulletClass() {
        return mBulletSkill.getBulletClass();
    }

    public void act(float delta) {
        if (mBulletSkill.isInCooldown()) {
            float constructionTime = mGame.getPlayerTower().getConstructionTime(mBulletSkill.getBulletClass());
            float timeDelta = constructionTime - mGame.getPlayerTower().getCooldownTime(mBulletSkill.getBulletClass());
            mProgressBar.setValue(timeDelta / constructionTime * 100);
        } else {
            mProgressBar.setValue(mProgressBar.getMaxValue());
        }

        Color color = mFireButton.getColor();

        if (!mGame.getPlayerTower().hasCash(mBulletSkill.getBulletClass())) {
            mFireButton.setColor(color.r, color.b, color.g, 0.5f);
        } else {
            mFireButton.setColor(color.r, color.b, color.g, 1.0f);
        }

        mCost.setText(Integer.toString(mBulletSkill.getCost()));
    }

    public void draw (Batch batch, float parentAlpha) {
        applyTransform(batch, computeTransform());
        drawChildren(batch, parentAlpha);
        drawCooldown(batch);
        resetTransform(batch);
    }

    private void drawCooldown(Batch batch) {
        if (!mBulletSkill.isInCooldown()) {
            return;
        }
        float constructionTime = mGame.getPlayerTower().getConstructionTime(mBulletSkill.getBulletClass());
        float timeDelta = constructionTime - mGame.getPlayerTower().getCooldownTime(mBulletSkill.getBulletClass());
        float part = timeDelta / constructionTime;

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
