package com.quadrolord.epicbattle.logic.town.building;

import com.badlogic.gdx.utils.ArrayMap;
import com.quadrolord.epicbattle.logic.town.building.leveling.ResourceBuildingStrategy;
import com.quadrolord.epicbattle.logic.town.building.loader.AbstractLoader;
import com.quadrolord.epicbattle.logic.town.building.loader.YieldTime;

/**
 * Created by morph on 24.01.2016.
 */
abstract public class ResourceBuildingEntity extends AbstractBuildingEntity<ResourceBuildingItem> {

    protected float mYieldTime;

    @Override
    public Class<? extends ResourceBuildingItem> getItemClass() {
        return ResourceBuildingItem.class;
    }

    public float getYieldTime() {
        return mYieldTime;
    }

    public ResourceBuildingEntity() {
        setLevelingStrategy(new ResourceBuildingStrategy());
    }

    public AbstractBuildingEntity setYieldTime(float time) {
        mYieldTime = time;
        return this;
    }

    @Override
    public ArrayMap<String, AbstractLoader> getJsonLoaders() {
        ArrayMap<String, AbstractLoader> loaders = super.getJsonLoaders();

        loaders.put("yield_time", new YieldTime());

        return loaders;
    }
}
