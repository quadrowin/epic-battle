package com.quadrolord.epicbattle.logic.tower.controller;

import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.logic.tower.Tower;

/**
 * Created by Quadrowin on 12.01.2016.
 */
abstract public class AbstractController {

    private Game mGame;

    private Tower mTower;

    public AbstractController(Game game) {
        mGame = game;
    }

    abstract public void act(float delta);

    public Game getGame() {
        return mGame;
    }

    public Tower getTower() {
        return mTower;
    }

    abstract public void reset();

    public void setTower(Tower tower) {
        mTower = tower;
    }

}
