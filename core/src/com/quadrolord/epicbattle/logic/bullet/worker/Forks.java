package com.quadrolord.epicbattle.logic.bullet.worker;

import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.logic.bullet.leveling.SimpleStrategy;

/**
 * Юнит раздваивается на 2 при смерти
 */
public class Forks extends AbstractBullet {

    private int mStage = 1;

    public Forks(Game game) {
        super(game);
    }

    @Override
    public void onDeath() {
        if (mStage < 2) {
            for (int i = 0; i < 2; i++) {
                Forks child = (Forks)mGame.createUnitEx(mTower, mInfo);
                child.setStage(mStage + 1);
                child.setX(getX() - getVelocity() * 1);
            }
        }

        super.onDeath();
    }

    public void setStage(int stage) {
        mStage = stage;
    }


    @Override
    public void initInfo() {
        super.initInfo();

        mInfo.setViewClass(com.quadrolord.epicbattle.view.worker.Forks.class);
        mInfo.setLevelingStrategy(new SimpleStrategy());
    }

}
