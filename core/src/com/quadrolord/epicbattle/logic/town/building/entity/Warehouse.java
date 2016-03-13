package com.quadrolord.epicbattle.logic.town.building.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.logic.town.building.CommonBuildingEntity;
import com.quadrolord.epicbattle.logic.town.building.CommonBuildingItem;
import com.quadrolord.epicbattle.screen.HintScreen;
import com.quadrolord.epicbattle.view.town.building.WarehouseView;

/**
 * Created by Quadrowin on 12.03.2016.
 */
public class Warehouse extends CommonBuildingEntity {

    public Warehouse() {
        setViewClass(WarehouseView.class);

        setConstructionTime(30 * 1000);
        setTitle("Warehouse");
        setSize(new Vector2(1, 1));
        setSliderTexture("town/warehouse128.png");
    }

    @Override
    public void initItem(CommonBuildingItem item) {

    }

    @Override
    public void runOnSelect(AbstractBuildingItem item) {
        Gdx.app.log("RightLegTemple", "clicked");
        HintScreen hs = new HintScreen(item.getView().getScreen(), item.getView().getX(), item.getView().getY(), "It's your right leg");
        item.getView().getScreen().getAdapter().switchToScreen(hs, false);
    }

}
