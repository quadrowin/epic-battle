package com.quadrolord.epicbattle.view.ball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.quadrolord.epicbattle.RM;
import com.quadrolord.epicbattle.logic.tower.GameUnit;
import com.quadrolord.epicbattle.logic.tower.UnitTime;
import com.quadrolord.epicbattle.view.SpriteAnimationDrawable;
import com.quadrolord.epicbattle.view.bullet.AbstractBulletView;

/**
 * Created by Quadrowin on 10.07.2016.
 */
public class WalkBallAnimation extends AbstractBallAnimation {

    private static final String TAG = "WalkBallAnimation";

    private Texture mBallTexture;
    private Texture mContentTexture;
    private Texture mEnergyTexture;

    public WalkBallAnimation(Texture ballTexture, Texture contentTexture, AbstractBulletView actor) {
        super(actor);

        mEnergyTexture = RM.getTextures().get("balls/content/energy.png");
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

//        Gdx.app.log(TAG, "time " + time.existsTime);

        // крутящаяся энергия
        batch.setColor(1, 1, 1, 0.5f);
        batch.draw(
                mEnergyTexture,
                -halfWidth, 0,
                halfWidth, halfHeight,      // originX, originY (центр колеса)
                width, height,              // width, height
                1f, 1f,                     // scaleX, scaleY
                -direction * time.existsTime * 110,      // rotation
                (int)(1f - direction) / 2, 0,                       // srcX, srcY
                mEnergyTexture.getWidth(),  // srcWidth
                mEnergyTexture.getHeight(), // srcHeight
                direction < 0, false        // flipX, flipY
        );
        batch.setColor(1, 1, 1, 1f);

        // содержимое
        batch.draw(
                mContentTexture,
                -halfWidth + (width * (1 - contentSize)) / 2,  // x
                (height * (1 - contentSize)) / 2, // y
                halfWidth, halfHeight,      // originX, originY (центр колеса)
                width * contentSize,        // width
                height * contentSize,       // height
                1f, 1f,                     // scaleX, scaleY
                (float)Math.sin(time.existsTime * 3) * 10,       // rotation - покачивание
                0, 0,                       // srcX, srcY
                mContentTexture.getWidth(), // srcWidth
                mContentTexture.getHeight(),// srcHeight
                false, false                // flipX, flipY
        );

        // оболочка шара
        batch.draw(
                mBallTexture,
                -halfWidth, 0,
                halfWidth, halfHeight,      // originX, originY (центр колеса)
                width, height,              // width, height
                1f, 1f,                     // scaleX, scaleY
                0,                          // rotation
                0, 0,                       // srcX, srcY
                mBallTexture.getWidth(),    // srcWidth
                mBallTexture.getHeight(),   // srcHeight
                false, false                // flipX, flipY
        );

    }

}
