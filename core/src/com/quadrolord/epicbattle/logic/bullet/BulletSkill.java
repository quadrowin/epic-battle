package com.quadrolord.epicbattle.logic.bullet;

import com.badlogic.gdx.Gdx;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.view.BulletUnitView;

/**
 * Скил на выстрел как он есть у игрока
 * Created by Quadrowin on 23.01.2016.
 */
public class BulletSkill {

    private float mAttackDamage = 50;

    private float mAttackDistance = 1;

    private float mAttackTime = 1000;

    private Class<? extends AbstractBullet> mBulletClass;

    private float mConstructionTime = 1;

    /**
     * Текущий кулдаун
     */
    private float mCooldown;

    private int mCost;

    private BulletInfo mInfo;

    private int mLevel;

    private float mMoveSpeed = 1;

    private int mMaxHp = 100;

    private int mMaxTargetCount = 1;

    private String mTitle;

    private Class<? extends BulletUnitView> mViewClass;

    private String mIcon;

    public BulletSkill(BulletInfo info) {
        mInfo = info;
        assignBase(info);
    }

    public void assignBase(BulletInfo info) {
        setAttackDamage(info.getAttackDamage());
        setAttackDistance(info.getAttackDistance());
        setAttackTime(info.getAttackTime());
        setBulletClass(info.getBulletClass());
        setCost(info.getCost());
        setConstructionTime(info.getConstructionTime());
        setIcon(info.getIcon());
        setMaxHp(info.getMaxHp());
        setMaxTargetCount(info.getMaxTargetCount());
        setMoveSpeed(info.getMoveSpeed());
        setTitle(info.getTitle());
        setViewClass(info.getViewClass());
    }

    public BulletInfo getInfo() {
        return mInfo;
    }

    public float getAttackDamage() {
        return mAttackDamage;
    }

    public float getAttackDistance() {
        return mAttackDistance;
    }

    public float getAttackTime() {
        return mAttackTime;
    }

    public Class<? extends AbstractBullet> getBulletClass() {
        return mBulletClass;
    }

    public float getConstructionTime() {
        return mConstructionTime;
    }

    public float getCooldown() {
        return mCooldown;
    }

    public int getCost() {
        return mCost;
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

    public int getLevel() {
        return mLevel;
    }

    public String getIcon() {
        return mIcon;
    }

    public Class<? extends BulletUnitView> getViewClass() {
        return mViewClass;
    }

    public boolean isInCooldown() {
        return mCooldown > 0;
    }

    public void setAttackDistance(float attackDistance) {
        mAttackDistance = attackDistance;
    }

    public void setAttackTime(float attackTime) {
        mAttackTime = attackTime;
    }

    public void setBulletClass(Class<? extends AbstractBullet> bulletClass) {
        mBulletClass = bulletClass;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setAttackDamage(float attackDamage) {
        mAttackDamage = attackDamage;
    }

    public void setConstructionTime(float constructionTime) {
        mConstructionTime = constructionTime;
    }

    public void setCooldown(float cooldown) {
        mCooldown = cooldown;
    }

    public void setCost(int cost) {
        mCost = cost;
    }

    public void setLevel(int level) {
        mLevel = level;
        mInfo.getLevelingStrategy().setLevel(this, mLevel);

        Gdx.app.log("bullets", "Set level of " + mBulletClass.getSimpleName() + " to " + mLevel);
    }

    public void setMaxHp(int maxHp) {
        mMaxHp = maxHp;
    }

    public void setMoveSpeed(float moveSpeed) {
        mMoveSpeed = moveSpeed;
    }

    public void setMaxTargetCount(int maxTargetCount) {
        mMaxTargetCount = maxTargetCount;
    }

    public void setViewClass(Class<? extends BulletUnitView> viewClass) {
        mViewClass = viewClass;
    }

}
