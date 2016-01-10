package com.quadrolord.epicbattle.logic.ai;

import com.quadrolord.epicbattle.logic.Tower;
import com.quadrolord.epicbattle.logic.bullet.worker.Simple;

/**
 * Created by Quadrowin on 10.01.2016.
 */
public class TowerAi {

    private float mTime = 0;

    private Tower mTower;

    public TowerAi(Tower tower) {
        mTower = tower;
    }

    public void act(float delta) {
        int frame1 = (int)(mTime / 3);
        mTime += delta;
        int frame2 = (int)(mTime / 3);
        if (frame2 > frame1) {
            mTower.getGame().createUnit(mTower, Simple.class);
        }
    }

}
