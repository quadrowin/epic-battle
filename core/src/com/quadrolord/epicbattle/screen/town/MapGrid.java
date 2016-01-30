package com.quadrolord.epicbattle.screen.town;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Created by Quadrowin on 24.01.2016.
 */
public class MapGrid extends Group {

    private Texture mWhiteTexture;

    private Texture mMapTexture;

    private float mCellSideX = 60;
    private float mCellSideY = 40;

    private int mSelectedX = -1;
    private int mSelectedY = -1;

    private int mMapSizeX = 10;
    private int mMapSizeY = 10;

    private ShapeRenderer mSr = new ShapeRenderer();

    public MapGrid(final AbstractScreen screen, Stage stage) {
        Skin skin = screen.getSkin();

        Pixmap white = new Pixmap(1, 5, Pixmap.Format.RGBA8888);
        white.setColor(Color.WHITE);
        white.fill();
        mWhiteTexture = new Texture(white);
        skin.add("white", mWhiteTexture);

        mMapTexture = new Texture("town/bg1.jpg");

        setBounds(40, 40, 400, 300);
        addListener(new ClickListener() {

            public void clicked(InputEvent event, float x, float y) {
                // isometric
                // Xis, Yis - координаты центра ячейки в изометрии
                // Cx, Cy - диагонали ромба ячейки
                // x, y - координаты ячейки
                // Xis = (x - y) * Cx
                // Yis = (x + y + 0.5) * Cy
                // =>
                // x = (Yis / Cy + Xis / Cx - 0.5) / 2
                // y = (Yis / Cy - Xis / Cx - 0.5) / 2

                float cx = mCellSideX / 2;
                float cy = mCellSideY / 2;
                int col = (int) Math.round((y / cy + x / cx - 0.5 - 0.5) / 2);
                int row = (int) Math.round((y / cy - x / cx - 0.5 - 0.5) / 2);

                Gdx.app.log("MapGrid", "(" + x + ";" + y + ") -> (" + col + ";" + row + ")");
                mSelectedX = col;
                mSelectedY = row;
            }

        });
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

        mSr.setProjectionMatrix(batch.getProjectionMatrix());
        mSr.setTransformMatrix(batch.getTransformMatrix());
        mSr.begin(ShapeRenderer.ShapeType.Line);

        mSr.setColor(Color.WHITE);

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
        mSr.begin(ShapeRenderer.ShapeType.Filled);

        if (mSelectedX >= 0 || true) {
            mSr.setColor(Color.RED);

            float botX = (mSelectedX - mSelectedY) * mCellSideX / 2;
            float botY = (mSelectedX + mSelectedY) * mCellSideY / 2;
            float lineWidth = 3;

            // низ - лево
            mSr.rectLine(
                    botX, botY,
                    botX - mCellSideX / 2, botY + mCellSideY / 2,
                    lineWidth
            );
            // лево - верх
            mSr.rectLine(
                    botX - mCellSideX / 2, botY + mCellSideY / 2,
                    botX, botY + mCellSideY,
                    lineWidth
            );
            // верх - право
            mSr.rectLine(
                    botX, botY + mCellSideY,
                    botX + mCellSideX / 2, botY + mCellSideY / 2,
                    lineWidth
            );
            // право - низ
            mSr.rectLine(
                    botX + mCellSideX / 2, botY + mCellSideY / 2,
                    botX, botY,
                    lineWidth
            );
//
//            float[] points = new float[] {
//                    // нижняя точка
//                    botX,
//                    botY,
//
//                    // левая точка
//                    botX - mCellSideX / 2,
//                    botY + mCellSideY / 2,
//
//                    // верх
//                    botX,
//                    botY + mCellSideY,
//
//                    // право
//                    botX + mCellSideX / 2,
//                    botY + mCellSideY / 2,
//
//                    // вниз
//                    botX,
//                    botY,
//            };
//
//            mSr.polyline(points);
        }

        mSr.end();

        
//        mSr.setColor(Color.BLUE);
//        mSr.begin(ShapeRenderer.ShapeType.Line);
//
//        mSr.polyline(new float[] {
//                -mCellSideX / 2, -mCellSideY / 2,
//                -mCellSideX / 2, +mCellSideY / 2,
//                +mCellSideX / 2, +mCellSideY / 2,
//                +mCellSideX / 2, -mCellSideY / 2,
//                -mCellSideX / 2, -mCellSideY / 2,
//        });
//
//        mSr.end();


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
                (col - row) * mCellSideX / 2,
                (col + row + 0.5f) * mCellSideY / 2
//                (col + row) * mCellSideY / 2
        );
    }

}
