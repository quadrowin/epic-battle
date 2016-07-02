package com.quadrolord.epicbattle.logic.bullet.worker.forks;

import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.tower.BattleGame;

/**
 * Created by Quadrowin on 02.07.2016.
 */
public class ForksBullet extends AbstractBullet {

    private int mStage = 1;

    public ForksBullet(BattleGame game) {
        super(game);
    }

    public int getStage() {
        return mStage;
    }

    public void setStage(int stage) {
        mStage = stage;
    }

}
