package com.quadrolord.epicbattle.logic.thing.entity;

import com.quadrolord.epicbattle.logic.thing.AbstractThingEntity;

/**
 * Created by morph on 17.01.2016.
 */
public class Money extends AbstractThingEntity {

    public Money() {
        setShort("$");
        setTitle("Money");
        setStackable(true);
    }

}
