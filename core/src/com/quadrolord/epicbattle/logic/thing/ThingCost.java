package com.quadrolord.epicbattle.logic.thing;

import com.badlogic.gdx.utils.ArrayMap;

/**
 * Created by Quadrowin on 20.03.2016.
 */
public class ThingCost {

    /**
     * Цена в гемах
     */
    private int mGems = 999999999;

    private ArrayMap<Class<? extends AbstractThingEntity>, Integer> mResources;

    public ThingCost(ArrayMap<Class<? extends AbstractThingEntity>, Integer> resources) {
        mResources = resources;
    }

    public int getGems() {
        return mGems;
    }

    public void setGems(int gems) {
        mGems = gems;
    }

    public ArrayMap<Class<? extends AbstractThingEntity>, Integer> getResources() {
        return mResources;
    }
}
