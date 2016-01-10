package com.quadrolord.epicbattle.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class BulletUnitView extends Group {

    private AbstractBullet mBullet;

    private SpriteAnimation mAnimation;

    private SpriteAnimation mRunningAnim;
    private SpriteAnimation mAttackingAnim;
    private SpriteAnimation mDeadAnim;

    public BulletUnitView(AbstractBullet bullet, AbstractScreen screen) {
        mBullet = bullet;
        mBullet.setViewObject(this);

        setBounds(mBullet.getX(), 20, bullet.getWidth(), bullet.getWidth());
        screen.getStage().addActor(this);

        mRunningAnim = new SpriteAnimation(
                screen.getSkin(),
                "animation_ninja/ninja_run.png",
                71,
                89,
                10,
                2,
                2,
                true
        );

        mAttackingAnim = new SpriteAnimation(
                screen.getSkin(),
                "animation_ninja/ninja_attack.png",
                96,
                89,
                10,
                2,
                2,
                true
        );

        mDeadAnim = new SpriteAnimation(
                screen.getSkin(),
                "animation_ninja/ninja_dead.png",
                86,
                89,
                10,
                2,
                2,
                false
        );

        mAnimation = mRunningAnim;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (mBullet.getVelocity() > 0) {
            setX(mBullet.getX());
            setScale(0.5f, 0.5f);
        } else {
            setX(mBullet.getX() + mBullet.getWidth());
            setScale(-0.5f, 0.5f);
        }

        if (mBullet.isRunning()) {
            setAnimation(mRunningAnim);
        }

        mAnimation.act(delta);
    }

    public void draw(Batch batch, float parentAlpha) {
        applyTransform(batch, computeTransform());
        mAnimation.draw(batch, 0, 0, getWidth(), getHeight());
        drawChildren(batch, parentAlpha);
        resetTransform(batch);
    }

    public void setAnimation(SpriteAnimation anim) {
        mAnimation = anim;
        anim.reset();
    }


    public void startAttackAnimation() {
        setAnimation(mAttackingAnim);
    }

    public void startDeadAnimation() {
        setAnimation(mDeadAnim);
    }

}
