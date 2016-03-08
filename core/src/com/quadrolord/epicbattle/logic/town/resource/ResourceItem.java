package com.quadrolord.epicbattle.logic.town.resource;

import com.quadrolord.epicbattle.logic.configurable.AbstractItem;

/**
 * Состояние ресурса у игрока
 * Created by Quadrowin on 01.02.2016.
 */
public class ResourceItem extends AbstractItem<ResourceEntity> {

    private float mValue;

    /**
     * Прирост ресурса в секунду
     * @return
     */
    private float mDelta;

    public float getDelta() {
        return mDelta;
    }

    public float getValue() {
        return mValue;
    }

    public void incValue(float delta) {
        mValue += delta;
    }

    public void setDelta(float delta) {
        mDelta = delta;
    }

    public void setValue(float value) {
        mValue = value;
    }

}
