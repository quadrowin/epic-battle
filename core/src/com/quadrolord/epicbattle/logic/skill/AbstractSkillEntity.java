package com.quadrolord.epicbattle.logic.skill;

import com.quadrolord.ejge.entity.AbstractEntity;
import com.quadrolord.epicbattle.logic.tower.BattleGame;
import com.quadrolord.epicbattle.logic.tower.Tower;

/**
 * Общая логика и описание скила.
 * Created by Quadrowin on 14.01.2016.
 */
abstract public class AbstractSkillEntity extends AbstractEntity<SkillItem> {

    private boolean mIsPassive = false;
    private boolean mIsActive = false;
    private boolean mIsSummon = false;

    private String mDescription;
    private String mIcon;
    private String mName;
    private int mBaseUpgradingCost = 100;

    public int getBaseUpgradingCost() {
        return mBaseUpgradingCost;
    }

    public float getCooldownLength() {
        return 0;
    }

    public int getSkillCost() {
        return 0;
    }

    public String getDescription() {
        return mDescription;
    }


    /**
     * Иконка (для активных скилов)
     * @return Путь до файла с иконкой
     */
    public String getIcon() {
        return mIcon;
    }

    public String getName() {
        return mName;
    }

    @Override
    public Class<? extends SkillItem> getItemClass() {
        return SkillItem.class;
    }

    @Override
    public void initItem(SkillItem item) {

    }

    public void initSkill(BattleGame game)
    {

    }

    public boolean isSummon() {
        return mIsSummon;
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

    public void setBaseUpgradingCost(int cost) {
        mBaseUpgradingCost = cost;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public void setName(String name) {
        mName = name;
    }

    protected void setIsActive(boolean active) {
        mIsActive = active;
    }

    protected void setIsPassive(boolean passive) {
        mIsPassive = passive;
    }

    protected void setIsSummon(boolean summon) {
        mIsSummon = summon;
    }

}
