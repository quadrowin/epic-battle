package com.quadrolord.epicbattle.logic.town;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuilding;
import com.quadrolord.epicbattle.logic.town.building.BuildingInfo;
import com.quadrolord.epicbattle.logic.town.building.loader.AbstractLoader;

/**
 * Created by morph on 23.01.2016.
 */
public class BuildingInfoManager {
    private JsonReader mReader;
    private ArrayMap<Class<? extends BuildingInfo>, ArrayMap<String, AbstractLoader>> mLoaders;

    public BuildingInfoManager() {
        mReader = new JsonReader();
        mLoaders = new ArrayMap<Class<? extends BuildingInfo>, ArrayMap<String, AbstractLoader>>();
    }

    public BuildingInfo getBuildingInfo(Class<? extends AbstractBuilding> buildingClass, Class<? extends BuildingInfo> infoClass) {
        BuildingInfo info;

        try {
            info = infoClass.newInstance();
        } catch (Exception e) {
            info = new BuildingInfo();
        }

        if (!mLoaders.containsKey(info.getClass())) {
            mLoaders.put(info.getClass(), info.getJsonLoaders());
        }

        ArrayMap<String, AbstractLoader> loaders = mLoaders.get(info.getClass());

        String fileName = "config/buildings/" + buildingClass.getSimpleName() + ".json";
        info.setBuildingClass(buildingClass);
        JsonValue json;

        Gdx.app.log("buildings", "Loaded building config: " + fileName);

        try {
            json = mReader.parse(Gdx.files.internal(fileName));
        } catch (Exception e) {
            throw new RuntimeException("Cannot found config file: " + fileName);
        }

        if (!json.has("title")) {
            throw new RuntimeException("Empty building name");
        }

        for (JsonValue.JsonIterator it = json.iterator(); it.hasNext(); ) {
            JsonValue val = it.next();
            String name = val.name();
            if (loaders.containsKey(name)) {
                loaders.get(name).assign(info, val);
            } else {
                Gdx.app.log(BuildingInfoManager.class.getName(), "Unknown param in " + buildingClass.getName() + ": " + name);
            }
        }

        return info;
    }
}
