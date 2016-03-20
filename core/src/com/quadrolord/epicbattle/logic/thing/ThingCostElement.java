package com.quadrolord.epicbattle.logic.thing;

import com.quadrolord.epicbattle.logic.town.resource.ResourceEntity;

/**
 * Created by Quadrowin on 20.03.2016.
 */
public class ThingCostElement {

    private Class<? extends ResourceEntity> mResource;

    private int mCount;


    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }

    public Class<? extends ResourceEntity> getResource() {
        return mResource;
    }

    public void setResource(Class<? extends ResourceEntity> resource) {
        mResource = resource;
    }
}
