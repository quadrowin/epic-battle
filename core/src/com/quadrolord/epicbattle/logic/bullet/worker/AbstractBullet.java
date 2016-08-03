package com.quadrolord.epicbattle.logic.bullet.worker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.quadrolord.epicbattle.logic.skill.AbstractBulletSkill;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.logic.tower.BattleGame;
import com.quadrolord.epicbattle.logic.tower.GameUnit;
import com.quadrolord.epicbattle.logic.tower.Tower;
import com.quadrolord.epicbattle.logic.tower.TowerUnitHeap;

import java.util.Iterator;

/**
 * Created by Quadrowin on 09.01.2016.
 * Абстрактный класс логики патрона (боевого юнита)
 */
abstract public class AbstractBullet extends GameUnit {

    private static float IDLE_TIME = 0.5f;

    public static float FOLD_BACK_TIME = 1;

    protected SkillItem mSkill;

    protected Tower mTower;

    private Array<GameUnit> mTargets = new Array<GameUnit>();

    /**
     * Состояние
     */
    private BulletState mState;

    /**
     * Время в текущем состоянии
     */
    private float mStateTime = 0;

    private float mStateLengthTime = 1;

    private float mLastAttackedTime;
    private float mTime = 0;

    public AbstractBullet(BattleGame game) {
        super(game);
    }

    public void findTargets() {
        TowerUnitHeap enemies = mTower.getEnemy().getUnitsHeap();
        AbstractBulletSkill bs = (AbstractBulletSkill) mSkill.getInfo();

        for (Iterator<GameUnit> it = enemies.iterator(); it.hasNext(); ) {

            if (bs.getMaxTargetCount() <= mTargets.size) {
                return;
            }

            GameUnit unit = it.next();

            if (canAttack(unit) && !unit.isDied()) {
                mTargets.add(unit);
            }
        }

        if (bs.getMaxTargetCount() <= mTargets.size) {
            return;
        }

        GameUnit enemyTower = mTower.getEnemy();

        if (canAttack(enemyTower)) {
            mTargets.add(enemyTower);
        }
    }

    public SkillItem getSkill() {
        return mSkill;
    }

    public void act(float delta) {
        mTime += delta;
        mStateTime += delta;

        if (isDied()) {
            return;
        }

        AbstractBulletSkill bs = (AbstractBulletSkill) mSkill.getInfo();

        Tower enemyTower = getTower().getEnemy();

        if (isAttackingTower(enemyTower) && enemyTower.getUnits().size > 0) {
            removeTarget(enemyTower);
        }

        if (bs.getMaxTargetCount() > mTargets.size) {
            findTargets();
        }

        mIsUnderAttack = false;
        boolean isStateFree = false;

        if (mState == BulletState.FOLD_BACK) {
            if (mStateTime >= FOLD_BACK_TIME) {
                setState(BulletState.IDLE, IDLE_TIME);
            } else {
                setX(getX() - 5 * Math.signum(getVelocity()) * delta * mTower.getTimeUp());
            }
        } else if (mState == BulletState.IDLE) {
            if (mStateTime >= IDLE_TIME) {
                isStateFree = true;
            }
        } else {
            isStateFree = true;
        }

        if (isStateFree) {
            if (mTargets.size > 0) {
                setState(BulletState.ATTACK, bs.getAttackTime());

                if ((mTime - mLastAttackedTime) >= bs.getAttackTime() / mTower.getTimeUp()) {
                    attack();
                    mLastAttackedTime = mTime;
                }
            } else {
                setState(BulletState.RUN, 1); // 1 - время одного шага
            }
        }

        if (isRunning()) {
            setX(getX() + getVelocity() * delta * mTower.getTimeUp());
        }

        mAttackBounds.set(
                getX() - bs.getAttackDistance(), getY(),
                bs.getAttackDistance() * 2, getHeight()
        );

        mTower.getUnitsHeap().act(this);
    }

    public boolean isAttackingTower(Tower tower) {
        return mTargets.contains(tower, false);
    }

    public void attack() {
        if (mTargets.size > 0) {
            mGame.getListener().onBulletAttack(this, mTargets.get(0));
        }
        AbstractBulletSkill bs = (AbstractBulletSkill) mSkill.getInfo();
        for (Iterator<GameUnit> it = mTargets.iterator(); it.hasNext(); ) {
            GameUnit target = it.next();
            if (target.isDied()) {
                it.remove();
            } else {
                target.harm(bs.getAttackDamage());
            }
        }
    }

    @Override
    public void harm(float damage) {
        super.harm(damage);

        mIsUnderAttack = true;
        mTower.getGame().getListener().onBulletInjure(this);
    }

    public BulletState getState() {
        return mState;
    }

    public float getStatePart() {
        return mStateTime / mStateLengthTime;
    }

    public float getStateTime() {
        return mStateTime;
    }

    public boolean canAttack(GameUnit unit) {
        return getAttackBounds().overlaps(unit.getAttackBounds());
    }

    public void removeTarget(GameUnit unit) {
        Iterator<GameUnit> iter = mTargets.iterator();

        while (iter.hasNext()) {
            GameUnit next = iter.next();

            if (next.equals(unit)) {
                iter.remove();
            }
        }
    }

    @Override
    public void onDeath() {
        setState(BulletState.DEATH, 1);

        mTower.getEnemy().reward(this);
        mTower.deleteUnit(this);

        Gdx.app.log("bullets", "unit was died");
    }

    public Tower getTower() {
        return mTower;
    }

    public void setSkill(SkillItem skill) {
        mSkill = skill;
        mTower = skill.getTower();
    }

    public void setState(BulletState state, float lengthTime) {
        if (mState != state) {
            mState = state;
            mStateTime = 0;
            mStateLengthTime = lengthTime;
        }
    }

    public void setStateTime(float time) {
        mStateTime = time;
    }

    public boolean isRunning() {
        return mState == BulletState.RUN;
    }
}
