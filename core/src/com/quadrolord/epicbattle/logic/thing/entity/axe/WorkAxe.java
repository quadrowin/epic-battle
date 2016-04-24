package com.quadrolord.epicbattle.logic.thing.entity.axe;

import com.quadrolord.epicbattle.logic.thing.AbstractThingEntity;
import com.quadrolord.epicbattle.logic.thing.ThingCostElement;
import com.quadrolord.epicbattle.logic.thing.entity.resource.IronOre;
import com.quadrolord.epicbattle.logic.thing.entity.resource.Wood;

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
