package com.quadrolord.epicbattle.logic.tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.skill.AbstractSkillEntity;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.view.TowerView;

import java.util.Iterator;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class Tower extends GameUnit {

    /**
     * Текущий активный скилл
     */
    private SkillItem mActiveSkill;

    private float mSpeedRatio = 1;

    private float mCash = 0;

    /**
     * Прирост бабла
     * TODO Вынести в пассивный скил
     */
    private float mCashGrowth = 50;

    private float mConstructionMultiplier = 1.0f;

    private float mRewardMultiplier = 1.0f;

    private float mTime = 0;

    private float mTimeUp = 2.0f;

    protected float mMaxHp = 4000;

    /**
     * Все доступные башне скилы
     */
    private ArrayMap<Class<? extends AbstractSkillEntity>, SkillItem> mAllSkills = new ArrayMap<Class<? extends AbstractSkillEntity>, SkillItem>();

    private Array<AbstractBullet> mBullets = new Array<AbstractBullet>();

    public Tower(BattleGame game) {
        super(game);

        mHp = mMaxHp;
    }

    public SkillItem addBulletSkill(Class<? extends AbstractSkillEntity> bulletClass, int level) {
        AbstractSkillEntity bulletSkill = mGame.getSkillManager().get(bulletClass);
        bulletSkill.setLevel(level);
        SkillItem si = new SkillItem();
        si.setTower(this);
        si.setInfo(bulletSkill);
        si.resetCooldown();
        mAllSkills.put(bulletClass, si);
        return si;
    }

    public void act(float delta) {
        mTime += delta;
        mCash += mCashGrowth * delta * mTimeUp;

        for (Iterator<SkillItem> it = mAllSkills.values().iterator(); it.hasNext(); ) {
            SkillItem skill = it.next();
            if (mTime >= skill.getCooldownFinish()) {
                skill.resetCooldown();
            }
            skill.getInfo().act(skill, delta);
        }
    }

    public void addActSkill(SkillItem skill) {
        skill.setTower(this);
        mAllSkills.put(skill.getInfo().getClass(), skill);
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
        skill.setTower(this);
        mActiveSkill = skill;
        Gdx.app.log("setActiveSkill", skill.getClass().getName());
    }

    public void spawnReset() {
        setHp(getMaxHp());
        mAllSkills.clear();
        mBullets.clear();
    }

    public SkillItem getBulletSkill(Class<? extends AbstractSkillEntity> workerClass) {
        return mAllSkills.get(workerClass);
    }

    public ArrayMap<Class<? extends AbstractSkillEntity>, SkillItem> getBulletSkills() {
        return mAllSkills;
    }

    public Array<AbstractBullet> getUnits() {
        return mBullets;
    }

    public void addUnit(AbstractBullet unit) {
        mBullets.add(unit);
        unit.setTower(this);
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
        if (mActiveSkill.isInCooldown()) {
            return;
        }
        toCooldown(mActiveSkill);
        mActiveSkill.getInfo().use(mActiveSkill);
    }

}
