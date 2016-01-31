package com.quadrolord.epicbattle.logic.town.building.entity;

import com.quadrolord.epicbattle.logic.town.MyTown;
import com.quadrolord.epicbattle.logic.town.building.ResourceBuildingItem;
import com.quadrolord.epicbattle.logic.town.resource.IronOre;

/**
 * Created by morph on 24.01.2016.
 */
public class Mine extends ResourceBuildingItem {

    public Mine(MyTown myTown) {
        super(myTown);
        mSize.set(2, 2);

        mResourceClass = IronOre.class;
    }

}
