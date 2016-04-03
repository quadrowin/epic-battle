package com.quadrolord.epicbattle.logic.thing;

/**
 * Created by Quadrowin on 20.03.2016.
 */
public class ThingCostElement {

    private Class<? extends AbstractThingEntity> mResource;

    private int mCount;

    public ThingCostElement(Class<? extends AbstractThingEntity> resource, int count) {
        mResource = resource;
        mCount = count;
    }

    public int getCount() {
        return mCount;
    }

    public Class<? extends AbstractThingEntity> getResource() {
        return mResource;
    }

}
