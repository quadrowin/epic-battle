package com.quadrolord.epicbattle.logic.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.quadrolord.epicbattle.logic.bullet.leveling.AbstractStrategy;
import com.quadrolord.epicbattle.logic.bullet.leveling.LevelingDto;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.view.BulletUnitView;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class BulletInfo {

    public BulletInfo() {

    }

    private int mCost;

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

    private Texture mIcon;

    private int mLevel = 0;

    protected Array<LevelingDto> mLevelUps = new Array<LevelingDto>();

    protected AbstractStrategy mLevelingStrategy;

    public Class<? extends AbstractBullet> getBulletClass() {
        return mBulletClass;
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

    public Texture getIcon() {
        return mIcon;
    }

    public void setIcon(Texture mIcon) {
        this.mIcon = mIcon;
    }

    public int getLevel() {
        return mLevel;
    }

    public void setLevel(int level) {
        mLevel = level;
        mLevelingStrategy.setLevel(this, mLevel);

        Gdx.app.log("bullets", "Set level of " + mBulletClass.getSimpleName() + " to " + mLevel);
    }

    public void setBulletClass(Class<? extends AbstractBullet> bulletClass) {
        mBulletClass = bulletClass;
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
