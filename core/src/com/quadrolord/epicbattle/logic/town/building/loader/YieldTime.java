package com.quadrolord.epicbattle.logic.town.building.loader;

import com.badlogic.gdx.utils.JsonValue;
import com.quadrolord.epicbattle.logic.town.building.BuildingInfo;
import com.quadrolord.epicbattle.logic.town.building.ResourceBuildingInfo;

/**
 * Created by morph on 24.01.2016.
 */
public class YieldTime extends AbstractLoader {
    @Override
    public void assign(BuildingInfo info, JsonValue data) {
        ((ResourceBuildingInfo)info).setYieldTime(data.asFloat());
    }
}
