package com.quadrolord.epicbattle.logic.town.building.loader;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.quadrolord.epicbattle.logic.town.BuildingInfoManager;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingEntity;

/**
 * Created by morph on 24.01.2016.
 */
public class LevelUps extends AbstractLoader {

    private BuildingInfoManager mLoaderManager;

    public LevelUps(BuildingInfoManager manager) {
        mLoaderManager = manager;
    }

    @Override
    public void assign(AbstractBuildingEntity info, JsonValue data) {
        Array<AbstractBuildingEntity> levelUps = new Array<AbstractBuildingEntity>();
        JsonValue levelingJson = data;

        for (JsonValue levelJson : levelingJson.iterator()) {
            AbstractBuildingEntity newInfo = null;

            try {
                newInfo = info.getClass().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            mLoaderManager.loadFromJson(newInfo, levelJson);

            levelUps.add(newInfo);
        }
    }
}
