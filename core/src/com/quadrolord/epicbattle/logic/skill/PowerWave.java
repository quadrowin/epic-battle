package com.quadrolord.epicbattle.logic.skill;

import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.tower.Tower;

import java.util.Iterator;

/**
 * Силовая волна.
 * Отбрасывает врагов назад.
 */
public class PowerWave extends AbstractSkill {

    private Tower mTower;

    public String getIcon() {
        return "icons/skills/wave.png";
    }

    /**
     * Инициализация башни в начале уровня
     * @param tower
     */
    @Override
    public void initTower(Tower tower) {
        mTower = tower;
        tower.setActiveSkill(this);
    }

    /**
     *
     */
    @Override
    public void use() {
        for (Iterator<AbstractBullet> blt = mTower.getEnemy().getUnits().iterator(); blt.hasNext(); ) {
            mTower.getGame().foldBackUnit(blt.next());
        }
    }

}
