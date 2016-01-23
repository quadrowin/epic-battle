package com.quadrolord.epicbattle.logic.town;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuilding;
import com.quadrolord.epicbattle.logic.town.building.BuildingInfo;
import com.quadrolord.epicbattle.logic.town.building.ResourceAbstractBuilding;
import com.quadrolord.epicbattle.logic.town.resource.Resource;
import com.quadrolord.epicbattle.logic.town.tile.Tile;

import java.util.Iterator;

/**
 * Created by Quadrowin on 16.01.2016.
 */
public class MyTown {

    private Game mGame;

    private Array<AbstractBuilding> mBuildings = new Array<AbstractBuilding>();
    private Tile[][] mMap = new Tile[][]{};

    private int mLevel = 1;
    private int mGemsCount = 1;

    private ArrayMap<Class<? extends Resource>, Float> mResourceCount = new ArrayMap<Class<? extends Resource>, Float>();
    private BuildingInfoManager mBuildingInfoManager;

    private float mTime = 0;

    public MyTown(Game game) {
        mGame = game;
        mBuildingInfoManager = new BuildingInfoManager();

        mBuildings.add(new AbstractBuilding(this) {

        });
    }

    public void act(float delta) {
        mTime += delta;

        yieldResources(delta);
    }

    public Array<AbstractBuilding> getBuildings() {
        return mBuildings;
    }

    public float getYieldDelta(ResourceAbstractBuilding building) {
        return Math.max(0, building.getInfo().getYieldTime() - building.getLastYield());
    }

    public BuildingInfoManager getBuildingInfoManager() {
        return mBuildingInfoManager;
    }

    public void yieldResources(float delta) {
        for (AbstractBuilding building : mBuildings) {
            if (!(building instanceof ResourceAbstractBuilding)) {
                continue;
            }

            ResourceAbstractBuilding resourceBuilding = (ResourceAbstractBuilding)building;

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

    public boolean takeResources(ResourceAbstractBuilding building) {
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

    public boolean hasResources(AbstractBuilding building) {
        Iterator<ObjectMap.Entry<Class<? extends Resource>, Integer>> iter = building.getInfo().getRequiredResources().iterator();

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

    public boolean canBuild(AbstractBuilding building, int col, int row) {
        for (int i = col; i < col + building.getWidth(); i++) {
            for (int j = row; j < row + building.getHeight(); j++) {
                if (mMap[i] == null || mMap[i][j] == null || !mMap[i][j].isFree()) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean build(AbstractBuilding building, int col, int row, boolean isRorated, boolean isByGems) {
        if (isRorated && !building.isRotated()) {
            building.rotate();
        }

        boolean hasResources = isByGems ? hasGems(building.getInfo().getCostGem()) : hasResources(building);

        if (hasResources && hasLevel(building.getInfo().getRequiredLevel()) && canBuild(building, col, row)) {
            building.setX(col);
            building.setY(row);

            mBuildings.add(building);
            takeAwayResources(building);

            for (int i = col; i < col + building.getWidth(); i++) {
                for (int j = row; j < row + building.getHeight(); j++) {
                    mMap[i][j].markAsBusy();
                }
            }

            return true;
        }

        return false;
    }

    public void takeAwayResources(AbstractBuilding building) {
        Iterator<ObjectMap.Entry<Class<? extends Resource>, Integer>> iter = building.getInfo().getRequiredResources().iterator();

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

    public void levelUp(AbstractBuilding building, boolean isByGems) {
        BuildingInfo info = building.getInfo();
        boolean hasResources = isByGems ? hasGems(info.getCostGem()) : hasResources(building);

        if (hasResources && hasLevel(info.getRequiredLevel()) && building.canLevelUp()) {
            takeAwayResources(building);
            building.levelUp();
        }
    }

    public void demolish(AbstractBuilding building) {
        mBuildings.removeValue(building, true);
    }

    public void setLevel(int level) {
        mLevel = level;
    }

}
