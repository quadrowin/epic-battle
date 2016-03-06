package com.quadrolord.epicbattle.logic.skill.active;

import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.skill.AbstractSkillEntity;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.logic.tower.Tower;

import java.util.Iterator;

/**
 * Силовая волна.
 * Отбрасывает врагов назад.
 */
public class PowerWave extends AbstractSkillEntity {

    public String getIcon() {
        return "icons/skills/wave.png";
    }

    /**
     * Инициализация башни в начале уровня
     * @param tower
     */
    @Override
    public void initTower(SkillItem skill, Tower tower) {
        skill.setTower(tower);
        tower.setActiveSkill(skill);
    }

    /**
     *
     */
    @Override
    public void use(SkillItem skill) {
        Tower tower = skill.getTower();
        for (Iterator<AbstractBullet> blt = tower.getEnemy().getUnits().iterator(); blt.hasNext(); ) {
            tower.getGame().foldBackUnit(blt.next());
        }
    }

}
