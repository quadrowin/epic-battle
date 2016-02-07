package com.quadrolord.epicbattle.logic.town.resource;

/**
 * Источник ресурса
 * Created by Quadrowin on 07.02.2016.
 */
public class ResourceSourceItem {

    /**
     * Накопленный и не собранный на данный момент ресурс
     */
    private long mCurrentBalance = 0;

    /**
     * Время последнего забора ресурсов
     */
    private long mLastYield = 0;

    private ResourceSourceEntity mEntity;

    public ResourceSourceEntity getEntity() {
        return mEntity;
    }

    public long getLastYield() {
        return mLastYield;
    }

    public void setEntity(ResourceSourceEntity entity) {
        mEntity = entity;
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
