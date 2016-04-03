package com.quadrolord.epicbattle.logic.town.resource;

import com.quadrolord.epicbattle.logic.configurable.AbstractEntity;
import com.quadrolord.epicbattle.logic.thing.AbstractThingEntity;

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
    private int mMaxBalance = 100;

    private Class<? extends AbstractThingEntity> mResourceClass;

    public int getMaxBalance() {
        return mMaxBalance;
    }

    public Class<? extends AbstractThingEntity> getResourceClass() {
        return mResourceClass;
    }

    public float getProductionRate() {
        return mProductionRate;
    }

    public void setMaxBalance(int value) {
        mMaxBalance = value;
    }

    public void setResourceClass(Class<? extends AbstractThingEntity> resourceClass) {
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
