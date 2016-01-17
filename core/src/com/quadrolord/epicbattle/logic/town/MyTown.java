package com.quadrolord.epicbattle.logic.town;

import com.badlogic.gdx.utils.Array;
import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.logic.town.building.Building;

/**
 * Created by Quadrowin on 16.01.2016.
 */
public class MyTown {

    private Game mGame;

    private Array<Building> buildings = new Array<Building>();

    public MyTown(Game game) {
        mGame = game;
    }



}
