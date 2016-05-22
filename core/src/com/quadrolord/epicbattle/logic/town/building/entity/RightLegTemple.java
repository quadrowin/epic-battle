package com.quadrolord.epicbattle.logic.town.building.entity;

import com.badlogic.gdx.Gdx;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingEntity;
import com.quadrolord.epicbattle.logic.town.building.BuildingItem;
import com.quadrolord.epicbattle.screen.HintScreen;
import com.quadrolord.epicbattle.view.town.building.RightLegTempleView;

/**
 * Created by Quadrowin on 31.01.2016.
 */
public class RightLegTemple extends AbstractBuildingEntity {

    public RightLegTemple() {
        setAllowDestruction(false);
        setAllowMoving(false);
        setViewClass(RightLegTempleView.class);
    }

    @Override
    public void runOnSelect(BuildingItem item) {
        Gdx.app.log("RightLegTemple", "clicked");
        HintScreen hs = new HintScreen(item.getView().getScreen(), item.getView().getX(), item.getView().getY(), "It's your right leg");
        item.getView().getScreen().getAdapter().switchToScreen(hs, false);
    }

}