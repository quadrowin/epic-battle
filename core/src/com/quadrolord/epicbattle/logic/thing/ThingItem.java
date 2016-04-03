package com.quadrolord.epicbattle.logic.thing;

import com.quadrolord.epicbattle.logic.configurable.AbstractItem;

/**
 * Предмет у игрока
 * Created by Quadrowin on 20.03.2016.
 */
public class ThingItem extends AbstractItem<AbstractThingEntity> {

    /**
     * Количество предмета
     */
    private int mCount;

    public int getCount() {
        return mCount;
    }

    public void incCount(int delta) {
        mCount += delta;
    }

    public void setCount(int count) {
        mCount = count;
    }

}
