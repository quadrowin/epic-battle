package com.quadrolord.epicbattle.logic.bullet;

import com.quadrolord.epicbattle.view.BulletUnitView;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class BulletInfo {

    private int mCost;

    private float mAttackDamage = 50;

    private float mAttackDistance = 1;

    private float mAttackTime = 1000;

    private int mConstructionTime = 1000;

    private float mMoveSpeed = 1;

    private int mMaxHp = 100;

    private int mMaxTargetCount = 1;

    private String mTitle;

    private Class<? extends BulletUnitView> mViewClass;

    public float getAttackDamage() {
        return mAttackDamage;
    }

    public float getAttackDistance() {
        return mAttackDistance;
    }

    public float getAttackTime() {
        return mAttackTime;
    }

    public int getCost() {
        return mCost;
    }

    public int getConstructionTime() {
        return mConstructionTime;
    }

    public float getMoveSpeed() {
        return mMoveSpeed;
    }

    public int getMaxHp() {
        return mMaxHp;
    }

    public int getMaxTargetCount() {
        return mMaxTargetCount;
    }

    public String getTitle() {
        return mTitle;
    }

    public Class<? extends BulletUnitView> getViewClass() {
        return mViewClass;
    }

    public void setViewClass(Class<? extends BulletUnitView> viewClass) {
        mViewClass = viewClass;
    }

    public void setInfo(String title, int cost, int constructionTime, float attackDamage, float attackDistance, float attackTime, float moveSpeed, int maxTargetCount, int maxHp) {
        mTitle = title;
        mCost = cost;
        mConstructionTime = constructionTime;
        mAttackDamage = attackDamage;
        mAttackDistance = attackDistance;
        mAttackTime = attackTime;
        mMoveSpeed = moveSpeed;
        mMaxTargetCount = maxTargetCount;
        mMaxHp = maxHp;
        mViewClass = BulletUnitView.class;
    }

}
