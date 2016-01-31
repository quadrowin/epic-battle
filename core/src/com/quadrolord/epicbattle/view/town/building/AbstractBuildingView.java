package com.quadrolord.epicbattle.view.town.building;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.screen.AbstractScreen;
import com.quadrolord.epicbattle.screen.town.MapGrid;

/**
 * Created by morph on 17.01.2016.
 */
abstract public class AbstractBuildingView extends Group {

    private AbstractBuildingItem mBuilding;

    private TextureRegion mBuildingTexture;

    private MapGrid mMap;

    private AbstractScreen mScreen;

    public AbstractBuildingView(final AbstractScreen screen, MapGrid map, AbstractBuildingItem building) {
        mBuilding = building;
        mMap = map;
        mScreen = screen;

        mBuildingTexture = loadBuildingTexture();

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

    public void drawBuilding(Batch batch) {
        float csx = mMap.getCellSideX();
        float csy = mMap.getCellSideY();
        batch.draw(
                mBuildingTexture,
                0, 0, csx * mBuilding.getSize().x, csy * mBuilding.getSize().y
        );
    }

    public AbstractBuildingItem getBuilding() {
        return mBuilding;
    }

    public TextureRegion getBuildingTexture() {
        return mBuildingTexture;
    }

    public MapGrid getMap() {
        return mMap;
    }

    public AbstractScreen getScreen() {
        return mScreen;
    }

    abstract public TextureRegion loadBuildingTexture();

    public void setBuildingTexture(TextureRegion texture) {
        mBuildingTexture = texture;
    }

}
