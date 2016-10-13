package com.quadrolord.epicbattle.screen.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.quadrolord.ejge.view.AbstractScreen;

import java.util.Iterator;

/**
 * Created by Quadrowin on 10.01.2016.
 */
public class Background extends Group {

    private static final String TAG = "Background";

    private Texture mSky;

    private Texture mFarGround;

    private Texture mMiddleGround;

    private Texture mNearGround;

    private Texture mGrass;

    private Camera mCamera;

    private float mPosition;

    private float mWidth, mHeight;

    private float mScale;

    private Stage mStage;

    private ShapeRenderer mShapeRenderer;

    public Background(Stage stage, Camera camera) {
        mShapeRenderer = new ShapeRenderer();
        mStage = stage;
        mCamera = camera;
        mWidth = stage.getViewport().getScreenWidth();
        mHeight = stage.getViewport().getScreenHeight();
        mScale = stage.getRoot().getScaleY();

        Gdx.app.log("", "w " + mWidth + " h " + mHeight);

        mSky = new Texture("Bg/parallax/Sky.png");
        mFarGround = new Texture("Bg/parallax/FarGround.png");
        mMiddleGround = new Texture("Bg/parallax/MiddleGround.png");
        mNearGround = new Texture("Bg/parallax/NearGround.png");
        mGrass = new Texture("Bg/parallax/GroundSolid.png");

        stage.addActor(this);
    }

    @Override
    public void act(float delta) {
        mPosition = mCamera.position.x;
        mWidth = mStage.getViewport().getScreenWidth();
        mHeight = mStage.getViewport().getScreenHeight();
        OrthographicCamera oc = (OrthographicCamera)mStage.getCamera();
        mScale = oc.zoom;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        ArrayMap<Texture, Integer> layers = new ArrayMap<Texture, Integer>();
        float[][] offsets = {
                {mHeight, 0},
                {290, 0},
                {130, 50},
                {100, 50},
                {100, -10}
        };

        layers.put(mSky, 16);
        layers.put(mFarGround, 14);
        layers.put(mMiddleGround, 12);
        layers.put(mNearGround, 10);
        layers.put(mGrass, 1);

        Gdx.app.log(TAG, "pos " + mPosition + " scale " + mScale);

        int i = 0;
        Iterator<ObjectMap.Entry<Texture, Integer>> iter = layers.iterator();

        while (iter.hasNext()) {
            ObjectMap.Entry<Texture, Integer> next = iter.next();

            float top = offsets[i][1];
            float pos = mPosition / 10000 * next.value;
            float delta = pos >= 0
                    ? -mWidth * (pos - (int)pos)
                    : -mWidth - mWidth * (pos - (int)pos);

            batch.draw(
                    next.key,
                    delta - mWidth, top, mWidth, offsets[i][0]
            );

            batch.draw(
                    next.key,
                    delta, top, mWidth, offsets[i][0]
            );

            batch.draw(
                    next.key,
                    delta + mWidth, top, mWidth, offsets[i][0]
            );

            batch.draw(
                    next.key,
                    delta + mWidth * 2, top, mWidth, offsets[i][0]
            );

            if (i == 0) {
                batch.draw(next.key, delta - mWidth, -200, mWidth * 4, 200, 0, 1, 1, 0.99f);
            }

            i++;
        }

    }


}
