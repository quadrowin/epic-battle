package com.quadrolord.epicbattle.logic.bullet.worker;

import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.logic.bullet.BulletInfo;
import com.quadrolord.epicbattle.logic.bullet.leveling.SimpleStrategy;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class Simple extends AbstractBullet {

    public Simple(Game game) {
        super(game);
    }

    @Override
    public void initInfo(BulletInfo info) {
        info.setViewClass(com.quadrolord.epicbattle.view.worker.Simple.class);
        info.setLevelingStrategy(new SimpleStrategy());
    }
}
