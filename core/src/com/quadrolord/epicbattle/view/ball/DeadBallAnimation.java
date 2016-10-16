package com.quadrolord.epicbattle.view.ball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.quadrolord.epicbattle.RM;
import com.quadrolord.epicbattle.logic.tower.UnitTime;
import com.quadrolord.epicbattle.view.SpriteAnimationDrawable;
import com.quadrolord.epicbattle.view.bullet.AbstractBallView;
import com.quadrolord.epicbattle.view.bullet.AbstractBulletView;

/**
 * Created by Quadrowin on 11.07.2016.
 */
public class DeadBallAnimation extends AbstractBallAnimation {

    private static final String TAG = "DeadBallAnimation";

    private Texture mBallTexture;
    private Texture mContentTexture;

    public DeadBallAnimation(Texture ballTexture, Texture contentTexture, AbstractBulletView actor) {
        super(actor);

        mBallTexture = ballTexture;
        mContentTexture = contentTexture;
    }

    @Override
    public void draw(Batch batch, float width, float height) {
        UnitTime time = getActor().getBullet().getTime();
        float contentSize = getContentSize();
        float direction = getActor().getBullet().getDirection();

        float halfWidth = width / 2;
        float halfHeight = height / 2;
        float scale = 1 - 0.5f * time.statePart;
        if (scale <= 0) {
            return;
        }

        float jump_y = height * (float)Math.abs(Math.sin(time.stateTime * 3));

        // содержимое
        batch.draw(
                mContentTexture,
                -halfWidth + (width * (1 - contentSize)) / 2 - direction * time.stateTime * 30,  // x
                (height * (1 - contentSize)) / 2 + jump_y, // y
                halfWidth, halfHeight,      // originX, originY (центр колеса)
                width * contentSize,  // width
                height * contentSize, // height
                scale, scale,                     // scaleX, scaleY
                (float)Math.sin(time.existsTime) * 10,       // rotation - покачивание
                0, 0,                       // srcX, srcY
                mContentTexture.getWidth(), // srcWidth
                mContentTexture.getHeight(),// srcHeight
                false, false                // flipX, flipY
        );

        // оболочка
        batch.draw(
                mBallTexture,
                -halfWidth -direction * time.stateTime * 30,           // x
                jump_y,                                     // y
                halfWidth, halfHeight,      // originX, originY (центр колеса)
                width, height,    // width, height
                scale, scale,               // scaleX, scaleY
                time.existsTime * 10,
                0, 0,                   // srcX, srcY
                mBallTexture.getWidth(),    // srcWidth
                mBallTexture.getHeight(),   // srcHeight
                false, false    // flipX, flipY
        );
    }

}
