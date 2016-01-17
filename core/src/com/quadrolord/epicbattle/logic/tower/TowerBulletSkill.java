package com.quadrolord.epicbattle.logic.tower;

import com.quadrolord.epicbattle.logic.bullet.BulletInfo;

/**
 * Created by Quadrowin on 16.01.2016.
 */
public class TowerBulletSkill {

    private float mCooldown;

    private int mLevel;

    private BulletInfo mBulletInfo;

    public BulletInfo getBulletInfo() {
        return mBulletInfo;
    }

    public float getCooldown() {
        return mCooldown;
    }

    public int getLevel() {
        return mLevel;
    }

    public void setBulletInfo(BulletInfo bulletInfo) {
        mBulletInfo = bulletInfo;
    }

    public void setCooldown(float cooldown) {
        mCooldown = cooldown;
    }

    public void setLevel(int level) {
        mLevel = level;
    }

}
