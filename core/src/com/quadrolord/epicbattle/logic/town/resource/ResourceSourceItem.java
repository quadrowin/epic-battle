package com.quadrolord.epicbattle.logic.town.resource;

import com.quadrolord.epicbattle.logic.configurable.AbstractItem;

/**
 * Источник ресурса
 * Created by Quadrowin on 07.02.2016.
 */
public class ResourceSourceItem extends AbstractItem<ResourceSourceEntity> {

    /**
     * Накопленный и не собранный на данный момент ресурс
     */
    private long mCurrentBalance = 0;

    /**
     * Время последнего забора ресурсов
     */
    private long mLastYield = 0;

    public long getLastYield() {
        return mLastYield;
    }

    public void setLastYield(long yield) {
        mLastYield = yield;
    }

    public long getCurrentBalance() {
        return mCurrentBalance;
    }

    public void setCurrentBalance(long balance) {
        mCurrentBalance = balance;
    }

}
