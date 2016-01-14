package com.quadrolord.epicbattle.logic;

import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public interface GameListener {

    void beforeStageClear();

    void onBulletAttack(AbstractBullet attacker, GameUnit target);

    void onBulletCreate(AbstractBullet bullet);

    void onBulletCreateFailCash(float current, int required);

    void onBulletCreateFailCooldown();

    void onBulletRemove(AbstractBullet bullet);

    void onLevelDefeat();

    void onLevelVictory();

    void onTowerCreate(com.quadrolord.epicbattle.logic.tower.Tower tower);

    void onTowerDeath(com.quadrolord.epicbattle.logic.tower.Tower tower);

    /**
     * Вызов визуального эффекта
     * @param x
     * @param y
     * @param visualEventClass
     */
    void onVisualEvent(float x, float y, Class visualEventClass);

}
