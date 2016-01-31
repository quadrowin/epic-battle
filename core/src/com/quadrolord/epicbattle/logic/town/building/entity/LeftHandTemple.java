package com.quadrolord.epicbattle.logic.town.building.entity;

import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.logic.town.building.CommonBuildingEntity;
import com.quadrolord.epicbattle.screen.HintScreen;
import com.quadrolord.epicbattle.view.town.building.LeftHandTempleView;

/**
 * Created by Quadrowin on 30.01.2016.
 */
public class LeftHandTemple extends CommonBuildingEntity {

    public LeftHandTemple() {
        setViewClass(LeftHandTempleView.class);
    }

    @Override
    public void runOnSelect(AbstractBuildingItem item) {
        HintScreen hs = new HintScreen(item.getView().getScreen(), item.getView().getX(), item.getView().getY(), "It's your left hand");
        item.getView().getScreen().getAdapter().switchToScreen(hs, false);
    }

}