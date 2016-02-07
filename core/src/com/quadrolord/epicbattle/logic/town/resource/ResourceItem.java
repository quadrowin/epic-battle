package com.quadrolord.epicbattle.logic.town.resource;

/**
 * Состояние ресурса у игрока
 * Created by Quadrowin on 01.02.2016.
 */
public class ResourceItem {

    private Resource mInfo;

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

    public Resource getInfo() {
        return mInfo;
    }

    public void incValue(float delta) {
        mValue += delta;
    }

    public void setDelta(float delta) {
        mDelta = delta;
    }

    public void setInfo(Resource info) {
        mInfo = info;
    }

    public void setValue(float value) {
        mValue = value;
    }

}
