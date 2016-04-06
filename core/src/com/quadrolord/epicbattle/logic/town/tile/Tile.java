package com.quadrolord.epicbattle.logic.town.tile;

import com.badlogic.gdx.math.Vector2;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.view.town.tile.TileView;

/**
 * Created by morph on 17.01.2016.
 */
public class Tile {
    protected Vector2 mPosition;
    protected TileView mView;
    protected boolean mIsFree;
    protected AbstractBuildingItem mBuilding;

    public Tile() {

    }

    public Tile(int x, int y) {
        this();
        mPosition = new Vector2(x, y);
    }

    public Tile(Vector2 position) {
        this();
        mPosition = position;
    }

    public void setPosition(int x, int y) {
        mPosition.set(x, y);
    }

    public void setPosition(Vector2 position) {
        mPosition = position;
    }

    public AbstractBuildingItem getBuilding() {
        return mBuilding;
    }

    public Vector2 getPosition() {
        return mPosition;
    }

    public int getX() {
        return (int)mPosition.x;
    }

    public int getY() {
        return (int)mPosition.y;
    }

    public void setX(int x) {
        mPosition.x = x;
    }

    public void setY(int y) {
        mPosition.y = y;
    }

    public TileView getView() {
        return mView;
    }

    public void setView(TileView view) {
        mView = view;
    }

    public boolean isFree() {
        return mBuilding == null;
    }

    public void markAsFree() {
        mBuilding = null;
    }

    public void markAsBusy(AbstractBuildingItem building) {
        mBuilding = building;
    }
}
