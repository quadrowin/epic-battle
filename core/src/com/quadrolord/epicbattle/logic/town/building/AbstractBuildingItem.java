package com.quadrolord.epicbattle.logic.town.building;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.quadrolord.epicbattle.logic.configurable.AbstractItem;
import com.quadrolord.epicbattle.logic.town.MyTown;
import com.quadrolord.epicbattle.logic.town.resource.ResourceSourceItem;
import com.quadrolord.epicbattle.view.town.building.AbstractBuildingView;

/**
 * Created by morph on 17.01.2016.
 */
public abstract class AbstractBuildingItem<T extends AbstractBuildingEntity> extends AbstractItem<T> {

    protected Vector2 mPosition = new Vector2(0, 0);
    protected Vector2 mSize = new Vector2(1, 1);
    protected boolean mIsRotated = false;
    protected int mLevel = 1;

    protected AbstractBuildingView mView;

    protected boolean mIsInConstruction = false;

    private long mConstructionStartTime = 0;
    private long mConstructionFinishTime = 0;
    private long mConstructionDuration = 0;

    private Array<CraftPlanItem> mCraftPlan = new Array<CraftPlanItem>();

    private Array<ResourceSourceItem> mResources = new Array<ResourceSourceItem>();

    private MyTown mTown;

    public AbstractBuildingItem(MyTown town) {
        mTown = town;
    }

    public Vector2 getPosition() {
        return mPosition;
    }

    public MyTown getTown() {
        return mTown;
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

    public AbstractBuildingView getView() {
        return mView;
    }

    public boolean canLevelUp() {
        return mLevel <= getInfo().getLevelUps().size - 1;
    }

    public boolean isInConstruction() {
        if (!mIsInConstruction) {
            return false;
        }
        if (mConstructionFinishTime <= TimeUtils.millis()) {
            mIsInConstruction = false;
        }
        return mIsInConstruction;
    }

    public long getRemainingUpdatingTime() {
        if (!mIsInConstruction) {
            return 0;
        }
        long now = TimeUtils.millis();
        if (mConstructionFinishTime <= now) {
            mIsInConstruction = false;
            return 0;
        }
        return mConstructionFinishTime - now;
    }

    public void levelUp() {
        getInfo().setLevel(mLevel + 1);
        startConstruction();
    }

    public Array<ResourceSourceItem> getResources() {
        return mResources;
    }

    public void setView(AbstractBuildingView view) {
        mView = view;
    }

    public void startConstruction() {
        mConstructionStartTime = TimeUtils.millis();
        mConstructionDuration = getInfo().getConstructionTime();
        mConstructionFinishTime = mConstructionStartTime + mConstructionDuration;
        mIsInConstruction = true;
    }

    public Array<CraftPlanItem> getCraftPlan() {
        return mCraftPlan;
    }
}
