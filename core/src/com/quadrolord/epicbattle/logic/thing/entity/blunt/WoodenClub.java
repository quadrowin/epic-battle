package com.quadrolord.epicbattle.logic.thing.entity.blunt;

import com.quadrolord.epicbattle.logic.thing.AbstractThingEntity;
import com.quadrolord.epicbattle.logic.thing.entity.resource.Wood;

/**
 * Created by Quadrowin on 23.04.2016.
 */
public class WoodenClub extends AbstractThingEntity {

    public WoodenClub() {
        setCraftTime(5);
        setImage("item/WarAxe512.png");
        setShort("WdCb");
        setTitle("Wooden Club");
        getCost().setGems(1);
        getCost().getResources().put(Wood.class, 1);
    }

}