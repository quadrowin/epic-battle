package com.quadrolord.epicbattle.logic.town;

import com.quadrolord.epicbattle.logic.configurable.EntityManager;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingEntity;
import com.quadrolord.epicbattle.logic.town.building.loader.ConstructionTime;
import com.quadrolord.epicbattle.logic.town.building.loader.GemCost;
import com.quadrolord.epicbattle.logic.town.building.loader.Icon;
import com.quadrolord.epicbattle.logic.town.building.loader.LevelUps;
import com.quadrolord.epicbattle.logic.town.building.loader.RequiredLevel;
import com.quadrolord.epicbattle.logic.town.building.loader.RequiredResources;
import com.quadrolord.epicbattle.logic.town.building.loader.Resources;
import com.quadrolord.epicbattle.logic.town.building.loader.Size;
import com.quadrolord.epicbattle.logic.town.building.loader.SliderTexture;
import com.quadrolord.epicbattle.logic.town.building.loader.Title;

/**
 * Created by morph on 23.01.2016.
 */
public class BuildingInfoManager extends EntityManager<AbstractBuildingEntity> {

    public BuildingInfoManager() {
        super(AbstractBuildingEntity.class);

        mLoaders.put("construction_time", new ConstructionTime());
        mLoaders.put("cost_gem", new GemCost());
        mLoaders.put("icon", new Icon());
        mLoaders.put("level_ups", new LevelUps(this));
        mLoaders.put("required_level", new RequiredLevel());
        mLoaders.put("required_resources", new RequiredResources());
        mLoaders.put("resources", new Resources());
        mLoaders.put("size", new Size());
        mLoaders.put("slider_texture", new SliderTexture());
        mLoaders.put("title", new Title());
    }

    @Override
    public String getConfigDir() {
        return "config/buildings";
    }
}
