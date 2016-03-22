package com.quadrolord.epicbattle.logic.thing;

import com.badlogic.gdx.utils.Array;

/**
 * Created by Quadrowin on 20.03.2016.
 */
public class ThingCost {

    /**
     * Цена в гемах
     */
    private int mGems = 999999999;

    private Array<ThingCostElement> mResources = new Array<ThingCostElement>();


    public int getGems() {
        return mGems;
    }

    public void setGems(int gems) {
        mGems = gems;
    }

    public Array<ThingCostElement> getResources() {
        return mResources;
    }
}
