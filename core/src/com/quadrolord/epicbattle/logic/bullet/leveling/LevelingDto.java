package com.quadrolord.epicbattle.logic.bullet.leveling;

import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.view.bullet.AbstractBulletView;

/**
 * Created by morph on 13.01.2016.
 */
public class LevelingDto {
    private int mCostDelta;

    private float mAttackDamageDelta;

    private float mAttackDistanceDelta;

    private float mAttackTimeDelta;

    private float mConstructionTimeDelta;

    private float mMoveSpeedDelta;

    private int mMaxHpDelta;

    private int mMaxTargetCountDelta;

    private String mTitle;

    private Class<? extends AbstractBulletView> mViewClass;

    private Class<? extends AbstractBullet> mBulletClass;

    private String mIcon;

    private AbstractStrategy mLevelingStrategy;

    public AbstractStrategy getLevelingStrategy() {
        return mLevelingStrategy;
    }

    public LevelingDto setLevelingStrategy(AbstractStrategy levelingStrategy) {
        mLevelingStrategy = levelingStrategy;
        return this;
    }

    public int getCostDelta() {
        return mCostDelta;
    }

    public LevelingDto setCostDelta(int costDelta) {
        this.mCostDelta = costDelta;
        return this;
    }

    public float getAttackDamageDelta() {
        return mAttackDamageDelta;
    }

    public LevelingDto setAttackDamageDelta(float attackDamageDelta) {
        this.mAttackDamageDelta = attackDamageDelta;
        return this;
    }

    public float getAttackDistanceDelta() {
        return mAttackDistanceDelta;
    }

    public LevelingDto setAttackDistanceDelta(float attackDistanceDelta) {
        this.mAttackDistanceDelta = attackDistanceDelta;
        return this;
    }

    public float getAttackTimeDelta() {
        return mAttackTimeDelta;
    }

    public LevelingDto setAttackTimeDelta(float attackTimeDelta) {
        this.mAttackTimeDelta = attackTimeDelta;
        return this;
    }

    public float getConstructionTimeDelta() {
        return mConstructionTimeDelta;
    }

    public LevelingDto setConstructionTimeDelta(float constructionTimeDelta) {
        this.mConstructionTimeDelta = constructionTimeDelta;
        return this;
    }

    public float getMoveSpeedDelta() {
        return mMoveSpeedDelta;
    }

    public LevelingDto setMoveSpeedDelta(float moveSpeedDelta) {
        this.mMoveSpeedDelta = moveSpeedDelta;
        return this;
    }

    public int getMaxHpDelta() {
        return mMaxHpDelta;
    }

    public LevelingDto setMaxHpDelta(int maxHpDelta) {
        this.mMaxHpDelta = maxHpDelta;
        return this;
    }

    public int getMaxTargetCountDelta() {
        return mMaxTargetCountDelta;
    }

    public LevelingDto setMaxTargetCountDelta(int maxTargetCountDelta) {
        this.mMaxTargetCountDelta = maxTargetCountDelta;
        return this;
    }

    public String getTitle() {
        return mTitle;
    }

    public LevelingDto setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    public Class<? extends AbstractBulletView> getViewClass() {
        return mViewClass;
    }

    public LevelingDto setViewClass(Class<? extends AbstractBulletView> viewClass) {
        this.mViewClass = viewClass;
        return this;
    }

    public String getIcon() {
        return mIcon;
    }

    public LevelingDto setIcon(String icon) {
        mIcon = icon;
        return this;
    }

    public Class<? extends AbstractBullet> getBulletClass() {
        return mBulletClass;
    }

    public LevelingDto setBulletClass(Class<? extends AbstractBullet> bulletClass) {
        mBulletClass = bulletClass;
        return this;
    }
}
