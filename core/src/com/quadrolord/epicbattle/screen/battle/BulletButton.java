package com.quadrolord.epicbattle.screen.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.logic.bullet.BulletInfo;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Created by Quadrowin on 10.01.2016.
 */
public class BulletButton extends Group {

    private Class<? extends AbstractBullet> mBulletClass;
    private Game mGame;
    private ImageButton mFireButton;
    private ProgressBar mProgressBar;
    private Label mCost;

    public BulletButton(AbstractScreen screen, Class<? extends AbstractBullet> bulletClass) {
        mBulletClass = bulletClass;
        mGame = screen.getGame();

        BulletInfo bi = screen.getGame().getPlayerTower().getBulletInfo(bulletClass);

        mFireButton = new ImageButton(new Image(bi.getIcon()).getDrawable());

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

        mCost = new Label(Integer.toString(bi.getCost()), screen.getSkin(), "default", Color.WHITE);
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
        skin.add("white", new Texture(white));

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
        return mBulletClass;
    }

    public void act(float delta) {
        boolean isInCooldown = mGame.getPlayerTower().isInCooldown(mBulletClass);

        if (isInCooldown) {
            float constructionTime = mGame.getPlayerTower().getConstructionTime(mBulletClass);
            float timeDelta = constructionTime - mGame.getPlayerTower().getCooldownTime(mBulletClass);

            mProgressBar.setValue(timeDelta / constructionTime * 100);

            if (mCost.isVisible()) {
                mCost.setVisible(false);
            }
        } else {
            mProgressBar.setValue(mProgressBar.getMaxValue());

            if (!mCost.isVisible()) {
                mCost.setVisible(true);
            }
        }

        Color color = mFireButton.getColor();

        if (isInCooldown || !mGame.getPlayerTower().hasCash(mBulletClass)) {
            mFireButton.setColor(color.r, color.b, color.g, 0.5f);
        } else {
            mFireButton.setColor(color.r, color.b, color.g, 1.0f);
        }
    }
}