package com.quadrolord.epicbattle.view.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.ArrayMap;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.BulletState;
import com.quadrolord.epicbattle.view.SpriteAnimationDrawable;
import com.quadrolord.epicbattle.view.ball.AbstractBallAnimation;
import com.quadrolord.epicbattle.view.ball.AnimationWithContent;
import com.quadrolord.epicbattle.view.ball.AttackBallAnimation;
import com.quadrolord.epicbattle.view.ball.DeadBallAnimation;
import com.quadrolord.epicbattle.view.ball.WalkBallAnimation;

import java.util.Iterator;

/**
 * Created by Goorus on 25.09.2016.
 */

public abstract class AbstractBallView extends AbstractBulletView {

    AbstractBallAnimation mAnimation;

    private ArrayMap<BulletState, AbstractBallAnimation> mAnimations;

    private Texture mBallTexture;

    private Texture mContentTexture;

    private BulletState mLastState = BulletState.IDLE;

    public AbstractBallView(AbstractBullet bullet, AbstractScreen screen) {
        super(bullet, screen);
        mAnimation = getAnimation(BulletState.RUN);
    }

    @Override
    public void act(float delta) {
        AbstractBullet bullet = getBullet();
        BulletState bulletState = bullet.getState();

        // Переключение анимации
        if (bulletState != mLastState) {
            Gdx.app.log("st", "st " + bulletState.name());
            switch (bulletState) {
                case ATTACK_PREPARE:
                    switchAnimation(BulletState.ATTACK_PREPARE);
                    break;
                case ATTACK_FINISH:
                    switchAnimation(BulletState.IDLE);
                    break;
                case DEATH:
                    switchAnimation(BulletState.DEATH);
                    break;
                default:
                    switchAnimation(BulletState.RUN);
            }
            mLastState = bulletState;
        }

        setPosition(bullet.getX(), bullet.getY());
        switch (bulletState) {
            case FOLD_BACK:
                float dy = (float)Math.sin(3.14f * bullet.getTime().statePart);
                setY(bullet.getY() + dy * 10);
                break;
            case ATTACK_PREPARE:
                setY(bullet.getY());
                break;
            case DEATH:
                setY(bullet.getY());
                break;
            default:
                setY(bullet.getY());
        }

        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        applyTransform(batch, computeTransform());
        drawChildren(batch, parentAlpha);
        drawAnimation(batch);
        resetTransform(batch);
    }

    public void drawAnimation(Batch batch) {
        AbstractBullet bullet = getBullet();
        mAnimation.draw(batch, bullet.getWidth(), bullet.getHeight());
    }

    @Override
    protected void initAnimations(AbstractScreen screen) {
        mAnimations = new ArrayMap<BulletState, AbstractBallAnimation>();

        mAnimations.put(
                BulletState.RUN,
                new WalkBallAnimation(getBallTexture(screen), getContentTexture(screen), this)
        );

        mAnimations.put(
                BulletState.ATTACK_PREPARE,
                new AttackBallAnimation(getBallTexture(screen), getContentTexture(screen), this)
        );

        mAnimations.put(
                BulletState.DEATH,
                new DeadBallAnimation(getBallTexture(screen), getContentTexture(screen), this)
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
        return "balls/bubble2.png";
    }

    abstract protected String getContentTextureFile();

    protected void setContentSize(float scale) {
        for (Iterator<AbstractBallAnimation> it = mAnimations.values().iterator(); it.hasNext(); ) {
            AbstractBallAnimation anim = it.next();
            anim.setContentSize(scale);
        }
    }

    public AbstractBallAnimation getAnimation(BulletState state) {
        AbstractBallAnimation anim = mAnimations.get(state);
        if (anim == null) {
            Gdx.app.log("buv", "st " + state.name());
            anim = mAnimations.firstValue();
        }
        return anim;
    }

    private void switchAnimation(BulletState state) {
        mAnimation = getAnimation(state);
    }

}
