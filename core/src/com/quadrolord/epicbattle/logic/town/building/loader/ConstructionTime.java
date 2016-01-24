package com.quadrolord.epicbattle.logic.town.building.loader;

import com.badlogic.gdx.utils.JsonValue;
import com.quadrolord.epicbattle.logic.town.building.BuildingInfo;

/**
 * Created by morph on 24.01.2016.
 */
public class ConstructionTime extends AbstractLoader {
    @Override
    public void assign(BuildingInfo info, JsonValue data) {
        info.setConstructionTime(data.asFloat());
    }
}
