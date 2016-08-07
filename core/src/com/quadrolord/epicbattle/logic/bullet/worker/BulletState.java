package com.quadrolord.epicbattle.logic.bullet.worker;

/**
 * Created by Quadrowin on 21.01.2016.
 */
public enum BulletState {

    IDLE,

    WALK,

    RUN,

    /**
     * Подготовка к атаке
     */
    ATTACK_PREPARE,

    /**
     * Промежуток между атаками (для серсии ударов)
     */
    ATTACK_BETWEEN,

    /**
     * Завершение атаки
     */
    ATTACK_FINISH,

    FOLD_BACK,

    DEATH,

}
