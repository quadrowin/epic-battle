package com.quadrolord.epicbattle.logic.thing;

import com.badlogic.gdx.utils.ObjectMap;

/**
 * Created by Quadrowin on 20.03.2016.
 */

public class ThingCostElement extends ObjectMap.Entry<Class<? extends AbstractThingEntity>, Integer> {

    public ThingCostElement(Class<? extends AbstractThingEntity> resource, int count) {
        key = resource;
        value = count;
    }

    public int getCount() {
        return value;
    }

    public Class<? extends AbstractThingEntity> getResource() {
        return key;
    }

}
