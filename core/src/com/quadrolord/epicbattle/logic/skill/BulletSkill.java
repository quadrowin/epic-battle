package com.quadrolord.epicbattle.logic.skill;

import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.tower.Tower;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Quadrowin on 25.01.2016.
 */
abstract public class BulletSkill <T extends AbstractBullet> extends AbstractSkill {

    /**
     * Инициализация башни в начале уровня
     * @param tower
     */
    @Override
    public void initTower(Tower tower) {
        ParameterizedType type = (ParameterizedType)getClass().getGenericSuperclass();
        Class parameter = (Class)type.getActualTypeArguments()[0];
        tower.addBulletSkill(parameter, getLevel());
    }

}
