package com.quadrolord.epicbattle.logic.thing;

import com.quadrolord.epicbattle.logic.town.resource.ResourceEntity;

/**
 * Created by Quadrowin on 20.03.2016.
 */
public class ThingCostElement {

    private Class<? extends ResourceEntity> mResource;

    private int mCount;

    public ThingCostElement(Class<? extends ResourceEntity> resource, int count) {
        mResource = resource;
        mCount = count;
    }

    public int getCount() {
        return mCount;
    }

    public Class<? extends ResourceEntity> getResource() {
        return mResource;
    }

}
