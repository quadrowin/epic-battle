package com.quadrolord.epicbattle.logic.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.view.BulletUnitView;

/**
 * Created by morph on 13.01.2016.
 */
public class BulletInfoDto {
    private int mCost;

    private float mAttackDamage;

    private float mAttackDistance;

    private float mAttackTime;

    private float mConstructionTime;

    private float mMoveSpeed;

    private int mMaxHp;

    private int mMaxTargetCount;

    private String mTitle;

    private Class<? extends BulletUnitView> mViewClass;

    private Class<? extends AbstractBullet> mBulletClass;

    private Texture mIcon;

    public int getCost() {
        return mCost;
    }

    public BulletInfoDto setCost(int cost) {
        this.mCost = cost;
        return this;
    }

    public float getAttackDamage() {
        return mAttackDamage;
    }

    public BulletInfoDto setAttackDamage(float attackDamage) {
        this.mAttackDamage = attackDamage;
        return this;
    }

    public float getAttackDistance() {
        return mAttackDistance;
    }

    public BulletInfoDto setAttackDistance(float attackDistance) {
        this.mAttackDistance = attackDistance;
        return this;
    }

    public float getAttackTime() {
        return mAttackTime;
    }

    public BulletInfoDto setAttackTime(float attackTime) {
        this.mAttackTime = attackTime;
        return this;
    }

    public float getConstructionTime() {
        return mConstructionTime;
    }

    public BulletInfoDto setConstructionTime(float constructionTime) {
        this.mConstructionTime = constructionTime;
        return this;
    }

    public float getMoveSpeed() {
        return mMoveSpeed;
    }

    public BulletInfoDto setMoveSpeed(float moveSpeed) {
        this.mMoveSpeed = moveSpeed;
        return this;
    }

    public int getMaxHp() {
        return mMaxHp;
    }

    public BulletInfoDto setMaxHp(int maxHp) {
        this.mMaxHp = maxHp;
        return this;
    }

    public int getMaxTargetCount() {
        return mMaxTargetCount;
    }

    public BulletInfoDto setMaxTargetCount(int maxTargetCount) {
        this.mMaxTargetCount = maxTargetCount;
        return this;
    }

    public String getTitle() {
        return mTitle;
    }

    public BulletInfoDto setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    public Class<? extends BulletUnitView> getViewClass() {
        return mViewClass;
    }

    public BulletInfoDto setViewClass(Class<? extends BulletUnitView> viewClass) {
        this.mViewClass = viewClass;
        return this;
    }

    public Texture getIcon() {
        return mIcon;
    }

    public BulletInfoDto setIcon(Texture icon) {
        this.mIcon = icon;
        return this;
    }

    public Class<? extends AbstractBullet> getBulletClass() {
        return mBulletClass;
    }

    public BulletInfoDto setBulletClass(Class<? extends AbstractBullet> bulletClass) {
        mBulletClass = bulletClass;
        return this;
    }

    public BulletInfo createInfo() {
        return new BulletInfo(this);
    }
}
