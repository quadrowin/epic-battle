package com.quadrolord.epicbattle.logic.thing.entity;

import com.quadrolord.epicbattle.logic.thing.AbstractThingEntity;
import com.quadrolord.epicbattle.logic.thing.ThingCostElement;

/**
 * Created by Quadrowin on 27.03.2016.
 */
public class WarAxe extends AbstractThingEntity {

    public WarAxe() {
        setCraftTime(30);
        setImage("item/WarAxe512.png");
        setShort("WrAx");
        setTitle("War Axe");
        getCost().setGems(1);
        getCost().getResources().add(new ThingCostElement(IronOre.class, 2));
        getCost().getResources().add(new ThingCostElement(Wood.class, 1));
    }

}
