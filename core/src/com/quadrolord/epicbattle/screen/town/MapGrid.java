package com.quadrolord.epicbattle.screen.town;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.epicbattle.logic.town.MyTown;
import com.quadrolord.ejge.view.AbstractScreen;

/**
 * Created by Quadrowin on 24.01.2016.
 */
public class MapGrid extends Group {

    private boolean mDebugGrid = true;

    private Texture mWhiteTexture;

    private Texture mMapTexture;

    private float mCellSideX = MyTown.MAP_CELL_WIDTH;
    private float mCellSideY = MyTown.MAP_CELL_HEIGHT;

    private int mSelectedX = -1;
    private int mSelectedY = -1;

    private MyTown mTown;

    private int mMapSizeX = MyTown.MAP_SIZE_X;
    private int mMapSizeY = MyTown.MAP_SIZE_Y;

    private ShapeRenderer mSr = new ShapeRenderer();

    public MapGrid(final AbstractScreen screen, MyTown town, Stage stage) {
        Skin skin = screen.getSkin();
        mTown = town;

        Pixmap white = new Pixmap(1, 5, Pixmap.Format.RGBA8888);
        white.setColor(Color.WHITE);
        white.fill();
        mWhiteTexture = new Texture(white);
        skin.add("white", mWhiteTexture);

        mMapTexture = new Texture("town/bg1.jpg");

        setBounds(-200, 0, 400, 300);
        final float zeroX = getCellXY(0, 0).x;
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
                x -= zeroX;
                int col = (int) Math.round((y / cy + x / cx - 0.5 - 0.5) / 2);
                int row = (int) Math.round((y / cy - x / cx - 0.5 - 0.5) / 2);

                Gdx.app.log("MapGrid", "(" + x + ";" + y + ") -> (" + col + ";" + row + ")");
                mSelectedX = col;
                mSelectedY = row;

                mTown.setSelected(col, row);
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

        Vector2 zero = getCellXY(0, 0);
        float maxX = mMapSizeX * mCellSideX;
        float maxY = mMapSizeY * mCellSideY;
        float halfCellX = mCellSideX / 2;
        float halfCellY = mCellSideY / 2;

//        batch.draw(
//                mMapTexture,
//                0, 0, maxX, maxY,
//                0f, 1f, 0.5f, 0f
//        );

        float clr = Color.WHITE.toFloatBits();
        // x, y, color, u, v
        float[] mapPoints = new float[] {
                zero.x, 0,
                clr, 0.5f, 0,

                zero.x + maxX / 2, maxY / 2,
                clr, 1, 0.5f,

                zero.x, maxY,
                clr, 0.5f, 1,

                zero.x - maxX / 2, maxY / 2,
                clr, 0, 0.5f
        };

        batch.draw(
                mMapTexture,
                mapPoints,
                0, mapPoints.length
        );

        batch.end();

        mSr.setProjectionMatrix(batch.getProjectionMatrix());
        mSr.setTransformMatrix(batch.getTransformMatrix());
        mSr.begin(ShapeRenderer.ShapeType.Line);

        mSr.setColor(Color.WHITE);

        for (int i = 0; i < mMapSizeY; i++) {

            float botLeftX = zero.x - halfCellX * i;
            float botLeftY = zero.y + halfCellY * i;
            float topRightX = zero.x + halfCellX * (mMapSizeX - i);
            float topRightY = zero.y + halfCellY * (mMapSizeY + i);

            mSr.line(botLeftX, botLeftY, topRightX, topRightY);

            float botRightX = zero.x + halfCellX * i;
            float botRightY = zero.y + botLeftY;
            float topLeftX = zero.x + halfCellX * (i - mMapSizeX);
            float topLeftY = zero.y + topRightY;

            mSr.line(botRightX, botRightY, topLeftX, topLeftY);
        }

        mSr.end();

        if (mSelectedX >= 0 && false) {
            mSr.begin(ShapeRenderer.ShapeType.Filled);
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            mSr.setColor(Color.YELLOW.r, Color.YELLOW.g, Color.YELLOW.b, 0.5f);

            float botX = zero.x + (mSelectedX - mSelectedY) * mCellSideX / 2;
            float botY = zero.y + (mSelectedX + mSelectedY) * mCellSideY / 2;
//            float lineWidth = 3;
//
//            // низ - лево
//            mSr.rectLine(
//                    botX, botY,
//                    botX - mCellSideX / 2, botY + mCellSideY / 2,
//                    lineWidth
//            );
//            // лево - верх
//            mSr.rectLine(
//                    botX - mCellSideX / 2, botY + mCellSideY / 2,
//                    botX, botY + mCellSideY,
//                    lineWidth
//            );
//            // верх - право
//            mSr.rectLine(
//                    botX, botY + mCellSideY,
//                    botX + mCellSideX / 2, botY + mCellSideY / 2,
//                    lineWidth
//            );
//            // право - низ
//            mSr.rectLine(
//                    botX + mCellSideX / 2, botY + mCellSideY / 2,
//                    botX, botY,
//                    lineWidth
//            );
//

            float borderSize = 3;

            float[] points = new float[] {
                    // нижняя точка
                    botX,
                    botY,

                    // левая точка
                    botX - mCellSideX / 2,
                    botY + mCellSideY / 2,

                    // верх
                    botX,
                    botY + mCellSideY,

                    // право
                    botX + mCellSideX / 2,
                    botY + mCellSideY / 2,

                    // вниз
                    botX,
                    botY - borderSize,
            };

            mSr.triangle(
                    points[0], points[1],
                    points[2], points[3],
                    points[4], points[5]
            );
            mSr.triangle(
                    points[4], points[5],
                    points[6], points[7],
                    points[0], points[1]
            );

            mSr.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
        }

        if (mDebugGrid) {
            mSr.setColor(Color.BLUE);
            mSr.begin(ShapeRenderer.ShapeType.Line);

            float leftX = zero.x - mMapSizeX * mCellSideX / 2;

            mSr.polyline(new float[] {
                    leftX, zero.y,
                    leftX, zero.y + mMapSizeY * mCellSideY,
                    leftX + mMapSizeX * mCellSideX, zero.y + mMapSizeY * mCellSideY,
                    leftX + mMapSizeX * mCellSideX, zero.y,
                    leftX, zero.y,
            });
            for (int col = 1; col < mMapSizeX; col++) {
                mSr.line(
                        leftX + col * mCellSideX, zero.y,
                        leftX + col * mCellSideX, zero.y + mMapSizeY * mCellSideY
                );
            }
            for (int row = 1; row < mMapSizeY; row++) {
                mSr.line(
                        leftX, zero.y + row * mCellSideY,
                        leftX + mMapSizeX * mCellSideX, zero.y + row * mCellSideY
                );
            }

            mSr.end();
        }

        batch.begin();
    }

    public float getCellSideX() {
        return mCellSideX;
    }

    public float getCellSideY() {
        return mCellSideY;
    }

    public void setDebugGrid(boolean debug) {
        mDebugGrid = debug;
    }

    public void setChildPosition(Actor child, int col, int row) {
        Vector2 v = getCellXY(col, row);
        child.setPosition(v.x - mCellSideX / 2, v.y);
    }

    /**
     * Возвращает координату нижнего угла ромба клетки
     * @param col
     * @param row
     * @return
     */
    public Vector2 getCellXY(int col, int row) {
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
        return new Vector2(
                (mMapSizeX + col - row - 1) * mCellSideX / 2,
                (col + row - 1) * mCellSideY / 2 + 0.5f * mCellSideY
//                (col + row) * mCellSideY / 2
        );
    }

}
