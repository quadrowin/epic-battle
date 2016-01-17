package com.quadrolord.epicbattle.logic.town.building;

import com.badlogic.gdx.math.Vector2;
import com.quadrolord.epicbattle.logic.bullet.BulletInfo;

/**
 * Created by morph on 17.01.2016.
 */
public abstract class Building {
    protected Vector2 mPosition;
    protected Vector2 mSize;
    protected boolean mIsRotated = false;

    protected BuildingView mView;
    protected BuildingInfo mInfo;

    public Building() {

    }

    public Vector2 getPosition() {
        return mPosition;
    }

    public void setPosition(int x, int y) {
        mPosition.set(x, y);
    }

    public void setPosition(Vector2 position) {
        mPosition = position;
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

    public Vector2 getSize() {
        return mSize;
    }

    public void setSize(Vector2 size) {
        mSize = size;
    }

    public void setSize(int width, int height) {
        mSize.set(width, height);
    }

    public int getWidth() {
        return (int)mSize.x;
    }

    public int getHeight() {
        return (int)mSize.y;
    }

    public void setWidth(int width) {
        mSize.x = width;
    }

    public void setHeight(int height) {
        mSize.y = height;
    }

    public boolean isRotated() {
        return mIsRotated;
    }

    public void rotate() {
        mIsRotated = !mIsRotated;

        int width = getWidth();
        setWidth(getHeight());
        setHeight(width);
    }

    public BuildingView getView() {
        return mView;
    }

    public BuildingInfo getInfo() {
        return mInfo;
    }

    public void setInfo(BuildingInfo info) {
        mInfo = info;
    }
}
