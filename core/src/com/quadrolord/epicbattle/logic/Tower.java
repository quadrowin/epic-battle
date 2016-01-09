package com.quadrolord.epicbattle.logic;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.TimeUtils;

import java.sql.Time;
import java.util.Iterator;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class Tower {

    private float mSpeedRatio = 1;

    private float mPosition;

    private float mCash = 0;

    private float mCashGrowth = 100;

    private float mConstuctionMultiplier = 1.0f;

    private ArrayMap<Class<? extends BulletUnit>, Long> cooldown = new ArrayMap<Class<? extends BulletUnit>, Long>();
    private Array<BulletUnit> mBullets = new Array<BulletUnit>();

    private Game mGame;

    public Tower(Game game) {
        mGame = game;
    }

    public void act(float delta) {
        mCash += mCashGrowth * delta;

        Iterator<ObjectMap.Entry<Class<? extends BulletUnit>, Long>> iter = cooldown.iterator();
        long now = TimeUtils.millis();

        while (iter.hasNext()) {
            if (now >= iter.next().value) {
                iter.remove();
            }
        }
    }

    public Array<BulletUnit> getUnits() {
        return mBullets;
    }

    public void addUnit(BulletUnit unit) {
        mBullets.add(unit);
        unit.setTower(this);
    }

    public long getConstructionTime(BulletUnit unit) {
        return Math.round(unit.ConstructionTime * mConstuctionMultiplier);
    }

    public void toCooldown(BulletUnit unit) {
        cooldown.put(unit.getClass(), TimeUtils.millis() + getConstructionTime(unit));
    }

    public boolean isInCooldown(BulletUnit unit) {
        return cooldown.containsKey(unit.getClass());
    }

    public float getCash() {
        return mCash;
    }

    public float getPosition() {
        return mPosition;
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

    public void setPosition(float pos) {
        mPosition = pos;
    }

    public void setSpeedRatio(float ratio) {
        mSpeedRatio = ratio;
    }

    public boolean hasCash(BulletUnit unit) {
        return unit.Cost <= mCash;
    }

    public Game getGame() {
        return mGame;
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

    public void deleteUnit(BulletUnit unit) {
        for (Iterator<BulletUnit> iter = mBullets.iterator(); iter.hasNext(); ) {
            if (iter.next().equals(unit)) {
                iter.remove();
            }
        }
    }
}
