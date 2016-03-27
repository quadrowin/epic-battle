package com.quadrolord.epicbattle.logic.thing.entity;

import com.quadrolord.epicbattle.logic.thing.AbstractThingEntity;
import com.quadrolord.epicbattle.logic.thing.ThingCostElement;
import com.quadrolord.epicbattle.logic.town.resource.IronOre;
import com.quadrolord.epicbattle.logic.town.resource.Wood;

/**
 * Created by Quadrowin on 27.03.2016.
 */
public class WarAxeEntity extends AbstractThingEntity {

    public WarAxeEntity() {
        setImage("item/WarAxe512.png");
        setTitle("War Axe");
        getCost().setGems(1);
        getCost().getResources().add(new ThingCostElement(IronOre.class, 2));
        getCost().getResources().add(new ThingCostElement(Wood.class, 1));
    }

}
