package com.quadrolord.epicbattle.logic.tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.logic.GameUnit;
import com.quadrolord.epicbattle.logic.bullet.BulletInfo;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.Simple;
import com.quadrolord.epicbattle.logic.skill.AbstractSkill;
import com.quadrolord.epicbattle.view.TowerView;

import org.w3c.dom.css.Rect;

import java.util.Iterator;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class Tower extends GameUnit {

    private float mSpeedRatio = 1;

    private float mCash = 0;

    private float mCashGrowth = 50;

    private float mConstuctionMultiplier = 1.0f;

    private float mRewardMultiplier = 1.0f;

    private float mTime = 0;

    private float mTimeUp = 2.0f;

    protected float mMaxHp = 4000;

    private ArrayMap<Class<? extends AbstractBullet>, Float> cooldown = new ArrayMap<Class<? extends AbstractBullet>, Float>();
    private Array<AbstractSkill> mActSkills = new Array<AbstractSkill>();
    private Array<AbstractBullet> mBullets = new Array<AbstractBullet>();
    private ArrayMap<Class<? extends AbstractBullet>, Integer> mBulletLevels = new ArrayMap<Class<? extends AbstractBullet>, Integer>();

    private ArrayMap<Class<? extends AbstractBullet>, BulletInfo> mBulletInfos = new ArrayMap<Class<? extends AbstractBullet>, BulletInfo>();
    private Array<Class<? extends AbstractBullet>> mBulletClasses = new Array<Class<? extends AbstractBullet>>();

    public Tower(Game game) {
        super(game);
        mHp = mMaxHp;
        mWidth = 200;
    }

    public void act(float delta) {
        mTime += delta;
        mCash += mCashGrowth * delta * mTimeUp;

        Iterator<ObjectMap.Entry<Class<? extends AbstractBullet>, Float>> iter = cooldown.iterator();

        while (iter.hasNext()) {
            if (mTime >= iter.next().value) {
                iter.remove();
            }
        }

        for (Iterator<AbstractSkill> skill_iter = mActSkills.iterator(); skill_iter.hasNext(); ) {
            AbstractSkill skill = skill_iter.next();
            //Gdx.app.log("tower", "act skill " + skill.getClass().getName() + " " + delta);
            skill.act(delta);
        }
    }

    public void addActSkill(AbstractSkill skill) {
        mActSkills.add(skill);
        Gdx.app.log("tower", "addActSkill len: " + mActSkills.size);
    }

    @Override
    public void harm(float damage) {
        super.harm(damage);

        mGame.getListener().onTowerInjure(this);
    }

    public float getRealWidth() {
        return ((TowerView)getViewObject()).getWidth();
    }

    public Rectangle getBounds() {
        TowerView tv = (TowerView)getViewObject();

        mBounds.setPosition(getX(), tv.getY());
        mBounds.setSize(getRealWidth(), tv.getHeight());

        return super.getBounds();
    }

    public void spawnReset() {
        setHp(getMaxHp());
        mActSkills.clear();
        Gdx.app.log("tower", "spawnReset");
    }

    public BulletInfo getBulletInfo(Class<? extends AbstractBullet> workerClass) {
        BulletInfo bi = mBulletInfos.get(workerClass);

        if (bi == null) {
            AbstractBullet bullet;

            try {
                bullet = workerClass.getConstructor(Game.class).newInstance(getGame());
            } catch (Exception e) {
                bullet = new Simple(getGame());
            }

            bullet.initInfo();
            bi = bullet.getInfo();

            mBulletInfos.put(workerClass, bi);
        }
        return bi;
    }

    public void setBulletLevels(ArrayMap<Class<? extends AbstractBullet>, Integer> bulletLevels) {
        mBulletLevels = bulletLevels;
    }

    public ArrayMap<Class<? extends AbstractBullet>, Integer> getBulletLevels() {
        return mBulletLevels;
    }

    public int getBulletLevel(Class<? extends AbstractBullet> bulletClass) {
        if (mBulletLevels.containsKey(bulletClass)) {
            return mBulletLevels.get(bulletClass);
        }

        return 1;
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

    public long getConstructionTime(Class<? extends AbstractBullet> bulletClass) {
        return Math.round(getBulletInfo(bulletClass).getConstructionTime() * mConstuctionMultiplier / mTimeUp);
    }

    public void toCooldown(AbstractBullet unit) {
        cooldown.put(unit.getClass(), mTime + getConstructionTime(unit));
    }

    public boolean isInCooldown(AbstractBullet unit) {
        return cooldown.containsKey(unit.getClass());
    }

    public boolean isInCooldown(Class<? extends AbstractBullet> bulletClass) {
        return cooldown.containsKey(bulletClass);
    }

    public float getCooldownTime(Class<? extends AbstractBullet> bulletClass) {
        if (!cooldown.containsKey(bulletClass)) {
            return 0.0f;
        }

        return cooldown.get(bulletClass) - mTime;
    }

    public boolean isPlayer() {
        return mSpeedRatio > 0;
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

    public boolean hasCash(Class<? extends AbstractBullet> bulletClass) {
        return getBulletInfo(bulletClass).getCost() <= mCash;
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
        mCash += bullet.getInfo().getCost() / 3 * mRewardMultiplier;
    }

    public Array<Class<? extends AbstractBullet>> getBulletClasses() {
        return mBulletClasses;
    }

    public float getTimeUp() {
        return mTimeUp;
    }

}
