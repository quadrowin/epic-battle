package com.quadrolord.epicbattle.screen.town;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.quadrolord.epicbattle.logic.town.MyTown;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.logic.town.tile.Tile;
import com.quadrolord.epicbattle.screen.AbstractScreen;
import com.quadrolord.epicbattle.view.town.building.AbstractBuildingView;

/**
 * Контрол для перемещения здания или выбора места для новой постройки
 * Created by Quadrowin on 23.02.2016.
 */
public class PlacingControl extends Group {

    private AbstractBuildingItem mBuilding;

    private AbstractBuildingView mBuildingView;

    // текущая позиция здания
    private int mBuildingPreviewX;
    private int mBuildingPreviewY;

    // начальная позиция здания
    private int mBuildingStartX;
    private int mBuildingStartY;


    private float mCellSideX = MyTown.MAP_CELL_WIDTH;
    private float mCellSideY = MyTown.MAP_CELL_HEIGHT;

    private float mDeltaX;

    private float mDeltaY;

    private ShapeRenderer mSr = new ShapeRenderer();

    private Stage mStage;

    private Vector2 mOffset = new Vector2(0, 0);

    private Vector2 mTempScaled;

    public PlacingControl(AbstractScreen screen, AbstractBuildingItem building, AbstractBuildingView view) {
        mBuilding = building;
        mBuildingView = view;
        mBuildingStartX = mBuilding.getX();
        mBuildingStartY = mBuilding.getY();

        // place building view after PlacingControl.
        mStage = screen.getStage();
        view.getParent().addActorBefore(view, this);
    }

    @Override
    public void act(float delta) {
        if (Gdx.input.isTouched()) {
            mDeltaX += Gdx.input.getDeltaX();
            mDeltaY -= Gdx.input.getDeltaY();

            Viewport vp = mStage.getViewport();
            float worldDeltaX = mDeltaX * vp.getWorldWidth() / vp.getScreenWidth() * 0.5f;
            float worldDeltaY = mDeltaY * vp.getWorldHeight() / vp.getScreenHeight() * 0.5f;

            mBuildingPreviewX = mBuildingStartX + (int)((worldDeltaY / mCellSideY + worldDeltaX / mCellSideX));
            mBuildingPreviewY = mBuildingStartY + (int)((worldDeltaY / mCellSideY - worldDeltaX / mCellSideX));
            mBuilding.setPosition(mBuildingPreviewX, mBuildingPreviewY);
//            mWrapper.setX(Math.max(
//                    mBuilding.getWidth() - mWrapper.getWidth(),
//                    Math.min(
//                            0,
//                            mWrapper.getX() + Gdx.input.getDeltaX()
//                    )
//            ));
        }
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        applyTransform(batch, computeTransform());
        drawMovingBuilding(batch);
        drawChildren(batch, parentAlpha);
        resetTransform(batch);
    }

    private void drawMovingBuilding(Batch batch) {
        batch.end();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        mSr.setProjectionMatrix(batch.getProjectionMatrix());
        mSr.setTransformMatrix(batch.getTransformMatrix());
        mSr.begin(ShapeRenderer.ShapeType.Filled);

        mOffset.x = 0;
        mOffset.y = 0;
        //mBuilding.getView().getParent().localToStageCoordinates(mOffset);

        float lineWidth = 3;

        int sizeX = (int)mBuilding.getSize().x;
        int sizeY = (int)mBuilding.getSize().y;

        float points[] = new float[8];

        for (int cx = 0; cx < sizeX; cx++) {
            for (int cy = 0; cy < sizeY; cy++) {
                int selectedX = mBuilding.getX() + cx;
                int selectedY = mBuilding.getY() + cy;

                if (mBuilding.getTown().canBuildItem(mBuilding, selectedX, selectedY)) {
                    mSr.setColor(Color.WHITE.r, Color.WHITE.g, Color.WHITE.b, 0.5f);
                } else {
                    mSr.setColor(Color.RED.r, Color.RED.g, Color.RED.b, 0.5f);
                }

                float botX = mOffset.x + (selectedX - selectedY) * mCellSideX / 2;
                float botY = mOffset.y + (selectedX + selectedY) * mCellSideY / 2;

                // низ - лево
                points[0] = botX;
                points[1] = botY;

                // лево
                points[2] = botX - mCellSideX / 2;
                points[3] = botY + mCellSideY / 2;

                // верх
                points[4] = botX;
                points[5] = botY + mCellSideY;

                // право
                points[6] = botX + mCellSideX / 2;
                points[7] = botY + mCellSideY / 2;

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
            }
        }
        mSr.setColor(1, 1, 1, 1);
        mSr.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        batch.begin();
    }

    public AbstractBuildingItem getBuildingItem() {
        return mBuilding;
    }

    public AbstractBuildingView getBuildingView() {
        return mBuildingView;
    }

    public int getBuildingX() {
        return mBuildingPreviewX;
    }

    public int getBuildingY() {
        return mBuildingPreviewY;
    }

}
