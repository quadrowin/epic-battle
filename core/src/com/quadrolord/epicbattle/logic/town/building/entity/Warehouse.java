package com.quadrolord.epicbattle.logic.town.building.entity;

import com.badlogic.gdx.math.Vector2;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingEntity;
import com.quadrolord.epicbattle.logic.town.building.BuildingItem;
import com.quadrolord.epicbattle.screen.AbstractScreen;
import com.quadrolord.epicbattle.screen.town.building.WarehouseScreen;
import com.quadrolord.epicbattle.view.town.building.WarehouseView;

/**
 * Created by Quadrowin on 12.03.2016.
 */
public class Warehouse extends AbstractBuildingEntity {

    public Warehouse() {
        setViewClass(WarehouseView.class);

        setConstructionTime(3 * 1000);
        setTitle("Warehouse");
        setSize(new Vector2(1, 1));
        setSliderTexture("town/warehouse128.png");
    }

    @Override
    public void initItem(BuildingItem item) {

    }

    @Override
    public void runOnSelect(BuildingItem item) {
        AbstractScreen scr = new WarehouseScreen(item.getView().getScreen(), item);
        item.getView().getScreen().getAdapter().switchToScreen(scr, false);
    }

}
