package com.quadrolord.epicbattle.logic;

import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.tower.*;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public interface GameListener {

    void beforeStageClear();

    void onBulletAttack(AbstractBullet attacker, com.quadrolord.epicbattle.logic.tower.GameUnit target);

    void onBulletInjure(AbstractBullet target);

    void onBulletCreate(AbstractBullet bullet);

    void onBulletCreateFailCash(float current, int required);

    void onBulletCreateFailCooldown();

    void onBulletRemove(AbstractBullet bullet);

    void onLevelDefeat();

    void onLevelStart();

    void onLevelVictory();

    void onTowerCreate(Tower tower);

    void onTowerInjure(Tower tower);

    void onTowerDeath(Tower tower);

    /**
     * Вызов визуального эффекта
     * @param x
     * @param y
     * @param visualEventClass
     */
    void onVisualEvent(float x, float y, Class visualEventClass);

}
