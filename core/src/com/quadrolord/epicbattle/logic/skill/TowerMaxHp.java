package com.quadrolord.epicbattle.logic.skill;

import com.quadrolord.epicbattle.logic.tower.Tower;

/**
 * Created by Quadrowin on 14.01.2016.
 */
public class TowerMaxHp extends AbstractSkill {

    /**
     * Инициализация башни в начале уровня
     * @param tower
     */
    @Override
    public void initTower(Tower tower) {
        tower.setMaxHp((getLevel() + 5) * 100);
    }

}
