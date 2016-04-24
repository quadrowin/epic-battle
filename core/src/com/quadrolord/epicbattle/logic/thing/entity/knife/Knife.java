package com.quadrolord.epicbattle.logic.thing.entity.knife;

import com.quadrolord.epicbattle.logic.thing.AbstractThingEntity;
import com.quadrolord.epicbattle.logic.thing.ThingCostElement;
import com.quadrolord.epicbattle.logic.thing.entity.resource.IronOre;

/**
 * Created by Quadrowin on 23.04.2016.
 */
public class Knife extends AbstractThingEntity {

    public Knife() {
        setCraftTime(5);
        setImage("item/WarAxe512.png");
        setShort("Kn");
        setTitle("Knife");
        getCost().setGems(1);
        getCost().getResources().add(new ThingCostElement(IronOre.class, 1));
    }

}