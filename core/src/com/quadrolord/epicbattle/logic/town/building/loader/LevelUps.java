package com.quadrolord.epicbattle.logic.town.building.loader;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.JsonValue;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingEntity;

/**
 * Created by morph on 24.01.2016.
 */
public class LevelUps extends AbstractLoader {
    @Override
    public void assign(AbstractBuildingEntity info, JsonValue data) {
        Array<AbstractBuildingEntity> levelUps = new Array<AbstractBuildingEntity>();
        JsonValue levelingJson = data;
        ArrayMap<String, AbstractLoader> loaders = info.getJsonLoaders();

        for (JsonValue levelJson : levelingJson.iterator()) {
            AbstractBuildingEntity newInfo = null;

            try {
                newInfo = info.getClass().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (JsonValue.JsonIterator it = levelJson.iterator(); it.hasNext(); ) {
                JsonValue val = it.next();
                String name = val.name();

                if (loaders.containsKey(name)) {
                    loaders.get(name).assign(info, val);
                }
            }

            levelUps.add(newInfo);
        }
    }
}
