package com.quadrolord.epicbattle.logic.town.building;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.quadrolord.ejge.entity.AbstractItem;
import com.quadrolord.epicbattle.logic.thing.ThingCost;
import com.quadrolord.epicbattle.logic.town.MyTown;
import com.quadrolord.epicbattle.logic.town.resource.ResourceSourceItem;
import com.quadrolord.epicbattle.view.town.building.AbstractBuildingView;

/**
 * Created by morph on 17.01.2016.
 */
public class BuildingItem<T extends AbstractBuildingEntity> extends AbstractItem<T> {

    protected Vector2 mPosition = new Vector2(0, 0);
    protected Vector2 mSize = new Vector2(1, 1);
    protected boolean mIsRotated = false;

    protected AbstractBuildingView mView;

    private UpgradingProcess mConstruction;

    private UpgradingProcess mUpgrading;

    private Array<CraftPlanItem> mCraftPlan = new Array<CraftPlanItem>();

    private Array<ResourceSourceItem> mResources = new Array<ResourceSourceItem>();

    private MyTown mTown;

    public BuildingItem(MyTown town) {
        mTown = town;
    }

    public void finishConstruction() {
        mConstruction = null;
    }

    /**
     *
     * @return from 0 to 1 progress
     */
    public float getConstructionProgress()
    {
        if (mConstruction == null) {
            return 0;
        }
        long now = TimeUtils.millis();
        if (now >= mConstruction.getFinishTime()) {
            return 1;
        }
        long startTime = mConstruction.getStartTime();
        if (now <= startTime) {
            return 0;
        }
        return (now - startTime) / (mConstruction.getFinishTime() - startTime);
    }

    public boolean isInConstruction() {
        return mConstruction != null;
    }

    public long getConstructionRemainingTime() {
        if (mConstruction == null) {
            return 0;
        }
        long now = TimeUtils.millis();
        if (mConstruction.getFinishTime() <= now) {
            return 0;
        }
        return mConstruction.getFinishTime() - now;
    }

    public void upgradingSuccess() {
        setInfo((T)mUpgrading.getTarget());
        mUpgrading = null;
    }

    public void upgradingTerminate() {
        mUpgrading = null;
    }

    /**
     *
     * @return from 0 to 1
     */
    public float getUpgradingProgress()
    {
        long now = TimeUtils.millis();
        if (mUpgrading == null || now >= mUpgrading.getFinishTime()) {
            return 1;
        }
        long startTime = mUpgrading.getStartTime();
        if (now <= startTime) {
            return 0;
        }
        return (now - startTime) / (mUpgrading.getFinishTime() - startTime);
    }

    public long getUpgradingRemainingTime() {
        if (mUpgrading == null) {
            return 0;
        }
        long now = TimeUtils.millis();
        if (mUpgrading.getFinishTime() <= now) {
            return 0;
        }
        return mUpgrading.getFinishTime() - now;
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
        return mUpgrading != null;
    }


    public Array<ResourceSourceItem> getResources() {
        return mResources;
    }

    public void setView(AbstractBuildingView view) {
        mView = view;
    }

    public void startConstruction(AbstractBuildingEntity target, ThingCost cost) {
        mConstruction = new UpgradingProcess(
                cost,
                target,
                TimeUtils.millis()
        );
    }

    /**
     * Возвращает запланированные на крафт предметы
     * @return
     */
    public Array<CraftPlanItem> getCraftPlan() {
        return mCraftPlan;
    }

    public void startUpgrading(AbstractBuildingEntity target, ThingCost cost) {
        mUpgrading = new UpgradingProcess(
                cost,
                target,
                TimeUtils.millis()
        );
    }

}
