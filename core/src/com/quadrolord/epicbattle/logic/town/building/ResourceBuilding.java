package com.quadrolord.epicbattle.logic.town.building;

import com.quadrolord.epicbattle.logic.town.MyTown;
import com.quadrolord.epicbattle.logic.town.resource.Resource;

/**
 * Created by morph on 17.01.2016.
 */
public abstract class ResourceBuilding extends AbstractBuilding {
    protected Class<? extends Resource> mResourceClass;

    protected float mLastYield = 0;
    protected float mYieldCount = 0;

    public ResourceBuilding(MyTown town) {
        super(town);
    }

    public Class<? extends Resource> getResourceClass() {
        return mResourceClass;
    }

    public float getLastYield() {
        return mLastYield;
    }

    public void setLastYield(float yield) {
        mLastYield = yield;
    }

    public float getYieldCount() {
        return mYieldCount;
    }

    public void setYieldCount(float count) {
        mYieldCount = count;
    }
}
