package com.quadrolord.epicbattle.view;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class BulletUnitView extends Group {

    private AbstractBullet mBullet;

    private SpriteAnimationActor mAnimation;

    private SpriteAnimationDrawable mRunningAnim;
    private SpriteAnimationDrawable mAttackingAnim;
    private SpriteAnimationDrawable mDeadAnim;

    public BulletUnitView(AbstractBullet bullet, AbstractScreen screen) {
        mBullet = bullet;
        mBullet.setViewObject(this);

        setBounds(mBullet.getX(), 20, bullet.getWidth(), bullet.getWidth());
        screen.getStage().addActor(this);

        mRunningAnim = screen.getSpriteAnimationLoader().createDrawable(
                screen.getSkin(),
                "animation_ninja/ninja_run.png",
                71,
                89,
                10,
                0.1f,
                2,
                2,
                true
        );

        mAttackingAnim = screen.getSpriteAnimationLoader().createDrawable(
                screen.getSkin(),
                "animation_ninja/ninja_attack.png",
                96,
                89,
                10,
                0.1f,
                2,
                2,
                true
        );

        mDeadAnim = screen.getSpriteAnimationLoader().createDrawable(
                screen.getSkin(),
                "animation_ninja/ninja_dead.png",
                86,
                89,
                10,
                0.1f,
                2,
                2,
                false
        );

        mAnimation = new SpriteAnimationActor();
        mAnimation.setAnimationLooped(mRunningAnim);
        addActor(mAnimation);
    }

    @Override
    public void act(float delta) {
        if (mBullet.getVelocity() > 0) {
            setX(mBullet.getX());
            setScale(0.5f, 0.5f);
        } else {
            setX(mBullet.getX() + mBullet.getWidth());
            setScale(-0.5f, 0.5f);
        }

        if (mBullet.isRunning()) {
            if (mAnimation.getAnimation() != mRunningAnim) {
                mAnimation.setAnimationLooped(mRunningAnim);
            }
        }

        super.act(delta);
    }

    public void startAttackAnimation() {
        mAnimation.setAnimationLooped(mAttackingAnim);
    }

    public void startDeadAnimation() {
        final Actor self = this;
        mAnimation.setAnimationCallback(
                mDeadAnim,
                new Runnable() {
                    @Override
                    public void run() {
                        self.remove();
                    }
                }
        );
    }

}
