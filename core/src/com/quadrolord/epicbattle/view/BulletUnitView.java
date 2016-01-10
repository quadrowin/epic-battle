package com.quadrolord.epicbattle.view;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.quadrolord.epicbattle.logic.GameUnit;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.screen.AbstractScreen;
import com.quadrolord.epicbattle.screen.battle.SpriteAnimation;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class BulletUnitView extends Group {

    private AbstractBullet mBullet;

    private ImageButton mWrapper;

    protected SpriteAnimation mAttackingAnim;
    protected SpriteAnimation mRunningAnim;
    protected SpriteAnimation mDeadAnim;

    public BulletUnitView(AbstractBullet bullet, AbstractScreen screen) {
        mBullet = bullet;
        mBullet.setViewObject(this);

        setBounds(mBullet.getX(), 20, bullet.getWidth(), bullet.getWidth());
        screen.getStage().addActor(this);

        mRunningAnim = new SpriteAnimation(
                bullet,
                screen.getSkin(),
                screen.getStage(),
                "animation_ninja/ninja_run.png",
                71,
                89,
                10,
                2,
                2,
                false,
                true
        );

        mAttackingAnim = new SpriteAnimation(
                bullet,
                screen.getSkin(),
                screen.getStage(),
                "animation_ninja/ninja_attack.png",
                96,
                89,
                10,
                2,
                2,
                false,
                true
        );

        mDeadAnim = new SpriteAnimation(
                bullet,
                screen.getSkin(),
                screen.getStage(),
                "animation_ninja/ninja_dead.png",
                86,
                89,
                10,
                2,
                2,
                false,
                true
        );

        mWrapper = new ImageButton(new TextureRegionDrawable(mRunningAnim.getTexture()));
        mWrapper.setBounds(0, 0, getWidth(), getHeight());

        addActor(mWrapper);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setX(mBullet.getX());

        if (mBullet.isRunning()) {
            mWrapper.getStyle().imageUp = new TextureRegionDrawable(mRunningAnim.getTexture());
            mAttackingAnim.reset();
        }

        if (mBullet.isAttacking()) {
            mWrapper.getStyle().imageUp = new TextureRegionDrawable(mAttackingAnim.getTexture());
            mRunningAnim.reset();
        }

        if (mBullet.isDied()) {
            mWrapper.getStyle().imageUp = new TextureRegionDrawable(mDeadAnim.getTexture());
        }
    }
}
