package com.quadrolord.epicbattle.logic.town.building.loader;

import com.badlogic.gdx.utils.JsonValue;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingEntity;

/**
 * Created by morph on 24.01.2016.
 */
public class ConstructionTime extends AbstractLoader {
    @Override
    public void assign(AbstractBuildingEntity info, JsonValue data) {
        info.setConstructionTime(data.asFloat());
    }
}
