package com.quadrolord.epicbattle.logic.town.building.loader;

import com.badlogic.gdx.utils.JsonValue;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingEntity;

/**
 * Created by Quadrowin on 31.01.2016.
 */
public class Title extends AbstractLoader {

    @Override
    public void assign(AbstractBuildingEntity info, JsonValue data) {
        info.setTitle(data.asString());
    }

}
