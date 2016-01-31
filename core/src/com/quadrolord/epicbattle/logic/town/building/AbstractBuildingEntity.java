package com.quadrolord.epicbattle.logic.town.building;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.quadrolord.epicbattle.logic.configurable.AbstractEntity;
import com.quadrolord.epicbattle.logic.town.building.leveling.AbstractStrategy;
import com.quadrolord.epicbattle.logic.town.resource.Resource;
import com.quadrolord.epicbattle.logic.town.tile.Tile;
import com.quadrolord.epicbattle.view.town.building.AbstractBuildingView;

/**
 * Created by morph on 17.01.2016.
 */
abstract public class AbstractBuildingEntity<T extends AbstractBuildingItem> extends AbstractEntity<T> {
    protected String mTitle;
    protected Vector2 mSize = new Vector2(1, 1);
    protected Class<? extends AbstractBuildingView> mViewClass;
    protected String mIcon;
    protected Class<? extends Tile> mTileClass;
    protected ArrayMap<Class<? extends Resource>, Integer> mRequiredResources = new ArrayMap<Class<? extends Resource>, Integer>();
    protected int mRequiredLevel = 1;

    protected int mCostGem;
    protected float mConstructionTime;
    protected AbstractStrategy mLevelingStrategy;
    protected Array<AbstractBuildingEntity> mLevelUps = new Array<AbstractBuildingEntity>();

    public String getTitle() {
        return mTitle;
    }

    public Array<AbstractBuildingEntity> getLevelUps() {
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

    public Class<? extends AbstractBuildingView> getViewClass() {
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

    public void runOnSelect(AbstractBuildingItem item) {

    }

    public AbstractBuildingEntity setConstructionTime(float $time) {
        mConstructionTime = $time;
        return this;
    }

    public AbstractBuildingEntity setTileClass(Class<? extends Tile> tileClass) {
        mTileClass = tileClass;
        return this;
    }

    public AbstractBuildingEntity setIcon(String icon)  {
        mIcon = icon;
        return this;
    }

    public AbstractBuildingEntity setViewClass(Class<? extends AbstractBuildingView> viewClass) {
        mViewClass = viewClass;
        return this;
    }

    public AbstractBuildingEntity setLevelUps(Array<AbstractBuildingEntity> levelUps) {
        mLevelUps = levelUps;
        return this;
    }

    public AbstractBuildingEntity setSize(Vector2 size) {
        mSize = size;
        return this;
    }

    public AbstractBuildingEntity setRequiredResources(ArrayMap<Class<? extends Resource>, Integer> resources) {
        mRequiredResources = resources;
        return this;
    }

    public AbstractBuildingEntity setRequiredLevel(int level) {
        mRequiredLevel = level;
        return this;
    }

    public AbstractBuildingEntity setCostGem(int cost) {
        mCostGem = cost;
        return this;
    }

    public AbstractBuildingEntity setLevelingStrategy(AbstractStrategy strategy) {
        mLevelingStrategy = strategy;
        return this;
    }

    public AbstractBuildingEntity setTitle(String title) {
        mTitle = title;
        return this;
    }

    public void setLevel(int level) {
        getLevelingStrategy().setLevel(this, level);
    }

}
