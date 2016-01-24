package com.quadrolord.epicbattle.logic.town.building;

import com.quadrolord.epicbattle.logic.town.MyTown;
import com.quadrolord.epicbattle.logic.town.resource.IronOre;

/**
 * Created by morph on 24.01.2016.
 */
public class Mine extends ResourceBuilding {
    public Mine(MyTown myTown) {
        super(myTown);
        mSize.set(2, 2);

        mResourceClass = IronOre.class;
    }
}
