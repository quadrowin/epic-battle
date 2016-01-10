package com.quadrolord.epicbattle.screen.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Created by Quadrowin on 10.01.2016.
 */
public class Background extends Group {

    private Texture mSky;

    private Texture mGround;

    private Texture mGrass;

    private Camera mCamera;

    private float mPosition;

    private float mWidth, mHeight;

    public Background(AbstractScreen screen, Stage stage, Camera camera) {
        mCamera = camera;
        mWidth = screen.getStage().getViewport().getScreenWidth();
        mHeight = screen.getStage().getViewport().getScreenHeight();

        Gdx.app.log("", "w " + mWidth + " h " + mHeight);

        mSky = new Texture("Bg/one/sky1.png");
        mGround = new Texture("Bg/one/bg1.png");
        mGrass = new Texture("Bg/one/grass1.png");

        stage.addActor(this);
    }

    @Override
    public void act(float delta) {
        mPosition = mCamera.position.x;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(
                mSky, 0, 0, mWidth, mHeight
        );

        float posGround = mPosition / 4000;
        float deltaGround = posGround >= 0
                ? -mWidth * (posGround - (int)posGround)
                : -mWidth - mWidth * (posGround - (int)posGround);

        batch.draw(
                mGround,
                deltaGround, 0, mWidth, mHeight
        );

        batch.draw(
                mGround,
                mWidth + deltaGround, 0, mWidth, mHeight
        );

        float posGrass = mPosition / 1000;
        float deltaGrass = posGrass >= 0
                ? -mWidth * (posGrass - (int)posGrass)
                : -mWidth - mWidth * (posGrass - (int)posGrass);

        batch.draw(
                mGrass,
                deltaGrass, 0, mWidth, mHeight / 2
        );

        batch.draw(
                mGrass,
                mWidth + deltaGrass, 0, mWidth, mHeight / 2
        );

    }


}
