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
    private float mCooldown;

    private int mLevel;

    private float mTime;

    /**
     * Владелец скила
     */
    private Tower mTower;

    public float getCooldown() {
        return mCooldown;
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
        return mCooldown > 0;
    }

    public void setCooldown(float cooldown) {
        mCooldown = cooldown;
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
