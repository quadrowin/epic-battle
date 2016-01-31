package com.quadrolord.epicbattle.logic.town.building;

import com.quadrolord.epicbattle.logic.town.resource.Resource;

/**
 * Created by morph on 17.01.2016.
 */
public class ResourceBuildingItem extends AbstractBuildingItem<ResourceBuildingEntity> {

    protected Class<? extends Resource> mResourceClass;

    protected float mLastYield = 0;

    protected float mYieldCount = 0;

    public Class<? extends Resource> getResourceClass() {
        return mResourceClass;
    }

    public float getLastYield() {
        return mLastYield;
    }

    public void setLastYield(float yield) {
        mLastYield = yield;
    }

    public void setResourceClass(Class<? extends Resource> resourceClass) {
        mResourceClass = resourceClass;
    }

    public float getYieldCount() {
        return mYieldCount;
    }

    public void setYieldCount(float count) {
        mYieldCount = count;
    }

}
