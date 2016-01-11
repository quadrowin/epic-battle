package com.quadrolord.epicbattle.logic.bullet.worker;

import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.logic.bullet.BulletInfo;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class Big extends AbstractBullet {

    public Big(Game game) {
        super(game);
    }

    @Override
    public void initInfo(BulletInfo info) {
        info.setInfo(
                "Big",
                200,
                2,
                70,
                2,
                2,
                30,
                2,
                250
        );
    }

}
