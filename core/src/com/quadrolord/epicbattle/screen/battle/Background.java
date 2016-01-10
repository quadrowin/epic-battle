package com.quadrolord.epicbattle.screen.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.quadrolord.epicbattle.screen.AbstractScreen;

import java.util.Iterator;

import javax.xml.soap.Text;

/**
 * Created by Quadrowin on 10.01.2016.
 */
public class Background extends Group {

    private Texture mSky;

    private Texture mFarGround;

    private Texture mMiddleGround;

    private Texture mNearGround;

    private Texture mGrass;

    private Camera mCamera;

    private float mPosition;

    private float mWidth, mHeight;

    private float mScale;

    public Background(AbstractScreen screen, Stage stage, Camera camera) {
        mCamera = camera;
        mWidth = screen.getStage().getViewport().getScreenWidth();
        mHeight = screen.getStage().getViewport().getScreenHeight();
        mScale = screen.getStage().getRoot().getScaleY();

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
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        ArrayMap<Texture, Integer> layers = new ArrayMap<Texture, Integer>();
        float[][] offsets = {
                {mHeight, -150},
                {290, 0},
                {130, 50},
                {100, 50},
                {50, -10}
        };

        layers.put(mSky, 16);
        layers.put(mFarGround, 14);
        layers.put(mMiddleGround, 12);
        layers.put(mNearGround, 10);
        layers.put(mGrass, 1);

        int i = 0;
        Iterator<ObjectMap.Entry<Texture, Integer>> iter = layers.iterator();

        while (iter.hasNext()) {
            ObjectMap.Entry<Texture, Integer> next = iter.next();

            float top = offsets[i][1];
            float pos = mPosition / 1000 * next.value;
            float delta = pos >= 0
                    ? -mWidth * (pos - (int)pos)
                    : -mWidth - mWidth * (pos - (int)pos);

            batch.draw(
                    next.key,
                    delta, top, mWidth, offsets[i][0]
            );

            batch.draw(
                    next.key,
                    mWidth + delta, top, mWidth, offsets[i][0]
            );

            i++;
        }

    }


}
