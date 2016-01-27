package com.quadrolord.epicbattle.logic.town.building.loader;

import com.badlogic.gdx.utils.JsonValue;
import com.quadrolord.epicbattle.logic.town.building.BuildingInfo;

/**
 * Created by morph on 24.01.2016.
 */
public class GemCost extends AbstractLoader {
    @Override
    public void assign(BuildingInfo info, JsonValue data) {
        info.setCostGem(data.asInt());
    }
}