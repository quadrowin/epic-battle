package com.quadrolord.epicbattle.view.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.BulletState;
import com.quadrolord.epicbattle.view.SpriteAnimationDrawable;
import com.quadrolord.epicbattle.view.ball.AnimationWithContent;
import com.quadrolord.epicbattle.view.ball.AttackAnimationDrawable;
import com.quadrolord.epicbattle.view.ball.DeadAnimationDrawable;
import com.quadrolord.epicbattle.view.ball.WalkAnimationDrawable;

import java.util.Iterator;

/**
 * Created by Goorus on 25.09.2016.
 */

public abstract class AbstractBallView extends AbstractBulletView {

    private Texture mBallTexture;

    private Texture mContentTexture;

    public AbstractBallView(AbstractBullet bullet, AbstractScreen screen) {
        super(bullet, screen);
    }

    @Override
    protected void initAnimations(AbstractScreen screen) {
        mAnimations.put(
                BulletState.RUN,
                new WalkAnimationDrawable(getBallTexture(screen), getContentTexture(screen), getWidth(), getHeight())
        );

        mAnimations.put(
                BulletState.ATTACK_PREPARE,
                new AttackAnimationDrawable(getBallTexture(screen), getContentTexture(screen), this)
        );

        mAnimations.put(
                BulletState.DEATH,
                new DeadAnimationDrawable(getBallTexture(screen), getContentTexture(screen), getWidth(), getHeight())
        );
    }

    protected Texture getBallTexture(AbstractScreen screen) {
        if (null == mBallTexture) {
            mBallTexture = screen.getTextures().get(getBallTextureFile());
        }
        return mBallTexture;
    }

    protected Texture getContentTexture(AbstractScreen screen) {
        if (null == mContentTexture) {
            mContentTexture = screen.getTextures().get(getContentTextureFile());
        }
        return mContentTexture;
    }

    protected String getBallTextureFile() {
        return "balls/bubble1.png";
    }

    abstract protected String getContentTextureFile();

    protected void setContentSize(float scale) {
        for (Iterator<SpriteAnimationDrawable> it = mAnimations.values().iterator(); it.hasNext(); ) {
            SpriteAnimationDrawable anim = it.next();
            if (anim instanceof AnimationWithContent) {
                ((AnimationWithContent) anim).setContentSize(scale);
            }
        }
    }

}
