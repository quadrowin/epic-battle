package com.quadrolord.epicbattle.logic.town;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.logic.town.building.Building;
import com.quadrolord.epicbattle.logic.town.resource.Resource;
import com.quadrolord.epicbattle.logic.town.tile.Tile;

/**
 * Created by Quadrowin on 16.01.2016.
 */
public class MyTown {

    private Game mGame;

    private Array<Building> mBuildings = new Array<Building>();
    private ArrayMap<Resource, Integer> mResources = new ArrayMap<Resource, Integer>();
    private Tile[][] mMap = new Tile[][]{};

    public MyTown(Game game) {
        mGame = game;
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

        if (canBuild(building, col, row)) {
            building.setX(col);
            building.setY(row);

            mBuildings.add(building);

            for (int i = col; i < col + building.getWidth(); i++) {
                for (int j = row; j < row + building.getHeight(); j++) {
                    mMap[i][j].markAsBusy();
                }
            }

            return true;
        }

        return false;
    }

}
