package com.quadrolord.epicbattle.logic.town;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.logic.town.building.Building;
import com.quadrolord.epicbattle.logic.town.resource.Resource;

/**
 * Created by Quadrowin on 16.01.2016.
 */
public class MyTown {

    private Game mGame;

    private Array<Building> mBuildings = new Array<Building>();
    private ArrayMap<Resource, Integer> mResources = new ArrayMap<Resource, Integer>();

    public MyTown(Game game) {
        mGame = game;
    }



}
