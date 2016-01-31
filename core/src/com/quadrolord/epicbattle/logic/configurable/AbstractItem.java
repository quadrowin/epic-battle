package com.quadrolord.epicbattle.logic.configurable;

/**
 * Created by Quadrowin on 31.01.2016.
 */
public class AbstractItem<T extends AbstractEntity> {

    private T mInfo;

    public T getInfo() {
        return mInfo;
    }

    public void setInfo(T info) {
        mInfo = info;
    }

}
