package com.quadrolord.epicbattle.logic.thing.entity;

import com.quadrolord.epicbattle.logic.thing.AbstractThingEntity;
import com.quadrolord.epicbattle.logic.thing.ThingCostElement;

/**
 * Created by Quadrowin on 20.03.2016.
 */
public class WorkAxe extends AbstractThingEntity {

    public WorkAxe() {
        setCraftTime(120);
        setImage("item/WorkAxe512.png");
        setShort("WkAx");
        setTitle("Work Axe");
        getCost().setGems(1);
        getCost().getResources().add(new ThingCostElement(IronOre.class, 1));
        getCost().getResources().add(new ThingCostElement(Wood.class, 1));
    }

}
