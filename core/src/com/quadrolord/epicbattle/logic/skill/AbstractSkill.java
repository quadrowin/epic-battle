package com.quadrolord.epicbattle.logic.skill;

import com.quadrolord.epicbattle.logic.tower.Tower;

/**
 * Created by Quadrowin on 14.01.2016.
 */
abstract public class AbstractSkill {

    private int mLevel;

    /**
     * Иконка (для активных скилов)
     * @return Путь до файла с иконкой
     */
    public String getIcon() {
        return null;
    }

    public int getLevel() {
        return mLevel;
    }

    public void act(float delta) {

    }

    /**
     * Инициализация башни в начале уровня
     * @param tower
     */
    public void initTower(Tower tower) {

    }

    public void setLevel(int level) {
        mLevel = level;
    }

    /**
     * Использование скила в битве. Только для активных скилов.
     */
    public void use() {

    }

}
