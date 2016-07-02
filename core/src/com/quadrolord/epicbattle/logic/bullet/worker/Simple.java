package com.quadrolord.epicbattle.logic.bullet.worker;

import com.quadrolord.epicbattle.logic.bullet.BulletInfo;
import com.quadrolord.epicbattle.logic.bullet.leveling.SimpleStrategy;
import com.quadrolord.epicbattle.logic.tower.BattleGame;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class Simple extends AbstractBullet {

    public Simple(BattleGame game) {
        super(game);
    }

    @Override
    public void initInfo(BulletInfo info) {
        info.setDescription("I will fight for you to the end.");
        info.setViewClass(com.quadrolord.epicbattle.view.worker.Simple.class);
        info.setLevelingStrategy(new SimpleStrategy());
    }
}
