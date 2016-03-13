package com.quadrolord.epicbattle.logic.town.building.entity;

import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.logic.town.building.CommonBuildingEntity;
import com.quadrolord.epicbattle.logic.town.building.CommonBuildingItem;
import com.quadrolord.epicbattle.logic.town.resource.IronOre;
import com.quadrolord.epicbattle.logic.town.resource.ResourceSourceEntity;
import com.quadrolord.epicbattle.screen.town.building.MineBuildingScreen;
import com.quadrolord.epicbattle.view.town.building.MineView;

/**
 * Created by Quadrowin on 31.01.2016.
 */
public class Mine extends CommonBuildingEntity {

    public Mine() {
        setViewClass(MineView.class);

        ResourceSourceEntity rse = new ResourceSourceEntity();
        rse.setResourceClass(IronOre.class);
        rse.setMaxBalance(100);
        rse.setProductionRate(10);
        getResources().add(rse);
    }


    @Override
    public void initItem(CommonBuildingItem item) {
        initItemResources(item);
        item.setSize((int) mSize.x, (int) mSize.y);
    }

    @Override
    public void runOnSelect(AbstractBuildingItem item) {
        MineBuildingScreen scr = new MineBuildingScreen(item.getView().getScreen(), item);
        item.getView().getScreen().getAdapter().switchToScreen(scr, false);
    }

}
