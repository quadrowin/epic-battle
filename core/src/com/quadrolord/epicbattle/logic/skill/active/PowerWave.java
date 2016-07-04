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

    public PowerWave() {
        setDescription("Knock back all your enemies.");
        setName("Power wave");
        setIcon("icons/skills/wave.png");
        setIsActive(true);
    }

    public float getCooldownLength() {
        return 10;
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
