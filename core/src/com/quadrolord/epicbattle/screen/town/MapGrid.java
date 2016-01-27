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

    private float mCellSideX = 40;
    private float mCellSideY = 20;

    private int mMapSizeX = 20;
    private int mMapSizeY = 20;

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

        float maxX = mMapSizeX * mCellSideX;
        float maxY = mMapSizeY * mCellSideY;
        float minY = 0;

        batch.draw(
                mMapTexture,
                0, 0, maxX, maxY,
                0f, 1f, 0.5f, 0f
        );

        batch.end();

        mSr.begin(ShapeRenderer.ShapeType.Line);
        mSr.setColor(Color.WHITE);
        mSr.setProjectionMatrix(batch.getProjectionMatrix());

        for (int i = 1; i < mMapSizeY; i++) {

            float deltaY = i * mCellSideY;
            float topRightY = deltaY;
            float botRightY = deltaY;
            float topX = maxX;
            float topY = deltaY + mMapSizeX * mCellSideY;
            float botX = maxX;
            float botY = deltaY - mMapSizeX * mCellSideY;
            if (topY > maxY) {
                topRightY = maxY + maxY - topY;
                topX = topX / (topY - deltaY) * (maxY - deltaY);
                topY = maxY;
            }
            if (botY < minY) {
                botRightY = minY - botY;
                botX = botX / (botY - deltaY) * (minY - deltaY);
                botY = minY;
            }

            float[] points = new float[] {
                    // точка на правой грани
                    maxX, topRightY,

                    // точка на верхней грани
                    topX, topY,

                    // левая грань
                    0, deltaY,

                    // низ
                    botX, botY,

                    // правая грань
                    maxX, botRightY
            };

            mSr.polyline(points);
        }
        mSr.line(0, 0, maxX, maxY);
        mSr.line(0, maxY, maxX, 0);

        mSr.end();

        batch.begin();
    }

    public float getCellSideX() {
        return mCellSideX;
    }

    public float getCellSideY() {
        return mCellSideY;
    }

    public void setChildPosition(Actor child, int col, int row) {
//        // orthogonal
//        child.setPosition(
//                col * mCellSide,
//                row * mCellSide
//        );
        // isometric
        // Xis, Yis - координаты центра ячейки в изометрии
        // Cx, Cy - диагонали ромба ячейки
        // x, y - координаты ячейки
        // Xis = (x - y) * Cx
        // Yis = (x + y + 0.5) * Cy
        child.setPosition(
                (col - row) * mCellSideX,
                (col + row + 0.5f) * mCellSideY
        );
    }

}
