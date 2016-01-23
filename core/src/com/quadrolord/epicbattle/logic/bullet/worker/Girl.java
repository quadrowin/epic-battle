package com.quadrolord.epicbattle.logic.bullet.worker;

import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.logic.bullet.BulletInfo;
import com.quadrolord.epicbattle.logic.bullet.leveling.SimpleStrategy;

/**
 * Created by Quadrowin on 20.01.2016.
 */
public class Girl extends AbstractBullet {

    private int mState = 1;

    public Girl(Game game) {
        super(game);
    }


    @Override
    public void initInfo(BulletInfo info) {
        info.setViewClass(com.quadrolord.epicbattle.view.worker.Girl.class);
        info.setLevelingStrategy(new SimpleStrategy());
    }

}
