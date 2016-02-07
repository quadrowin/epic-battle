package com.quadrolord.epicbattle.logic.town.resource;

/**
 * Created by Quadrowin on 07.02.2016.
 */
public class ResourceSourceEntity {

    private int mMaxBalance;

    private float mYieldTime;

    private Class<? extends Resource> mResourceClass;

    public int getMaxBalance() {
        return mMaxBalance;
    }

    public Class<? extends Resource> getResourceClass() {
        return mResourceClass;
    }

    public float getYieldTime() {
        return mYieldTime;
    }

    public void setMaxBalance(int value) {
        mMaxBalance = value;
    }

    public void setResourceClass(Class<? extends Resource> resourceClass) {
        mResourceClass = resourceClass;
    }

    public void setYieldTime(float value) {
        mYieldTime = value;
    }

}
