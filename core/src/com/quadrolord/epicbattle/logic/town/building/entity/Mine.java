package com.quadrolord.epicbattle.logic.town.building.entity;

import com.quadrolord.epicbattle.logic.town.building.ResourceBuildingEntity;
import com.quadrolord.epicbattle.logic.town.building.ResourceBuildingItem;
import com.quadrolord.epicbattle.logic.town.resource.IronOre;
import com.quadrolord.epicbattle.view.town.building.MineView;

/**
 * Created by Quadrowin on 31.01.2016.
 */
public class Mine extends ResourceBuildingEntity {

    public Mine() {
        setViewClass(MineView.class);
    }

    @Override
    public void initItem(ResourceBuildingItem item) {
        item.setSize((int)mSize.x, (int)mSize.y);
        item.setResourceClass(IronOre.class);
    }

}
