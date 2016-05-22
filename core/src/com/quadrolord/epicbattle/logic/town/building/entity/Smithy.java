package com.quadrolord.epicbattle.logic.town.building.entity;

import com.badlogic.gdx.math.Vector2;
import com.quadrolord.epicbattle.logic.thing.entity.axe.WarAxe;
import com.quadrolord.epicbattle.logic.thing.entity.axe.WorkAxe;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingEntity;
import com.quadrolord.epicbattle.logic.town.building.BuildingItem;
import com.quadrolord.epicbattle.screen.AbstractScreen;
import com.quadrolord.epicbattle.screen.town.building.SmithyBuildingScreen;
import com.quadrolord.epicbattle.view.town.building.SmithyView;

/**
 * Created by Quadrowin on 08.03.2016.
 */
public class Smithy extends AbstractBuildingEntity {

    public Smithy() {
        setViewClass(SmithyView.class);

        setConstructionTime(3 * 1000);
        setTitle("Smithy");
        setSize(new Vector2(1, 1));
        setSliderTexture("town/smithy128.png");

        getAvailableThings().add(new WorkAxe());
        getAvailableThings().add(new WarAxe());
    }

    @Override
    public void initItem(BuildingItem item) {

    }

    @Override
    public void runOnSelect(BuildingItem item) {
        AbstractScreen scr = new SmithyBuildingScreen(item.getView().getScreen(), item);
        item.getView().getScreen().getAdapter().switchToScreen(scr, false);
    }

}
