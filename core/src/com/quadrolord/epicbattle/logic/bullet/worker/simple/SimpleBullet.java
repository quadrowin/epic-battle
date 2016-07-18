package com.quadrolord.epicbattle.logic.bullet.worker.simple;

import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.tower.BattleGame;

/**
 * Created by Quadrowin on 02.07.2016.
 */
public class SimpleBullet extends AbstractBullet {

    public SimpleBullet(BattleGame game) {
        super(game);
        setHeight(50);
        setWidth(39);
    }

}
