package com.quadrolord.epicbattle.logic;

import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public interface GameListener {

    void onBulletCreate(AbstractBullet bullet);

    void onBulletCreateFailCash(float current, int required);

    void onBulletCreateFailCooldown();

    void onBulletRemove(AbstractBullet bullet);

    void onTowerCreate(Tower tower);

}
