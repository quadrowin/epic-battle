package com.quadrolord.epicbattle.logic.town.building;

import com.quadrolord.epicbattle.logic.town.building.leveling.ResourceBuildingStrategy;

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

}
