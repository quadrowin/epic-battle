package com.quadrolord.epicbattle.logic.town.building;

import com.quadrolord.epicbattle.logic.town.resource.Resource;

/**
 * Created by morph on 17.01.2016.
 */
public abstract class ResourceBuilding extends Building {
    protected Resource mResource;

    protected float mLastYield = 0;
    protected float mYieldCount = 0;

    public Resource getResource() {
        return mResource;
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
