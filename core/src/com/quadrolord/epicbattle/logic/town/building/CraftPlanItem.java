package com.quadrolord.epicbattle.logic.town.building;

import com.quadrolord.epicbattle.logic.thing.AbstractThingEntity;

/**
 * Created by Quadrowin on 26.03.2016.
 */
public class CraftPlanItem {

    private AbstractThingEntity mThing;

    private long mCreated;

    /**
     * Время начала крафта в миллисекундах
     * @return
     */
    public long getCreated() {
        return mCreated;
    }

    public void setCreated(long created) {
        mCreated = created;
    }

    public AbstractThingEntity getThing() {
        return mThing;
    }

    public void setThing(AbstractThingEntity thing) {
        mThing = thing;
    }
}
