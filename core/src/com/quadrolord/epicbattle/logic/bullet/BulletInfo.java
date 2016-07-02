package com.quadrolord.epicbattle.logic.bullet;

import com.badlogic.gdx.utils.Array;
import com.quadrolord.epicbattle.logic.bullet.leveling.AbstractStrategy;
import com.quadrolord.epicbattle.logic.bullet.leveling.LevelingDto;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.view.BulletUnitView;

/**
 * Базовое описание скила-выстрела
 */
public class BulletInfo {

    public BulletInfo() {

    }

    private int mCost;

    private String mDescription;

    private float mAttackDamage = 50;

    private float mAttackDistance = 1;

    private float mAttackTime = 1000;

    private float mConstructionTime = 1;

    private float mMoveSpeed = 1;

    private int mMaxHp = 100;

    private int mMaxTargetCount = 1;

    private String mTitle;

    private Class<? extends BulletUnitView> mViewClass;

    private Class<? extends AbstractBullet> mBulletClass;

    private String mIcon;

    protected Array<LevelingDto> mLevelUps = new Array<LevelingDto>();

    protected AbstractStrategy mLevelingStrategy;

    public Class<? extends AbstractBullet> getBulletClass() {
        return mBulletClass;
    }

    public String getDescription() {
        return mDescription;
    }

    public AbstractStrategy getLevelingStrategy() {
        return mLevelingStrategy;
    }

    public void setLevelingStrategy(AbstractStrategy levelingStrategy) {
        mLevelingStrategy = levelingStrategy;
    }

    public Array<LevelingDto> getLevelUps() {
        return mLevelUps;
    }

    public void setLevelUps(Array<LevelingDto> levelUps) {
        mLevelUps = levelUps;
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

    public int getCost() {
        return mCost;
    }

    public float getConstructionTime() {
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

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String mIcon) {
        this.mIcon = mIcon;
    }

    public void setBulletClass(Class<? extends AbstractBullet> bulletClass) {
        mBulletClass = bulletClass;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setAttackDamage(float attackDamage) {
        mAttackDamage = attackDamage;
    }

    public void setAttackTime(float attackTime) {
        mAttackTime = attackTime;
    }

    public void setAttackDistance(float attackDistance) {
        mAttackDistance = attackDistance;
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

    public void setCost(int cost) {
        mCost = cost;
    }

    public void setConstructionTime(float constructionTime) {
        mConstructionTime = constructionTime;
    }
}
