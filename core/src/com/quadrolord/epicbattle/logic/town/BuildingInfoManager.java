package com.quadrolord.epicbattle.logic.town;

import com.badlogic.gdx.utils.JsonReader;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuilding;
import com.quadrolord.epicbattle.logic.town.building.BuildingInfo;

/**
 * Created by morph on 23.01.2016.
 */
public class BuildingInfoManager {
    private JsonReader mReader;

    public BuildingInfoManager() {
        mReader = new JsonReader();
    }

    public BuildingInfo getBuildingInfo(Class<? extends AbstractBuilding> buildingClass) {
        BuildingInfo info = new BuildingInfo();

        return info;
    }
}
