package com.quadrolord.epicbattle.logic.town.building.entity;

import com.quadrolord.epicbattle.logic.thing.entity.resource.Noodles;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingEntity;
import com.quadrolord.epicbattle.logic.town.building.BuildingItem;
import com.quadrolord.epicbattle.logic.town.resource.ResourceSourceEntity;
import com.quadrolord.epicbattle.screen.town.building.MineBuildingScreen;
import com.quadrolord.epicbattle.view.town.building.DoodleShopView;

/**
 * Created by Quadrowin on 01.02.2016.
 */
public class DoodleShop extends AbstractBuildingEntity {

    public DoodleShop() {
        setViewClass(DoodleShopView.class);

        ResourceSourceEntity rse = new ResourceSourceEntity();
        rse.setResourceClass(Noodles.class);
        rse.setMaxBalance(100);
        rse.setProductionRate(10);
        getResources().add(rse);
    }

    @Override
    public void runOnSelect(BuildingItem item) {
        MineBuildingScreen scr = new MineBuildingScreen(item.getView().getScreen(), item);
        item.getView().getScreen().getAdapter().switchToScreen(scr, false);
    }

}
