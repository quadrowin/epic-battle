package com.quadrolord.epicbattle.logic.campaign;

import com.badlogic.gdx.utils.Json;

/**
 * Created by Quadrowin on 11.01.2016.
 */
public class FirstCampaign extends AbstractCampaign {

    private String mLevelsJson = "[" +
            "{" +
                "class:Level," +
                "name:First Level," +
                "enemyTower:{" +
                    "class:EnemyTower," +
                    "maxHp:5000," +
                    "x:640," +
                "}" +
            "}," +
            "{" +
                "class: Level," +
                "name:Second Level," +
                "enemyTower:{" +
                    "class: EnemyTower," +
                    "maxHp: 6000," +
                    "x: 300," +
                "}" +
            "}" +
    "]";


    @Override
    public Level[] getLevels() {
        Json json = new Json();
        json.addClassTag("Level", Level.class);
        json.addClassTag("EnemyTower", EnemyTower.class);
        Level[] levels = json.fromJson(Level[].class, mLevelsJson);
        return levels;
    }
}
