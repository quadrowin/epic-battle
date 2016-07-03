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
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.logic.tower.BattleGame;
import com.quadrolord.epicbattle.screen.cooldown.CooldownElement;

/**
 * Created by Quadrowin on 10.01.2016.
 */
public class BulletButton extends Group {

    private SkillItem mBulletSkill;

    private BattleGame mGame;
    private ImageButton mFireButton;
    private ProgressBar mProgressBar;
    private Label mCost;

    private CooldownElement mCooldownElement;

    public BulletButton(AbstractScreen screen, SkillItem skill) {
        mBulletSkill = skill;

        mGame = screen.get(BattleGame.class);

        Gdx.app.log("BulletButton create", skill.getInfo().getName() + " " + skill.getInfo().getIcon().toString());

        Texture iconTexture = new Texture(skill.getInfo().getIcon());
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

        mCost = new Label(Integer.toString(mBulletSkill.getCost()), screen.getSkin(), "default", Color.WHITE);
        mCost.setBounds(0, 0, mFireButton.getWidth(), mFireButton.getHeight());
        mCost.setAlignment(Align.bottom, Align.center);
        mCost.setFontScale(0.7f, 0.7f);
        mCost.setPosition(0, 5);
        mCost.setZIndex(2);

        mFireButton.addActor(mCost);

        Skin skin = screen.getSkin();

        mCooldownElement = new CooldownElement();
        mCooldownElement.initWhiteTexture(skin);

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

    public SkillItem getBulletSkill() {
        return mBulletSkill;
    }

    public void act(float delta) {
        if (mBulletSkill.isInCooldown()) {
            float constructionTime = mBulletSkill.getTower().getCooldownLength(mBulletSkill);
            float timeDelta = constructionTime - mBulletSkill.getTower().getCooldownRest(mBulletSkill);
            mProgressBar.setValue(timeDelta / constructionTime * 100);
        } else {
            mProgressBar.setValue(mProgressBar.getMaxValue());
        }

        Color color = mFireButton.getColor();

        if (!mGame.getPlayerTower().hasCash(mBulletSkill)) {
            mFireButton.setColor(color.r, color.b, color.g, 0.5f);
        } else {
            mFireButton.setColor(color.r, color.b, color.g, 1.0f);
        }

        mCost.setText(Integer.toString(mBulletSkill.getCost()));
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        applyTransform(batch, computeTransform());
        drawChildren(batch, parentAlpha);
        mCooldownElement.draw(batch, this, mBulletSkill);
        resetTransform(batch);
    }

}
