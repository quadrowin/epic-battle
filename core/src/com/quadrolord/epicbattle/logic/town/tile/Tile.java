package com.quadrolord.epicbattle.logic.town.tile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.quadrolord.epicbattle.view.town.tile.TileView;

/**
 * Created by morph on 17.01.2016.
 */
public abstract class Tile {
    protected Vector2 mPosition;
    protected TileView mView;
    protected boolean mIsFree;

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
        return mIsFree;
    }

    public void markAsFree() {
        mIsFree = true;
    }

    public void markAsBusy() {
        mIsFree = false;
    }
}