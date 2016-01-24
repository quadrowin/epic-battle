package com.quadrolord.epicbattle.logic.town.building.loader;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.JsonValue;
import com.quadrolord.epicbattle.logic.town.building.BuildingInfo;

/**
 * Created by morph on 24.01.2016.
 */
public class LevelUps extends AbstractLoader {
    @Override
    public void assign(BuildingInfo info, JsonValue data) {
        Array<BuildingInfo> levelUps = new Array<BuildingInfo>();
        JsonValue levelingJson = data;
        ArrayMap<String, AbstractLoader> loaders = info.getJsonLoaders();

        for (JsonValue levelJson : levelingJson.iterator()) {
            BuildingInfo newInfo;

            try {
                newInfo = info.getClass().newInstance();
            } catch (Exception e) {
                newInfo = new BuildingInfo();
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
