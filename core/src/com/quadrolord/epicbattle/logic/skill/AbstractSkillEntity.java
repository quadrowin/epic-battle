package com.quadrolord.epicbattle.logic.skill;

import com.quadrolord.ejge.entity.AbstractEntity;
import com.quadrolord.epicbattle.logic.tower.Tower;

/**
 * Created by Quadrowin on 14.01.2016.
 */
abstract public class AbstractSkillEntity extends AbstractEntity<SkillItem> {

    /**
     * Иконка (для активных скилов)
     * @return Путь до файла с иконкой
     */
    public String getIcon() {
        return null;
    }

    @Override
    public Class<? extends SkillItem> getItemClass() {
        return SkillItem.class;
    }

    @Override
    public void initItem(SkillItem item) {

    }

    public void act(SkillItem skill, float delta) {

    }

    /**
     * Инициализация башни в начале уровня
     * @param tower
     */
    public void initTower(SkillItem skill, Tower tower) {

    }

    /**
     * Использование скила в битве. Только для активных скилов.
     */
    public void use(SkillItem skill) {

    }

}
