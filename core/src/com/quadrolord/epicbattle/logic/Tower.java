package com.quadrolord.epicbattle.logic;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.TimeUtils;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;

import java.util.Iterator;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class Tower extends GameUnit {

    private float mSpeedRatio = 1;

    private float mCash = 0;

    private float mCashGrowth = 100;

    private float mConstuctionMultiplier = 1.0f;

    private float mRewardMultiplier = 1.0f;

    private float mTimeUp = 5.0f;

    protected float mMaxHp = 4000;

    private ArrayMap<Class<? extends AbstractBullet>, Long> cooldown = new ArrayMap<Class<? extends AbstractBullet>, Long>();
    private Array<AbstractBullet> mBullets = new Array<AbstractBullet>();

    public Tower(Game game) {
        super(game);
        mHp = mMaxHp;
    }

    public void act(float delta) {
        mCash += mCashGrowth * delta * mTimeUp;

        Iterator<ObjectMap.Entry<Class<? extends AbstractBullet>, Long>> iter = cooldown.iterator();
        long now = TimeUtils.millis();

        while (iter.hasNext()) {
            if (now >= iter.next().value) {
                iter.remove();
            }
        }
    }

    public Array<AbstractBullet> getUnits() {
        return mBullets;
    }

    public void addUnit(AbstractBullet unit) {
        mBullets.add(unit);
        unit.setTower(this);
    }

    public long getConstructionTime(AbstractBullet unit) {
        return Math.round(unit.getInfo().getConstructionTime() * mConstuctionMultiplier / mTimeUp);
    }

    public void toCooldown(AbstractBullet unit) {
        cooldown.put(unit.getClass(), TimeUtils.millis() + getConstructionTime(unit));
    }

    public boolean isInCooldown(AbstractBullet unit) {
        return cooldown.containsKey(unit.getClass());
    }

    public float getCash() {
        return mCash;
    }

    public float getSpeedRatio() {
        return mSpeedRatio;
    }

    public void setCash(float cash) {
        mCash = cash;
    }

    public void setCashGrowth(float growth) {
        mCashGrowth = growth;
    }

    public void setSpeedRatio(float ratio) {
        mSpeedRatio = ratio;
    }

    public boolean hasCash(AbstractBullet unit) {
        return unit.getInfo().getCost() <= mCash;
    }

    public Tower getEnemy() {
        for (Iterator<Tower> iter = mGame.getTowers().iterator(); iter.hasNext(); ) {
            Tower next = iter.next();

            if (!next.equals(this)) {
                return next;
            }
        }

        return null;
    }

    public void deleteUnit(AbstractBullet unit) {
        for (Iterator<AbstractBullet> iter = mBullets.iterator(); iter.hasNext(); ) {
            if (iter.next().equals(unit)) {
                iter.remove();
            }
        }
    }

    public void onDeath() {
        mGame.towerDeath(this);
    }

    public void incCash(AbstractBullet bullet) {
        if (!bullet.isDied()) {
            mCash += bullet.getInfo().getCost() / 3 * mRewardMultiplier;
        }
    }

    public float getTimeUp() {
        return mTimeUp;
    }

}
