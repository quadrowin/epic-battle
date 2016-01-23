package com.quadrolord.epicbattle.logic.tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.logic.GameUnit;
import com.quadrolord.epicbattle.logic.bullet.BulletInfo;
import com.quadrolord.epicbattle.logic.bullet.BulletSkill;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.Simple;
import com.quadrolord.epicbattle.logic.skill.AbstractSkill;
import com.quadrolord.epicbattle.view.TowerView;

import java.util.Iterator;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class Tower extends GameUnit {

    private AbstractSkill mActiveSkill;

    private float mSpeedRatio = 1;

    private float mCash = 0;

    private float mCashGrowth = 50;

    private float mConstuctionMultiplier = 1.0f;

    private float mRewardMultiplier = 1.0f;

    private float mTime = 0;

    private float mTimeUp = 2.0f;

    protected float mMaxHp = 4000;

    private ArrayMap<Class<? extends AbstractBullet>, BulletSkill> mBulletSkills = new ArrayMap<Class<? extends AbstractBullet>, BulletSkill>();

    private Array<AbstractSkill> mActSkills = new Array<AbstractSkill>();
    private Array<AbstractBullet> mBullets = new Array<AbstractBullet>();
    private ArrayMap<Class<? extends AbstractBullet>, BulletInfo> mBulletInfos = new ArrayMap<Class<? extends AbstractBullet>, BulletInfo>();

    public Tower(Game game) {
        super(game);

        mHp = mMaxHp;
    }

    public BulletSkill addBulletSkill(Class<? extends AbstractBullet> bulletClass, int level) {
        BulletSkill bulletSkill = new BulletSkill(getBulletInfo(bulletClass));
        bulletSkill.setLevel(level);
        bulletSkill.setCooldown(0);
        mBulletSkills.put(bulletClass, bulletSkill);
        return bulletSkill;
    }

    public void act(float delta) {
        mTime += delta;
        mCash += mCashGrowth * delta * mTimeUp;

        for (Iterator<BulletSkill> it = mBulletSkills.values().iterator(); it.hasNext(); ) {
            BulletSkill tbs = it.next();
            if (mTime >= tbs.getCooldown()) {
                tbs.setCooldown(0);
            }
        }

        for (Iterator<AbstractSkill> skill_it = mActSkills.iterator(); skill_it.hasNext(); ) {
            AbstractSkill skill = skill_it.next();
            skill.act(delta);
        }
    }

    public void addActSkill(AbstractSkill skill) {
        mActSkills.add(skill);
    }

    public AbstractSkill getActiveSkill() {
        return mActiveSkill;
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

    public void setActiveSkill(AbstractSkill skill) {
        mActiveSkill = skill;
        Gdx.app.log("setActiveSkill", skill.getClass().getName());
    }

    public void spawnReset() {
        setHp(getMaxHp());
        mActSkills.clear();
        mBulletSkills.clear();
        mBullets.clear();
    }

    public BulletInfo getBulletInfo(Class<? extends AbstractBullet> workerClass) {
        BulletInfo bi = mBulletInfos.get(workerClass);

        if (bi == null) {
            AbstractBullet bullet;

            try {
                bullet = workerClass.getConstructor(Game.class).newInstance(getGame());
            } catch (Exception e) {
                Gdx.app.error("Tower.getBulletInfo", "error create bullet worker " + workerClass.getName());
                e.printStackTrace();
                bullet = new Simple(mGame);
            }

            bi = mGame.getBulletInfoManager().getBulletInfo(workerClass);
            bullet.initInfo(bi);

            mBulletInfos.put(workerClass, bi);
        }
        return bi;
    }

    public BulletSkill getBulletSkill(Class<? extends AbstractBullet> workerClass) {
        return mBulletSkills.get(workerClass);
    }

    public ArrayMap<Class<? extends AbstractBullet>, BulletSkill> getBulletSkills() {
        return mBulletSkills;
    }

    public int getBulletLevel(Class<? extends AbstractBullet> bulletClass) {
        if (mBulletSkills.containsKey(bulletClass)) {
            return mBulletSkills.get(bulletClass).getLevel();
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
        return Math.round(unit.getSkill().getConstructionTime() * mConstuctionMultiplier / mTimeUp);
    }

    public long getConstructionTime(Class<? extends AbstractBullet> bulletClass) {
        return Math.round(getBulletInfo(bulletClass).getConstructionTime() * mConstuctionMultiplier / mTimeUp);
    }

    public void toCooldown(AbstractBullet unit) {
        mBulletSkills.get(unit.getClass()).setCooldown(mTime + getConstructionTime(unit));
    }

    public boolean isInCooldown(AbstractBullet unit) {
        return isInCooldown(unit.getClass());
    }

    public boolean isInCooldown(Class<? extends AbstractBullet> bulletClass) {
        return mBulletSkills.containsKey(bulletClass)
                ? mBulletSkills.get(bulletClass).getCooldown() > 0
                : false;
    }

    public float getCooldownTime(Class<? extends AbstractBullet> bulletClass) {
        return Math.max(0, mBulletSkills.get(bulletClass).getCooldown() - mTime);
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

    public boolean hasCash(BulletInfo info) {
        return info.getCost() <= mCash;
    }

    public boolean hasCash(Class<? extends AbstractBullet> bulletClass) {
        return hasCash(getBulletInfo(bulletClass));
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

    public void reward(AbstractBullet bullet) {
        mCash += bullet.getSkill().getCost() / 3 * mRewardMultiplier;
    }

    public float getTimeUp() {
        return mTimeUp;
    }

    public void useActiveSkill() {
        if (mActiveSkill == null) {
            return;
        }
        mActiveSkill.use();
    }

}
