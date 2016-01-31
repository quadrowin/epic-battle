package com.quadrolord.epicbattle.logic.town.building.entity;

import com.badlogic.gdx.math.Vector2;
import com.quadrolord.epicbattle.logic.town.building.ResourceBuildingEntity;
import com.quadrolord.epicbattle.logic.town.building.ResourceBuildingItem;
import com.quadrolord.epicbattle.logic.town.resource.IronOre;

/**
 * Created by Quadrowin on 31.01.2016.
 */
public class Mine extends ResourceBuildingEntity {

    public Mine() {
        mSize = new Vector2(2, 2);
    }

    @Override
    public void initItem(ResourceBuildingItem item) {
        item.getSize().set(mSize.x, mSize.y);
        item.setResourceClass(IronOre.class);
    }

}
