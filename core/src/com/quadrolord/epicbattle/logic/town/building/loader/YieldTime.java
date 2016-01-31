package com.quadrolord.epicbattle.logic.town.building.loader;

import com.badlogic.gdx.utils.JsonValue;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingEntity;
import com.quadrolord.epicbattle.logic.town.building.ResourceBuildingEntity;

/**
 * Created by morph on 24.01.2016.
 */
public class YieldTime extends AbstractLoader {
    @Override
    public void assign(AbstractBuildingEntity info, JsonValue data) {
        ((ResourceBuildingEntity)info).setYieldTime(data.asFloat());
    }
}
