package com.quadrolord.epicbattle.logic.town.building;

import com.quadrolord.epicbattle.logic.thing.ThingCost;

/**
 * Процесс обновления здания
 */
public class UpgradingProcess {

    /**
     * Потраченные на запуск обновления ресурсы
     */
    private ThingCost mCost;

    private long mStartTime = 0;

    private long mFinishTime = 0;

    /**
     * Во что превращается здание
     */
    private AbstractBuildingEntity mTarget;

    public UpgradingProcess(ThingCost cost, AbstractBuildingEntity target, long startTime) {
        mCost = cost;
        mTarget = target;
        mStartTime = startTime;
        mFinishTime = startTime + target.getConstructionTime();
    }

    public ThingCost getCost()
    {
        return mCost;
    }

    public AbstractBuildingEntity getTarget()
    {
        return mTarget;
    }

    public long getStartTime()
    {
        return mStartTime;
    }

    public long getFinishTime()
    {
        return mFinishTime;
    }

}
