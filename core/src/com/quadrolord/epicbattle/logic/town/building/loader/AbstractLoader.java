package com.quadrolord.epicbattle.logic.town.building.loader;

import com.badlogic.gdx.utils.JsonValue;
import com.quadrolord.epicbattle.logic.town.building.BuildingInfo;

/**
 * Created by morph on 24.01.2016.
 */
public abstract class AbstractLoader {
    abstract public void assign(BuildingInfo info, JsonValue data);
}
