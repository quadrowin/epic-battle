package com.quadrolord.epicbattle.logic.town.building;

import com.quadrolord.epicbattle.logic.town.MyTown;
import com.quadrolord.epicbattle.logic.town.resource.Resource;

/**
 * Created by morph on 17.01.2016.
 */
public class ResourceBuildingItem extends AbstractBuildingItem<ResourceBuildingEntity> {

    protected Class<? extends Resource> mResourceClass;

    private long mCurrentBalance = 0;

    /**
     * Время последнего забора ресурсов
     */
    private long mLastYield = 0;

    public ResourceBuildingItem(MyTown town) {
        super(town);
    }

    public Class<? extends Resource> getResourceClass() {
        return mResourceClass;
    }

    public long getLastYield() {
        return mLastYield;
    }

    public void setLastYield(long yield) {
        mLastYield = yield;
    }

    public void setResourceClass(Class<? extends Resource> resourceClass) {
        mResourceClass = resourceClass;
    }

    public long getCurrentBalance() {
        return mCurrentBalance;
    }

    public void setCurrentBalance(long balance) {
        mCurrentBalance = balance;
    }

}
