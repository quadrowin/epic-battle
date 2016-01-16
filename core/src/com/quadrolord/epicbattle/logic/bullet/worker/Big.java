package com.quadrolord.epicbattle.logic.bullet.worker;

import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.logic.bullet.BulletInfo;
import com.quadrolord.epicbattle.logic.bullet.leveling.SimpleStrategy;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class Big extends AbstractBullet {
    public Big(Game game) {
        super(game);
    }

    @Override
    public void initInfo() {
        super.initInfo();

        mInfo.setViewClass(com.quadrolord.epicbattle.view.worker.Big.class);
        mInfo.setBulletClass(getClass());
        mInfo.setLevelingStrategy(new SimpleStrategy());
    }
}
