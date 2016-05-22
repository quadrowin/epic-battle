package com.quadrolord.epicbattle.logic.thing.entity.axe;

import com.quadrolord.epicbattle.logic.thing.AbstractThingEntity;
import com.quadrolord.epicbattle.logic.thing.entity.resource.IronOre;
import com.quadrolord.epicbattle.logic.thing.entity.resource.Wood;

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
        getCost().getResources().put(IronOre.class, 2);
        getCost().getResources().put(Wood.class, 1);
    }

}
