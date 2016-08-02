package com.quadrolord.epicbattle.logic.tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.skill.AbstractSkillEntity;
import com.quadrolord.epicbattle.logic.skill.SkillItem;

import java.util.Iterator;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class Tower extends GameUnit {

    /**
     * Текущий активный скилл
     */
    private SkillItem mActiveSkill;

    /**
     * Коэффициент времени отката активного скила
     */
    private float mActiveSkillCdRatio;

    private float mCash = 0;

    private float mConstructionMultiplier = 1.0f;

    private float mRewardMultiplier = 1.0f;

    private float mSummonSkillCdRatio = 1;

    private float mTime = 0;

    private float mTimeUp = 2.0f;

    protected float mMaxHp = 4000;

    /**
     * Все доступные башне скилы
     */
    private ArrayMap<AbstractSkillEntity, SkillItem> mAllSkills = new ArrayMap<AbstractSkillEntity, SkillItem>();

    private Array<AbstractBullet> mBullets = new Array<AbstractBullet>();

    private TowerUnitHeap mUnitsHeap = new TowerUnitHeap();

    public Tower(BattleGame game) {
        super(game);
        mHp = mMaxHp;
        mUnitsHeap.add(this);
    }

    public SkillItem addSkillEntity(AbstractSkillEntity skillEntity, int level) {
        SkillItem si = new SkillItem();
        si.setInfo(skillEntity);
        si.setLevel(level);
        addSkillItem(si);
        return si;
    }

    public void act(float delta) {
        mTime += delta;
        getAttackBounds().set(getX(), getY(), getWidth(), getHeight());

        for (Iterator<SkillItem> it = mAllSkills.values().iterator(); it.hasNext(); ) {
            SkillItem skill = it.next();
            if (mTime >= skill.getCooldownFinish()) {
                skill.resetCooldown();
            }
            skill.getInfo().act(skill, delta);
        }

        mUnitsHeap.act(this);
    }

    public void addSkillItem(SkillItem skill) {
        skill.setTower(this);
        skill.resetCooldown();
        mAllSkills.put(skill.getInfo(), skill);
        skill.getInfo().initTower(skill, this);
    }

    public SkillItem getActiveSkill() {
        return mActiveSkill;
    }

    @Override
    public void harm(float damage) {
        super.harm(damage);

        mGame.getListener().onTowerInjure(this);
    }

    public void setActiveSkill(SkillItem skill) {
        skill.setTower(this);
        mActiveSkill = skill;
        Gdx.app.log("setActiveSkill", skill.getClass().getName());
    }

    public void spawnReset() {
        setHp(getMaxHp());
        mAllSkills.clear();
        mBullets.clear();
    }

    public SkillItem getBulletSkill(AbstractSkillEntity workerClass) {
        return mAllSkills.get(workerClass);
    }

    public ArrayMap<AbstractSkillEntity, SkillItem> getBulletSkills() {
        return mAllSkills;
    }

    public Array<AbstractBullet> getUnits() {
        return mBullets;
    }

    public TowerUnitHeap getUnitsHeap() {
        return mUnitsHeap;
    }

    public void addUnit(AbstractBullet unit) {
        mBullets.add(unit);
        mUnitsHeap.add(unit);
    }

    public float getCooldownLength(SkillItem skill) {
        return skill.getInfo().getCooldownLength() * mConstructionMultiplier / mTimeUp;
    }

    public void toCooldown(SkillItem skill) {
        skill.setCooldown(mTime, skill.getInfo().getCooldownLength());
    }

    /**
     * Возвращает оставшееся время до конца кулдауна
     * @param skill
     * @return
     */
    public float getCooldownRest(SkillItem skill) {
        return skill == null
                ? 0
                : Math.max(0, skill.getCooldownFinish() - mTime) * mConstructionMultiplier / mTimeUp;
    }

    public boolean isPlayer() {
        return getDirection() > 0;
    }

    public float getCash() {
        return mCash;
    }

    public void setActiveSkillCdRatio(float ratio) {
        mActiveSkillCdRatio = ratio;
    }

    public void setCash(float cash) {
        mCash = cash;
    }

    public void setSummonSkillCdRatio(float ratio) {
        mSummonSkillCdRatio = ratio;
    }

    public boolean hasCash(SkillItem skill) {
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
        mBullets.removeValue(unit, true);
        mUnitsHeap.remove(unit);
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
        if (mActiveSkill.isInCooldown()) {
            return;
        }
        toCooldown(mActiveSkill);
        mActiveSkill.getInfo().use(mActiveSkill);
    }

}
