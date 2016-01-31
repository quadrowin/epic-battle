package com.quadrolord.epicbattle.logic.town;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingEntity;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.logic.town.building.ResourceBuildingItem;
import com.quadrolord.epicbattle.logic.town.resource.Resource;
import com.quadrolord.epicbattle.logic.town.tile.Tile;

import java.util.Iterator;

/**
 * Created by Quadrowin on 16.01.2016.
 */
public class MyTown {

    private Game mGame;

    private Array<AbstractBuildingItem> mBuildings = new Array<AbstractBuildingItem>();
    private Tile[][] mMap = new Tile[][]{};

    private int mLevel = 1;
    private int mGemsCount = 1;

    private ArrayMap<Class<? extends Resource>, Float> mResourceCount = new ArrayMap<Class<? extends Resource>, Float>();
    private BuildingInfoManager mBuildingInfoManager;

    private TownListener mListener;

    private float mTime = 0;

    public MyTown(Game game) {
        mGame = game;
        mBuildingInfoManager = new BuildingInfoManager();
    }

    public void act(float delta) {
        mTime += delta;

        yieldResources(delta);
    }

    public Array<AbstractBuildingItem> getBuildings() {
        return mBuildings;
    }

    public float getYieldDelta(ResourceBuildingItem building) {
        return Math.max(0, building.getInfo().getYieldTime() - building.getLastYield());
    }

    public BuildingInfoManager getBuildingInfoManager() {
        return mBuildingInfoManager;
    }

    public void yieldResources(float delta) {
        for (AbstractBuildingItem building : mBuildings) {
            if (!(building instanceof ResourceBuildingItem)) {
                continue;
            }

            ResourceBuildingItem resourceBuilding = (ResourceBuildingItem)building;

            if (getYieldDelta(resourceBuilding) < 0.00001f) {
                float count = resourceBuilding.getYieldCount();
                float yieldTime = resourceBuilding.getInfo().getYieldTime();

                if (delta < yieldTime) {
                    count += 1;
                } else {
                    count += delta / yieldTime;
                }

                resourceBuilding.setLastYield(0.0f);
                resourceBuilding.setYieldCount(count);
            } else {
                resourceBuilding.setLastYield(resourceBuilding.getLastYield() + delta);
            }
        }
    }

    public boolean takeResources(ResourceBuildingItem building) {
        if (building.getYieldCount() >= 1) {
            float count = 0;
            Class<? extends Resource> resource = building.getResourceClass();

            if (mResourceCount.containsKey(resource)) {
                count = mResourceCount.get(resource);
            }

            count += building.getYieldCount();

            building.setYieldCount(0.0f);
            mResourceCount.put(resource, count);

            return true;
        }

        return false;
    }

    public boolean hasGems(int gemsCount) {
        return mGemsCount >= gemsCount;
    }

    public boolean hasLevel(int level) {
        return mLevel >= level;
    }

    public boolean hasResources(AbstractBuildingEntity building) {
        Iterator<ObjectMap.Entry<Class<? extends Resource>, Integer>> iter = building.getRequiredResources().iterator();

        while (iter.hasNext()) {
            ObjectMap.Entry<Class<? extends Resource>, Integer> next = iter.next();
            Class<? extends Resource> resourceClass = next.key;
            int cost = next.value;

            if (!mResourceCount.containsKey(resourceClass) || mResourceCount.get(resourceClass) < cost) {
                return false;
            }
        }

        return true;
    }

    public boolean canBuild(AbstractBuildingEntity entity, int col, int row) {
        for (int i = col; i < col + entity.getSize().x; i++) {
            for (int j = row; j < row + entity.getSize().y; j++) {
                if (
                        i >= mMap.length || j >= mMap[i].length
                        || mMap[i] == null || mMap[i][j] == null
                        || !mMap[i][j].isFree()
                ) {
                    return false;
                }
            }
        }

        return true;
    }

