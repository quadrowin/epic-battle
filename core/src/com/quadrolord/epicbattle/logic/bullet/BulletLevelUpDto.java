package com.quadrolord.epicbattle.logic.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.view.BulletUnitView;

/**
 * Created by morph on 13.01.2016.
 */
public class BulletLevelUpDto {
    private int mCostDelta;

    private float mAttackDamageDelta;

    private float mAttackDistanceDelta;

    private float mAttackTimeDelta;

    private float mConstructionTimeDelta;

    private float mMoveSpeedDelta;

    private int mMaxHpDelta;

    private int mMaxTargetCountDelta;

    private String mTitle;

    private Class<? extends BulletUnitView> mViewClass;

    private Class<? extends AbstractBullet> mBulletClass;

    private Texture mIcon;

    public int getCostDelta() {
        return mCostDelta;
    }

    public BulletLevelUpDto setCostDelta(int costDelta) {
        this.mCostDelta = costDelta;
        return this;
    }

    public float getAttackDamageDelta() {
        return mAttackDamageDelta;
    }

    public BulletLevelUpDto setAttackDamageDelta(float attackDamageDelta) {
        this.mAttackDamageDelta = attackDamageDelta;
        return this;
    }

    public float getAttackDistanceDelta() {
        return mAttackDistanceDelta;
    }

    public BulletLevelUpDto setAttackDistanceDelta(float attackDistanceDelta) {
        this.mAttackDistanceDelta = attackDistanceDelta;
        return this;
    }

    public float getAttackTimeDelta() {
        return mAttackTimeDelta;
    }

    public BulletLevelUpDto setAttackTimeDelta(float attackTimeDelta) {
        this.mAttackTimeDelta = attackTimeDelta;
        return this;
    }

    public float getConstructionTimeDelta() {
        return mConstructionTimeDelta;
    }

    public BulletLevelUpDto setConstructionTimeDelta(float constructionTimeDelta) {
        this.mConstructionTimeDelta = constructionTimeDelta;
        return this;
    }

    public float getMoveSpeedDelta() {
        return mMoveSpeedDelta;
    }

    public BulletLevelUpDto setMoveSpeedDelta(float moveSpeedDelta) {
        this.mMoveSpeedDelta = moveSpeedDelta;
        return this;
    }

    public int getMaxHpDelta() {
        return mMaxHpDelta;
    }

    public BulletLevelUpDto setMaxHpDelta(int maxHpDelta) {
        this.mMaxHpDelta = maxHpDelta;
        return this;
    }

    public int getMaxTargetCountDelta() {
        return mMaxTargetCountDelta;
    }

    public BulletLevelUpDto setMaxTargetCountDelta(int maxTargetCountDelta) {
        this.mMaxTargetCountDelta = maxTargetCountDelta;
        return this;
    }

    public String getTitle() {
        return mTitle;
    }

    public BulletLevelUpDto setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    public Class<? extends BulletUnitView> getViewClass() {
        return mViewClass;
    }

    public BulletLevelUpDto setViewClass(Class<? extends BulletUnitView> viewClass) {
        this.mViewClass = viewClass;
        return this;
    }

    public Texture getIcon() {
        return mIcon;
    }

    public BulletLevelUpDto setIcon(Texture icon) {
        this.mIcon = icon;
        return this;
    }

    public Class<? extends AbstractBullet> getBulletClass() {
        return mBulletClass;
    }

    public BulletLevelUpDto setBulletClass(Class<? extends AbstractBullet> bulletClass) {
        mBulletClass = bulletClass;
        return this;
    }

    public void updateInfo(BulletInfo info) {
        if (getBulletClass() != null) {
            info.setBulletClass(getBulletClass());
        }

        if (getTitle() != "") {
            info.setTitle(getTitle());
        }

        if (getIcon() != null) {
            info.setIcon(getIcon());
        }

        if (getViewClass() != null) {
            info.setViewClass(getViewClass());
        }

        info.setAttackDamage(info.getAttackDamage() + getAttackDamageDelta());
        info.setAttackTime(info.getAttackTime() + getAttackTimeDelta());
        info.setAttackDistance(info.getAttackDistance() + getAttackDistanceDelta());
        info.setMaxHp(info.getMaxHp() + getMaxHpDelta());
        info.setMoveSpeed(info.getMoveSpeed() + getMoveSpeedDelta());
        info.setMaxTargetCount(info.getMaxTargetCount() + getMaxTargetCountDelta());
        info.setCost(info.getCost() + getCostDelta());
        info.setConstructionTime(info.getConstructionTime() + getConstructionTimeDelta());
    }
}
