package com.quadrolord.epicbattle.logic.town;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.logic.town.building.Building;
import com.quadrolord.epicbattle.logic.town.resource.Resource;
import com.quadrolord.epicbattle.logic.town.tile.Tile;

import java.util.Iterator;

/**
 * Created by Quadrowin on 16.01.2016.
 */
public class MyTown {

    private Game mGame;

    private Array<Building> mBuildings = new Array<Building>();
    private ArrayMap<Resource, Integer> mResources = new ArrayMap<Resource, Integer>();
    private Tile[][] mMap = new Tile[][]{};
    private int mLevel = 1;

    public MyTown(Game game) {
        mGame = game;
    }

    public boolean haveLevel(int level) {
        return mLevel >= level;
    }

    public boolean haveResources(Building building) {
        Iterator<ObjectMap.Entry<Resource, Integer>> iter = building.getInfo().getResources().iterator();

        while (iter.hasNext()) {
            ObjectMap.Entry<Resource, Integer> next = iter.next();
            Resource resource = next.key;
            int cost = next.value;

            if (!mResources.containsKey(resource) || mResources.get(resource) < cost) {
                return false;
            }
        }

        return true;
    }

    public boolean canBuild(Building building, int col, int row) {
        for (int i = col; i < col + building.getWidth(); i++) {
            for (int j = row; j < row + building.getHeight(); j++) {
                if (mMap[i] == null || mMap[i][j] == null || !mMap[i][j].isFree()) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean build(Building building, int col, int row, boolean isRorated) {
        if (isRorated && !building.isRotated()) {
            building.rotate();
        }

        if (haveLevel(building.getInfo().getRequiredLevel()) && canBuild(building, col, row) && haveResources(building)) {
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

    public void takeAwayResources(Building building) {
        Iterator<ObjectMap.Entry<Resource, Integer>> iter = building.getInfo().getResources().iterator();

        while (iter.hasNext()) {
            ObjectMap.Entry<Resource, Integer> next = iter.next();
            Resource resource = next.key;
            int cost = next.value;

            if (mResources.containsKey(resource)) {
                int oldValue = mResources.get(resource);
                mResources.put(resource, oldValue - cost);
            }
        }
    }

    public void demolish(Building building) {
        mBuildings.removeValue(building, true);
    }

    public void setLevel(int level) {
        mLevel = level;
    }

}
