package com.quadrolord.epicbattle.logic.tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.skill.AbstractBulletSkill;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.view.TowerView;

import java.util.Iterator;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class Tower extends GameUnit {

    private SkillItem mActiveSkill;

    private float mSpeedRatio = 1;

    private float mCash = 0;

    private float mCashGrowth = 50;

    private float mConstuctionMultiplier = 1.0f;

    private float mRewardMultiplier = 1.0f;

    private float mTime = 0;

    private float mTimeUp = 2.0f;

    protected float mMaxHp = 4000;

    private ArrayMap<Class<? extends AbstractBulletSkill>, SkillItem> mBulletSkills = new ArrayMap<Class<? extends AbstractBulletSkill>, SkillItem>();

    private Array<SkillItem> mActSkills = new Array<SkillItem>();
    private Array<AbstractBullet> mBullets = new Array<AbstractBullet>();

    public Tower(BattleGame game) {
        super(game);

        mHp = mMaxHp;
    }

    public AbstractBulletSkill addBulletSkill(Class<? extends AbstractBulletSkill> bulletClass, int level) {
        AbstractBulletSkill bulletSkill = (AbstractBulletSkill)mGame.getSkillManager().get(bulletClass);
        bulletSkill.setLevel(level);
        SkillItem si = new SkillItem();
        si.setInfo(bulletSkill);
        si.setCooldown(0);
        mBulletSkills.put(bulletClass, si);
        return bulletSkill;
    }

    public void act(float delta) {
        mTime += delta;
        mCash += mCashGrowth * delta * mTimeUp;

        for (Iterator<SkillItem> it = mBulletSkills.values().iterator(); it.hasNext(); ) {
            SkillItem tbs = it.next();
            if (mTime >= tbs.getCooldown()) {
                tbs.setCooldown(0);
            }
        }

        for (Iterator<SkillItem> skill_it = mActSkills.iterator(); skill_it.hasNext(); ) {
            SkillItem skill = skill_it.next();
            skill.getInfo().act(skill, delta);
        }
    }

    public void addActSkill(SkillItem skill) {
        mActSkills.add(skill);
    }

    public SkillItem getActiveSkill() {
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

    public void setActiveSkill(SkillItem skill) {
        mActiveSkill = skill;
        Gdx.app.log("setActiveSkill", skill.getClass().getName());
    }

    public void spawnReset() {
        setHp(getMaxHp());
        mActSkills.clear();
        mBulletSkills.clear();
        mBullets.clear();
    }

    public AbstractLogic getBulletInfo(Class<? extends AbstractLogic> workerClass) {
        return mGame.getBulletInfoManager().getBulletLogic(workerClass);
    }

    public SkillItem getBulletSkill(Class<? extends AbstractBulletSkill> workerClass) {
        return mBulletSkills.get(workerClass);
    }

    public ArrayMap<Class<? extends AbstractBulletSkill>, SkillItem> getBulletSkills() {
        return mBulletSkills;
    }

    public int getBulletLevel(Class<? extends AbstractBulletSkill> bulletClass) {
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

    public long getConstructionTime(AbstractBulletSkill bulletSkill) {
        return Math.round(bulletSkill.getConstructionTime() * mConstuctionMultiplier / mTimeUp);
    }

    public void toCooldown(SkillItem skill) {
        skill.setCooldown(mTime + ((AbstractBulletSkill)skill.getInfo()).getConstructionTime());
    }

    public boolean isInCooldown(Class<? extends AbstractBulletSkill> bulletClass) {
        return mBulletSkills.containsKey(bulletClass)
                ? mBulletSkills.get(bulletClass).getCooldown() > 0
                : false;
    }

    public float getCooldownTime(AbstractBulletSkill bulletSkill) {
        SkillItem skill = mBulletSkills.get(bulletSkill.getClass());
        return skill == null ? 0 : Math.max(0, skill.getCooldown() - mTime);
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

    public boolean hasCash(AbstractBulletSkill skill) {
        return skill.getCost() <= mCash;
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
        mCash += ((AbstractBulletSkill)bullet.getSkill().getInfo()).getCost() / 3 * mRewardMultiplier;
    }

    public float getTimeUp() {
        return mTimeUp;
    }

    public void useActiveSkill() {
        if (mActiveSkill == null) {
            return;
        }
        mActiveSkill.getInfo().use(mActiveSkill);
    }

}
