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
    private int mLevel = 1;

    protected AbstractBuildingView mView;

    private boolean mIsInConstruction = false;
    private long mConstructionStartTime = 0;
    private long mConstructionFinishTime = 0;

    private boolean mIsInUpgrading = false;
    private long mUpgradingStartTime = 0;
    private long mUpgradingFinishTime = 0;
    private AbstractBuildingEntity mUpgradingTarget;

    private Array<CraftPlanItem> mCraftPlan = new Array<CraftPlanItem>();

    private Array<ResourceSourceItem> mResources = new Array<ResourceSourceItem>();

    private MyTown mTown;

    public AbstractBuildingItem(MyTown town) {
        mTown = town;
    }

    public void finishConstruction() {
        mIsInConstruction = false;
        mConstructionStartTime = mConstructionFinishTime = 0;
    }

    /**
     *
     * @return from 0 to 1 progress
     */
    public float getConstructionProgress()
    {
        if (!mIsInConstruction) {
            return 0;
        }
        long now = TimeUtils.millis();
        if (now >= mConstructionFinishTime) {
            return 1;
        }
        if (now <= mConstructionStartTime) {
            return 0;
        }
        return (now - mConstructionStartTime) / (mConstructionFinishTime - mConstructionStartTime);
    }

    public boolean isInConstruction() {
        return mIsInConstruction;
    }

    public long getConstructionRemainingTime() {
        if (!mIsInConstruction) {
            return 0;
        }
        long now = TimeUtils.millis();
        if (mConstructionFinishTime <= now) {
            return 0;
        }
        return mConstructionFinishTime - now;
    }

    public void upgradingSuccess() {
        mIsInUpgrading = false;
        mUpgradingStartTime = mUpgradingFinishTime = 0;
        setInfo((T)mUpgradingTarget);
        mUpgradingTarget = null;
    }

    public void upgradingTerminate() {
        mIsInUpgrading = false;
        mUpgradingStartTime = mUpgradingFinishTime = 0;
        mUpgradingTarget = null;
    }

    /**
     *
     * @return from 0 to 1
     */
    public float getUpgradingProgress()
    {
        long now = TimeUtils.millis();
        if (now >= mUpgradingFinishTime) {
            return 1;
        }
        if (now <= mUpgradingStartTime) {
            return 0;
        }
        return (now - mUpgradingStartTime) / (mUpgradingFinishTime - mUpgradingStartTime);
    }

    public long getUpgradingRemainingTime() {
        if (!mIsInUpgrading) {
            return 0;
        }
        long now = TimeUtils.millis();
        if (mUpgradingFinishTime <= now) {
            return 0;
        }
        return mUpgradingFinishTime - now;
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

    public int getLevel() {
        return getInfo().getLevel();
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

    public boolean isInUpgrading() {
        return mIsInUpgrading;
    }


    public Array<ResourceSourceItem> getResources() {
        return mResources;
    }

    public void setView(AbstractBuildingView view) {
        mView = view;
    }

    public void startConstruction(long duration) {
        mConstructionStartTime = TimeUtils.millis();
        mConstructionFinishTime = mConstructionStartTime + duration;
        mIsInConstruction = true;
    }

    /**
     * Возвращает запланированные на крафт предметы
     * @return
     */
    public Array<CraftPlanItem> getCraftPlan() {
        return mCraftPlan;
    }

    public void startUpgrading(AbstractBuildingEntity target) {
        mUpgradingTarget = target;
        mUpgradingStartTime = TimeUtils.millis();
        mUpgradingFinishTime = mUpgradingStartTime + target.getConstructionTime();
        mIsInUpgrading = true;
    }

}
