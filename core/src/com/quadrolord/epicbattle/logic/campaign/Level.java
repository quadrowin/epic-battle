package com.quadrolord.epicbattle.logic.campaign;

/**
 * Created by Quadrowin on 11.01.2016.
 */
public class Level {

    private String name;

    private EnemyTower enemyTower;

    public EnemyTower getEnemyTower() {
        return enemyTower;
    }

    public String getName() {
        return name;
    }

    public void setName(String val) {
        name = val;
    }

}
