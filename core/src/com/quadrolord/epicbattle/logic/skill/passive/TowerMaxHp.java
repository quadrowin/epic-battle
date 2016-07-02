package com.quadrolord.epicbattle.logic.skill.passive;

import com.quadrolord.epicbattle.logic.skill.AbstractSkillEntity;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.logic.tower.Tower;

/**
 * Created by Quadrowin on 14.01.2016.
 */
public class TowerMaxHp extends AbstractSkillEntity {

    public TowerMaxHp() {
        setDescription("The bigger, the better.");
        setName("Max HP");
        setIcon("icons/skills/wave.png");
    }

    /**
     * Инициализация башни в начале уровня
     * @param tower
     */
    @Override
    public void initTower(SkillItem skill, Tower tower) {
        tower.setMaxHp((skill.getLevel() + 5) * 100);
    }

}
