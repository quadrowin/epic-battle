package com.quadrolord.epicbattle.logic.town.building.loader;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonValue;
import com.quadrolord.epicbattle.logic.town.building.BuildingInfo;

/**
 * Created by morph on 24.01.2016.
 */
public class Size extends AbstractLoader {
    @Override
    public void assign(BuildingInfo info, JsonValue data) {
        float[] axis = data.asFloatArray();
        info.setSize(new Vector2(axis[0], axis[1]));
    }
}