    public AbstractBuildingItem build(Class<? extends AbstractBuildingEntity> entityClass, int col, int row, boolean isRotated, boolean isByGems) {
        return build(mBuildingInfoManager.getInfo(entityClass), col, row, isRotated, isByGems);
    }

    public AbstractBuildingItem build(AbstractBuildingEntity entity, int col, int row, boolean isRotated, boolean isByGems) {
        boolean hasResources = isByGems ? hasGems(entity.getCostGem()) : hasResources(entity);
        if (!hasResources) {
            mListener.onUserActionFail(TownListener.BuildingAction.CREATE_NO_RESOURCES);
            return null;
        }

        if (!hasLevel(entity.getRequiredLevel())) {
            mListener.onUserActionFail(TownListener.BuildingAction.CREATE_NO_LEVEL);
            return null;
        }

        if (!canBuild(entity, col, row)) {
            mListener.onUserActionFail(TownListener.BuildingAction.CREATE_NO_PLACE);
//            return null;
        }

        takeAwayResources(entity);

        AbstractBuildingItem item;
        try {
            item = (AbstractBuildingItem)entity.getItemClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        item.setInfo(entity);
        entity.initItem(item);
        item.setX(col);
        item.setY(row);

        if (isRotated && !item.isRotated()) {
            item.rotate();
        }

        mBuildings.add(item);

        for (int i = col; i < col + item.getWidth(); i++) {
            for (int j = row; j < row + item.getHeight(); j++) {
//                mMap[i][j].markAsBusy();
            }
        }

        mListener.onBuildingAdd(item);
        return item;
    }

    public void setListener(TownListener listener) {
        mListener = listener;
    }

    /**
     * Забор ресурсов за постройку
     * @param entity
     */
    public void takeAwayResources(AbstractBuildingEntity entity) {
        Iterator<ObjectMap.Entry<Class<? extends Resource>, Integer>> iter = entity.getRequiredResources().iterator();

        while (iter.hasNext()) {
            ObjectMap.Entry<Class<? extends Resource>, Integer> next = iter.next();
            Class<? extends Resource> resourceClass = next.key;
            int cost = next.value;

            if (mResourceCount.containsKey(resourceClass)) {
                float oldValue = mResourceCount.get(resourceClass);
                mResourceCount.put(resourceClass, oldValue - cost);
            }
        }
    }

    public void levelUp(AbstractBuildingItem building, boolean isByGems) {
        AbstractBuildingEntity info = building.getInfo();
        boolean hasResources = isByGems ? hasGems(info.getCostGem()) : hasResources(info);

        if (!hasResources) {
            mListener.onUserActionFail(TownListener.BuildingAction.LEVEL_NO_RESOURCES);
            return;
        }

        if (building.isInUpdating()) {
            mListener.onUserActionFail(TownListener.BuildingAction.LEVEL_IN_UPDATING);
            return;
        }

        if (!hasLevel(info.getRequiredLevel())) {
            mListener.onUserActionFail(TownListener.BuildingAction.LEVEL_BAD_USER_LEVEL);
            return;
        }

        if (!building.canLevelUp()) {
            mListener.onUserActionFail(TownListener.BuildingAction.LEVEL_ALREADY_MAX);
            return;
        }

        takeAwayResources(info);
        building.levelUp();
        mListener.onBuildingChange(building);
    }

    public void demolish(AbstractBuildingItem building) {
        mBuildings.removeValue(building, true);
        mListener.onBuildingRemove(building);
    }

    public void setLevel(int level) {
        mLevel = level;
    }

    public void setSelected(int col, int row) {
        for (int i = 0; i < mBuildings.size; i++) {
            AbstractBuildingItem b = mBuildings.get(i);
            if (col == b.getX() && row == b.getY()) {
                mListener.onBuildingSelect(b);
                b.getInfo().runOnSelect(b);
            }
        }
    }

}
