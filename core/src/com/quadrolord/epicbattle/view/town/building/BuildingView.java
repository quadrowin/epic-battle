package com.quadrolord.epicbattle.view.town.building;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuilding;
import com.quadrolord.epicbattle.screen.AbstractScreen;
import com.quadrolord.epicbattle.screen.town.MapGrid;

/**
 * Created by morph on 17.01.2016.
 */
public class BuildingView extends Group {

    private AbstractBuilding mBuilding;

    private Texture mBuildingTexture;

    private MapGrid mMap;

    public BuildingView(AbstractScreen screen, MapGrid map, AbstractBuilding building) {
        mBuilding = building;
        mMap = map;

        mBuildingTexture = new Texture("town/mine1.png");

        map.setChildPosition(this, building.getX(), building.getY());
        map.addActor(this);
    }

    @Override
    public void act(float delta) {
        mMap.setChildPosition(this, mBuilding.getX(), mBuilding.getY());
    }

    public void draw (Batch batch, float parentAlpha) {
        applyTransform(batch, computeTransform());
        drawBuilding(batch);
        drawChildren(batch, parentAlpha);
        resetTransform(batch);
    }

    private void drawBuilding(Batch batch) {
        batch.draw(
                mBuildingTexture,
                0, 0, mMap.getCellSide() * mBuilding.getSize().x,  mMap.getCellSide() * mBuilding.getSize().y,
                0f, 1f, 1f, 0f
        );
    }

}
