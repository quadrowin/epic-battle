package com.quadrolord.epicbattle.logic.town.building;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.quadrolord.epicbattle.logic.town.building.leveling.AbstractStrategy;
import com.quadrolord.epicbattle.logic.town.building.loader.AbstractLoader;
import com.quadrolord.epicbattle.logic.town.building.loader.ConstructionTime;
import com.quadrolord.epicbattle.logic.town.building.loader.GemCost;
import com.quadrolord.epicbattle.logic.town.building.loader.Icon;
import com.quadrolord.epicbattle.logic.town.building.loader.LevelUps;
import com.quadrolord.epicbattle.logic.town.building.loader.RequiredLevel;
import com.quadrolord.epicbattle.logic.town.building.loader.RequiredResources;
import com.quadrolord.epicbattle.logic.town.building.loader.Size;
import com.quadrolord.epicbattle.logic.town.resource.Resource;
import com.quadrolord.epicbattle.logic.town.tile.Tile;
import com.quadrolord.epicbattle.view.town.building.BuildingView;

/**
 * Created by morph on 17.01.2016.
 */
public class BuildingInfo {
    protected String mTitle;
    protected Vector2 mSize;
    protected Class<? extends BuildingView> mViewClass;
    protected String mIcon;
    protected Class<? extends Tile> mTileClass;
    protected ArrayMap<Class<? extends Resource>, Integer> mRequiredResources = new ArrayMap<Class<? extends Resource>, Integer>();
    protected int mRequiredLevel = 1;

    protected int mCostGem;
    protected float mConstructionTime;
    protected AbstractStrategy mLevelingStrategy;
    protected Array<BuildingInfo> mLevelUps = new Array<BuildingInfo>();

    protected Class<? extends AbstractBuilding> mBuildingClass;

    public BuildingInfo() {

    }

    public String getTitle() {
        return mTitle;
    }

    public Array<BuildingInfo> getLevelUps() {
        return mLevelUps;
    }

    public float getConstructionTime() {
        return mConstructionTime;
    }

    public Class<? extends Tile> getTileClass() {
        return mTileClass;
    }

    public String getIcon() {
        return mIcon;
    }

    public Class<? extends AbstractBuilding> getBuildingClass() {
        return mBuildingClass;
    }

    public Class<? extends BuildingView> getViewClass() {
        return mViewClass;
    }

    public Vector2 getSize() {
        return mSize;
    }

    public ArrayMap<Class<? extends Resource>, Integer> getRequiredResources() {
        return mRequiredResources;
    }

    public int getRequiredLevel() {
        return mRequiredLevel;
    }

    public int getCostGem() {
        return mCostGem;
    }

    public AbstractStrategy getLevelingStrategy() {
        return mLevelingStrategy;
    }

    public BuildingInfo setConstructionTime(float $time) {
        mConstructionTime = $time;
        return this;
    }

    public BuildingInfo setTileClass(Class<? extends Tile> tileClass) {
        mTileClass = tileClass;
        return this;
    }

    public BuildingInfo setIcon(String icon)  {
        mIcon = icon;
        return this;
    }

    public BuildingInfo setViewClass(Class<? extends BuildingView> viewClass) {
        mViewClass = viewClass;
        return this;
    }

    public BuildingInfo setLevelUps(Array<BuildingInfo> levelUps) {
        mLevelUps = levelUps;
        return this;
    }

    public BuildingInfo setSize(Vector2 size) {
        mSize = size;
        return this;
    }

    public BuildingInfo setRequiredResources(ArrayMap<Class<? extends Resource>, Integer> resources) {
        mRequiredResources = resources;
        return this;
    }

    public BuildingInfo setRequiredLevel(int level) {
        mRequiredLevel = level;
        return this;
    }

    public BuildingInfo setCostGem(int cost) {
        mCostGem = cost;
        return this;
    }

    public BuildingInfo setLevelingStrategy(AbstractStrategy strategy) {
        mLevelingStrategy = strategy;
        return this;
    }

    public BuildingInfo setTitle(String title) {
        mTitle = title;
        return this;
    }

    public void setLevel(int level) {
        getLevelingStrategy().setLevel(this, level);
    }

    public BuildingInfo setBuildingClass(Class<? extends AbstractBuilding> buildingClass) {
        this.mBuildingClass = buildingClass;
        return this;
    }

    public ArrayMap<String, AbstractLoader> getJsonLoaders() {
        ArrayMap<String, AbstractLoader> loaders = new ArrayMap<String, AbstractLoader>();

        loaders.put("construction_time", new ConstructionTime());
        loaders.put("cost_gem", new GemCost());
        loaders.put("icon", new Icon());
        loaders.put("required_level", new RequiredLevel());
        loaders.put("size", new Size());
        loaders.put("level_ups", new LevelUps());
        loaders.put("required_resources", new RequiredResources());

        return loaders;
    }
}
