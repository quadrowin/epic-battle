package com.quadrolord.epicbattle.screen.town;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Created by Quadrowin on 24.01.2016.
 */
public class MapGrid extends Group {

    private Texture mWhiteTexture;

    private Texture mMapTexture;

    private float mCellSide = 20;

    private ShapeRenderer mSr = new ShapeRenderer();

    public MapGrid(final AbstractScreen screen, Stage stage) {
        Skin skin = screen.getSkin();

        Pixmap white = new Pixmap(1, 5, Pixmap.Format.RGBA8888);
        white.setColor(Color.WHITE);
        white.fill();
        mWhiteTexture = new Texture(white);
        skin.add("white", mWhiteTexture);

        mMapTexture = new Texture("town/bg1.jpg");

        setBounds(0, 0, 400, 300);
        stage.addActor(this);
    }

    public void draw (Batch batch, float parentAlpha) {
        applyTransform(batch, computeTransform());
        drawGrid(batch);
        drawChildren(batch, parentAlpha);
        resetTransform(batch);
    }

    private void drawGrid(Batch batch) {

        float side = mCellSide;

        batch.draw(mMapTexture, 0, 0, 400, 400, 0f, 1f, 0.5f, 0f);

        int maxX = 20;
        int maxY = 20;

        batch.end();

        mSr.begin(ShapeRenderer.ShapeType.Line);
        mSr.setColor(Color.WHITE);
        mSr.setProjectionMatrix(batch.getProjectionMatrix());

        for (int i = -maxX / 3 - 1; i < maxX * 1.3 + 1; i++) {


            float[] points = new float[] {
                    // вправо вверх
                    maxX * side, i * side + maxY * side / 3,

                    0, i * side,

                    // вправо вниз
                    maxX * side, i * side - maxY * side / 3,
            };

            mSr.polyline(points);
        }

        mSr.end();

        batch.begin();
    }

    public float getCellSide() {
        return mCellSide;
    }

    public void setChildPosition(Actor child, int col, int row) {
        child.setPosition(col * mCellSide, row * mCellSide);
    }

}
