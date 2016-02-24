package com.quadrolord.epicbattle.screen.town;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
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

    private int mBuildingStartX;

    private int mBuildingStartY;

    private float mCellSideX = 60;
    private float mCellSideY = 40;

    private Group mWrapper;

    private float mDeltaX;

    private float mDeltaY;

    private ShapeRenderer mSr = new ShapeRenderer();

    private Vector2 mOffset = new Vector2(0, 0);

    public PlacingControl(AbstractScreen screen, AbstractBuildingItem building, AbstractBuildingView view) {
        mBuilding = building;
        mBuildingView = view;
        mBuildingStartX = mBuilding.getX();
        mBuildingStartY = mBuilding.getY();

        screen.getStage().addActor(this);
    }

    @Override
    public void act(float delta) {
        if (Gdx.input.isTouched()) {
            mDeltaX += Gdx.input.getDeltaX();
            mDeltaY -= Gdx.input.getDeltaY();

            mBuilding.setPosition(
                    mBuildingStartX + (int)((mDeltaY / mCellSideY + mDeltaX / mCellSideX)),
                    mBuildingStartY + (int)((mDeltaY / mCellSideY - mDeltaX / mCellSideX))
            );
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

        mSr.setProjectionMatrix(batch.getProjectionMatrix());
        mSr.setTransformMatrix(batch.getTransformMatrix());
        mSr.begin(ShapeRenderer.ShapeType.Line);

        mOffset.x = 0;
        mOffset.y = 0;
        mBuilding.getView().getParent().localToStageCoordinates(mOffset);

        float lineWidth = 3;

        int sizeX = (int)mBuilding.getSize().x;
        int sizeY = (int)mBuilding.getSize().y;
        for (int cx = 0; cx < sizeX; cx++) {
            for (int cy = 0; cy < sizeY; cy++) {
                int selectedX = mBuilding.getX() + cx;
                int selectedY = mBuilding.getY() + cy;

                Tile cell = mBuilding.getTown().getMapCell(selectedX, selectedY);
                if (cell != null && cell.isFree()) {
                    mSr.setColor(Color.BLUE);
                } else {
                    mSr.setColor(Color.RED);
                }

                float botX = mOffset.x + (selectedX - selectedY) * mCellSideX / 2;
                float botY = mOffset.y + (selectedX + selectedY) * mCellSideY / 2;

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
            }
        }

        mSr.end();

        batch.begin();
    }

}
