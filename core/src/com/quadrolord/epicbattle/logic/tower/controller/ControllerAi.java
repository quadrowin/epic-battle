package com.quadrolord.epicbattle.logic.tower.controller;

import com.quadrolord.epicbattle.logic.bullet.worker.Simple;
import com.quadrolord.epicbattle.logic.campaign.EnemyTower;
import com.quadrolord.epicbattle.logic.tower.BattleGame;

/**
 * Created by Quadrowin on 12.01.2016.
 */
public class ControllerAi extends AbstractController {

    private EnemyTower mEnemyParams;

    private float mTime = 0;

    public ControllerAi(BattleGame game) {
        super(game);
    }

    @Override
    public void act(float delta) {
        int frame1 = (int)(mTime / 3);
        mTime += delta;
        int frame2 = (int)(mTime / 3);
        if (frame2 > frame1) {
            getGame().createUnit(getTower(), Simple.class);
        }
    }

    @Override
    public void reset() {
        mTime = 0;
    }

    public void setEnemyParams(EnemyTower params) {
        mEnemyParams = params;
    }

}
