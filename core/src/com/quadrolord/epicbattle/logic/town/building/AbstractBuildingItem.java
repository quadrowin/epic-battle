package com.quadrolord.epicbattle.logic.town.building;

import com.badlogic.gdx.math.Vector2;
import com.quadrolord.epicbattle.logic.configurable.AbstractItem;
import com.quadrolord.epicbattle.logic.town.MyTown;
import com.quadrolord.epicbattle.view.town.building.BuildingView;

/**
 * Created by morph on 17.01.2016.
 */
public abstract class AbstractBuildingItem<T extends AbstractBuildingEntity> extends AbstractItem<T> {

    protected Vector2 mPosition = new Vector2(0, 0);
    protected Vector2 mSize = new Vector2(50, 50);
    protected boolean mIsRotated = false;
    protected int mLevel = 1;

    protected BuildingView mView;

    protected MyTown mTown;
    protected boolean mIsInUpdating = false;

    protected float mRemainingUpdatingTime = 0;

    public AbstractBuildingItem(MyTown town) {
        mTown = town;
    }

    public MyTown getTown() {
        return mTown;
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

    public T getInfo() {
        return (T)super.getInfo();
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

    public boolean canLevelUp() {
        return mLevel <= getInfo().getLevelUps().size - 1;
    }

    public boolean isInUpdating() {
        return mIsInUpdating;
    }

    public float getRemainingUpdatingTime() {
        return mRemainingUpdatingTime;
    }

    public void levelUp() {
        getInfo().setLevel(mLevel + 1);
        mRemainingUpdatingTime = getInfo().getConstructionTime();
        mIsInUpdating = true;
    }
}
