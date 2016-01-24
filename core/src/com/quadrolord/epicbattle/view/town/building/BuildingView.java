package com.quadrolord.epicbattle.view.town.building;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuilding;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Created by morph on 17.01.2016.
 */
public class BuildingView extends Group {

    private AbstractBuilding mBuilding;

    private Texture mBuildingTexture;

    private int mCellSize = 20;

    public BuildingView(AbstractScreen screen, Group map, AbstractBuilding building) {
        mBuilding = building;

        mBuildingTexture = new Texture("town/mine1.png");

        setPosition(building.getX(), building.getY());
        map.addActor(this);
    }

    public void draw (Batch batch, float parentAlpha) {
        Matrix4 m = computeTransform();
        m.rotate(1, 0, 0, 30);
        applyTransform(batch, m);
        drawBuilding(batch);
        drawChildren(batch, parentAlpha);
        resetTransform(batch);
    }

    private void drawBuilding(Batch batch) {

        float x = getX();
        float y = getY();

        batch.draw(mBuildingTexture, x, y, mCellSize * mBuilding.getSize().x,  mCellSize * mBuilding.getSize().y, 0f, 1f, 1f, 0f);

    }

}
