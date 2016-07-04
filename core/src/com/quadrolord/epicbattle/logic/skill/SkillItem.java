package com.quadrolord.epicbattle.logic.skill;

import com.quadrolord.ejge.entity.AbstractItem;
import com.quadrolord.epicbattle.logic.tower.Tower;

/**
 * Состояние скила на поле боя
 * Created by Quadrowin on 06.03.2016.
 */
public class SkillItem extends AbstractItem<AbstractSkillEntity> {

    /**
     * Время, когда закончится кулдаун.
     */
    private float mCooldownFinish;

    /**
     * Полная продолжительность текущего кулдауна
     */
    private float mCooldownLength;

    private int mLevel;

    private float mTime;

    /**
     * Владелец скила
     */
    private Tower mTower;

    public float getCooldownFinish() {
        return mCooldownFinish;
    }
    public float getCooldownLength() {
        return mCooldownLength;
    }

    public int getCost() {
        return getInfo().getSkillCost();
    }

    public int getLevel() {
        return mLevel;
    }

    public float getTime() {
        return mTime;
    }

    public Tower getTower() {
        return mTower;
    }

    public boolean isInCooldown() {
        return mCooldownFinish > 0;
    }

    public void resetCooldown() {
        mCooldownFinish = 0;
        mCooldownLength = 0;
    }

    public void setCooldown(float currentTime, float cooldownLength) {
        mCooldownFinish = currentTime + cooldownLength;
        mCooldownLength = cooldownLength;
    }

    public void setLevel(int level) {
        mLevel = level;
    }

    public void setTime(float time) {
        mTime = time;
    }

    public void setTower(Tower tower) {
        mTower = tower;
    }

}
