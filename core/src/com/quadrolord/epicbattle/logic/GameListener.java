package com.quadrolord.epicbattle.logic;

import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public interface GameListener {

    void onBulletAttack(AbstractBullet attacker, GameUnit target);

    void onBulletCreate(AbstractBullet bullet);

    void onBulletCreateFailCash(float current, int required);

    void onBulletCreateFailCooldown();

    void onBulletRemove(AbstractBullet bullet);

    void onLevelDefeat();

    void onLevelVictory();

    void onTowerCreate(Tower tower);

    void onTowerDeath(Tower tower);

}
