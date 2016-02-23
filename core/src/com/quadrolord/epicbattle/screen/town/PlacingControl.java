package com.quadrolord.epicbattle.screen.town;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
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
                    mBuildingStartX + (int)(mDeltaX / 20),
                    mBuildingStartY + (int)(mDeltaY / 20)
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

        mSr.setColor(Color.BLUE);

        int selectedX = mBuilding.getX();
        int selectedY = mBuilding.getY();

        float botX = (selectedX - selectedY) * mCellSideX / 2;
        float botY = (selectedX + selectedY) * mCellSideY / 2;
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

        mSr.end();

        batch.begin();
    }

}
