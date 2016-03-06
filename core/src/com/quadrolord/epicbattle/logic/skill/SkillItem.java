package com.quadrolord.epicbattle.logic.skill;

import com.quadrolord.epicbattle.logic.configurable.AbstractItem;
import com.quadrolord.epicbattle.logic.tower.Tower;

/**
 * Created by Quadrowin on 06.03.2016.
 */
public class SkillItem extends AbstractItem<AbstractSkillEntity> {

    private int mLevel;

    private float mTime;

    private Tower mTower;

    public int getLevel() {
        return mLevel;
    }

    public float getTime() {
        return mTime;
    }

    public Tower getTower() {
        return mTower;
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
