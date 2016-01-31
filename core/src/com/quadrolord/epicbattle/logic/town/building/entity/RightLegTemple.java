package com.quadrolord.epicbattle.logic.town.building.entity;

import com.badlogic.gdx.Gdx;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.logic.town.building.CommonBuildingEntity;
import com.quadrolord.epicbattle.screen.HintScreen;
import com.quadrolord.epicbattle.view.town.building.RightLegTempleView;

/**
 * Created by Quadrowin on 31.01.2016.
 */
public class RightLegTemple extends CommonBuildingEntity {

    public RightLegTemple() {
        setViewClass(RightLegTempleView.class);
    }

    @Override
    public void runOnSelect(AbstractBuildingItem item) {
        Gdx.app.log("RightLegTemple", "clicked");
        HintScreen hs = new HintScreen(item.getView().getScreen(), item.getView().getX(), item.getView().getY(), "It's your right leg");
        item.getView().getScreen().getAdapter().switchToScreen(hs, false);
    }

}