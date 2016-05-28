package com.quadrolord.epicbattle.logic.town;

import com.badlogic.gdx.utils.ArrayMap;
import com.quadrolord.ejge.entity.EntityManager;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingEntity;
import com.quadrolord.epicbattle.logic.town.building.leveling.SimpleStrategy;
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

    protected ArrayMap<String, AbstractBuildingEntity> mLeveled = new ArrayMap<String, AbstractBuildingEntity>();

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

    public AbstractBuildingEntity getEntityLevel(Class<? extends AbstractBuildingEntity> entityClass, int level) {
        String key = entityClass.getName() + "-" + level;
        if (mLeveled.containsKey(key)) {
            return mLeveled.get(key);
        }
        AbstractBuildingEntity source = getInfo(entityClass);
        AbstractBuildingEntity leveled = source.clone();
        source.getLevelingStrategy().setLevel(leveled, level);
        mLeveled.put(key, leveled);
        return leveled;
    }

    @Override
    public String getConfigDir() {
        return "config/buildings";
    }

    @Override
    protected void initLoaded(AbstractBuildingEntity entity) {
        if (entity.getLevelingStrategy() == null) {
            entity.setLevelingStrategy(new SimpleStrategy());
        }
    }

}
