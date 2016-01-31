package com.quadrolord.epicbattle.logic.configurable;

/**
 * Created by Quadrowin on 31.01.2016.
 */
public class AbstractEntity<T extends AbstractItem> {

    private Class<T> mItemClass;

    public Class<T> getItemClass() {
        return mItemClass;
    }

    public void setItemClass(Class<T> workerClass) {
        mItemClass = (Class<T>)workerClass;
    }

}
