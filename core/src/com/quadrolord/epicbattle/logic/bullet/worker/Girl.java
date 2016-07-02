package com.quadrolord.epicbattle.logic.bullet.worker;

import com.quadrolord.epicbattle.logic.bullet.BulletInfo;
import com.quadrolord.epicbattle.logic.bullet.leveling.X15Strategy;
import com.quadrolord.epicbattle.logic.tower.BattleGame;

/**
 * Created by Quadrowin on 20.01.2016.
 */
public class Girl extends AbstractBullet {

    private int mState = 1;

    public Girl(BattleGame game) {
        super(game);
    }


    @Override
    public void initInfo(BulletInfo info) {
        info.setDescription("Beauty is a horrible force.");
        info.setViewClass(com.quadrolord.epicbattle.view.worker.Girl.class);
        info.setLevelingStrategy(new X15Strategy());
    }

}
