package com.quadrolord.epicbattle.logic.town.resource;

/**
 * Created by Quadrowin on 07.02.2016.
 */
public class ResourceSourceEntity {

    /**
     * Производство ресурса в секунду
     */
    private float mProductionRate = 0.1f;

    /**
     * Максимальное количество ресурса, которое может быть накоплено
     */
    private long mMaxBalance = 100;

    private Class<? extends Resource> mResourceClass;

    public long getMaxBalance() {
        return mMaxBalance;
    }

    public Class<? extends Resource> getResourceClass() {
        return mResourceClass;
    }

    public float getProductionRate() {
        return mProductionRate;
    }

    public void setMaxBalance(long value) {
        mMaxBalance = value;
    }

    public void setResourceClass(Class<? extends Resource> resourceClass) {
        mResourceClass = resourceClass;
    }

    public void setProductionRate(float value) {
        mProductionRate = value;
    }

}
