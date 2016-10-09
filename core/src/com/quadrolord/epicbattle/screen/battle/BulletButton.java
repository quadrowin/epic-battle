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
import com.quadrolord.epicbattle.RM;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.logic.tower.BattleGame;
import com.quadrolord.epicbattle.screen.SES;
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

        setSize(40 * SES.F, 40 * SES.F);

        Texture bgTexture = RM.getTextures().get("ui/spell-page-btn.png");
        Drawable bgDrawable = new TextureRegionDrawable(new TextureRegion(bgTexture));
        mFireButton = new ImageButton(bgDrawable);
        mFireButton.setBounds(0, 0, getWidth(), getHeight());
        mFireButton.setZIndex(0);
        addActor(mFireButton);

        Texture iconTexture;
        if (mBulletSkill != null) {
            iconTexture = RM.getTextures().get(mBulletSkill.getInfo().getIcon());
//        Drawable iconDrawable = new Image(iconTexture).getDrawable();
        } else {
            iconTexture = RM.getTextures().get("balls/bubble1.png");
        }
//        Drawable iconDrawable = new TextureRegionDrawable(new TextureRegion(iconTexture));
        Image skillImage = new Image(iconTexture);//new Texture(border));
        if (mBulletSkill != null) {
            skillImage.setBounds(5 * SES.F, 5 * SES.F, getWidth() - 10 * SES.F, getHeight() - 10 * SES.F);
        } else {
            skillImage.setBounds(10 * SES.F, 10 * SES.F, getWidth() - 20 * SES.F, getHeight() - 20 * SES.F);
        }
        skillImage.setZIndex(1);
        addActor(skillImage);


        String costText = mBulletSkill != null ?  Integer.toString(mBulletSkill.getCost()) : "";
        mCost = new Label(costText, screen.getSkin(), "default", Color.WHITE);
        mCost.setBounds(0, 0, mFireButton.getWidth(), mFireButton.getHeight());
        mCost.setAlignment(Align.bottom, Align.center);
        mCost.setFontScale(0.7f * SES.F, 0.7f * SES.F);
        mCost.setPosition(0, 5 * SES.F);
        mCost.setZIndex(2);

        addActor(mCost);

        Skin skin = screen.getSkin();

        mCooldownElement = new CooldownElement();
        mCooldownElement.initWhiteTexture(skin);

        ProgressBar.ProgressBarStyle barStyle = new ProgressBar.ProgressBarStyle(
                skin.newDrawable("white", Color.BLACK),
                skin.newDrawable("white", Color.WHITE)
        );
        barStyle.knobBefore = skin.newDrawable("white", Color.WHITE);

        mProgressBar = new ProgressBar(0, 100, 1, false, barStyle);
        mProgressBar.setBounds(5 * SES.F, 5 * SES.F, mFireButton.getWidth() - 10 * SES.F, 5 * SES.F);
        mProgressBar.setValue(mProgressBar.getMaxValue());
        mProgressBar.setZIndex(2);
        mProgressBar.setVisible(mBulletSkill != null);

        mFireButton.addActor(mProgressBar);
    }

    public SkillItem getBulletSkill() {
        return mBulletSkill;
    }

    public void act(float delta) {
        if (mBulletSkill == null) {
            // нет скила
            return;
        }

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
        if (mBulletSkill != null) {
            mCooldownElement.draw(batch, this, mBulletSkill);
        }
        resetTransform(batch);
    }

}
