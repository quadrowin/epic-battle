package com.quadrolord.epicbattle.logic.town.building.entity;

import com.badlogic.gdx.math.Vector2;
import com.quadrolord.epicbattle.logic.town.building.CommonBuildingEntity;
import com.quadrolord.epicbattle.logic.town.building.CommonBuildingItem;
import com.quadrolord.epicbattle.view.town.building.SmithyView;

/**
 * Created by Quadrowin on 08.03.2016.
 */
public class Smithy extends CommonBuildingEntity {

    public Smithy() {
        setViewClass(SmithyView.class);

        setConstructionTime(30 * 1000);
        setTitle("Smithy");
        setSize(new Vector2(1, 1));
        setSliderTexture("town/smithy128.png");
    }

    @Override
    public void initItem(CommonBuildingItem item) {

    }
}
