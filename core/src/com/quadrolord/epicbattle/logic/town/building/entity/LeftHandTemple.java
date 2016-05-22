package com.quadrolord.epicbattle.logic.town.building.entity;

import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingEntity;
import com.quadrolord.epicbattle.logic.town.building.BuildingItem;
import com.quadrolord.epicbattle.screen.HintScreen;
import com.quadrolord.epicbattle.view.town.building.LeftHandTempleView;

/**
 * Created by Quadrowin on 30.01.2016.
 */
public class LeftHandTemple extends AbstractBuildingEntity {

    public LeftHandTemple() {
        setAllowDestruction(false);
        setAllowMoving(false);
        setViewClass(LeftHandTempleView.class);
    }

    @Override
    public void runOnSelect(BuildingItem item) {
        HintScreen hs = new HintScreen(item.getView().getScreen(), item.getView().getX(), item.getView().getY(), "It's your left hand");
        item.getView().getScreen().getAdapter().switchToScreen(hs, false);
    }

}