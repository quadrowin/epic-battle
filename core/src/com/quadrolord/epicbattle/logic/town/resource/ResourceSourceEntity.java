package com.quadrolord.epicbattle.logic.town.resource;

import com.quadrolord.epicbattle.logic.configurable.AbstractEntity;

/**
 * Created by Quadrowin on 07.02.2016.
 */
public class ResourceSourceEntity extends AbstractEntity<ResourceSourceItem> {

    /**
     * Производство ресурса в секунду
     */
    private float mProductionRate = 0.1f;

    /**
     * Максимальное количество ресурса, которое может быть накоплено
     */
    private long mMaxBalance = 100;

    private Class<? extends ResourceEntity> mResourceClass;

    public long getMaxBalance() {
        return mMaxBalance;
    }

    public Class<? extends ResourceEntity> getResourceClass() {
        return mResourceClass;
    }

    public float getProductionRate() {
        return mProductionRate;
    }

    public void setMaxBalance(long value) {
        mMaxBalance = value;
    }

    public void setResourceClass(Class<? extends ResourceEntity> resourceClass) {
        mResourceClass = resourceClass;
    }

    public void setProductionRate(float value) {
        mProductionRate = value;
    }

    @Override
    public Class<? extends ResourceSourceItem> getItemClass() {
        return ResourceSourceItem.class;
    }

    @Override
    public void initItem(ResourceSourceItem item) {

    }
}
