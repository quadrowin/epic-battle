package com.quadrolord.epicbattle.logic.town.building.entity;

import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingEntity;
import com.quadrolord.epicbattle.view.town.building.SheepFarmView;

/**
 * Created by Quadrowin on 01.02.2016.
 */
public class SheepFarm extends AbstractBuildingEntity {

    public SheepFarm() {
        setViewClass(SheepFarmView.class);
    }

}
