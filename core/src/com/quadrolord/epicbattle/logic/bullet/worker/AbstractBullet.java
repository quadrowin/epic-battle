package com.quadrolord.epicbattle.logic.bullet.worker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.utils.Array;
import com.quadrolord.epicbattle.logic.skill.AbstractBulletSkill;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.logic.tower.BattleGame;
import com.quadrolord.epicbattle.logic.tower.GameUnit;
import com.quadrolord.epicbattle.logic.tower.Tower;
import com.quadrolord.epicbattle.logic.tower.TowerUnitHeap;
import com.quadrolord.epicbattle.logic.tower.UnitTime;

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
    private BulletState mState = BulletState.IDLE;

    private float mStateLengthTime = 1;

    private float mLastAttackedTime;

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
        UnitTime time = getTime();
        time.existsTime += delta;
        time.stateTime += delta;
        time.statePart = time.stateTime / mStateLengthTime;

        if (isDied()) {
            return;
        }

        AbstractBulletSkill bs = (AbstractBulletSkill) mSkill.getInfo();

        Tower enemyTower = getTower().getEnemy();

        if (isAttackingTower(enemyTower) && enemyTower.getUnits().size > 0) {
            removeTarget(enemyTower);
        }

        mIsUnderAttack = false;

        if (mState == BulletState.ATTACK_PREPARE && time.stateTime >= mStateLengthTime) {
            findTargets();
            if (mTargets.size > 0) {
                if ((time.existsTime - mLastAttackedTime) >= bs.getAttackTime() / mTower.getTimeUp()) {
                    attack();
                }
            }
            setState(BulletState.ATTACK_FINISH, 0);
        }

        if (mState == BulletState.ATTACK_FINISH && time.stateTime >= mStateLengthTime) {
            setState(BulletState.RUN, 1);
        }

        if (mState == BulletState.FOLD_BACK) {
            if (time.stateTime >= FOLD_BACK_TIME) {
                setState(BulletState.IDLE, IDLE_TIME);
            } else {
                setX(getX() - 5 * Math.signum(getVelocity()) * delta * mTower.getTimeUp());
            }
        }

        if (mState == BulletState.RUN || mState == BulletState.IDLE) {
            findTargets();
            if (mTargets.size > 0) {
                setState(BulletState.ATTACK_PREPARE, bs.getAttackTime());
                mLastAttackedTime = time.existsTime;
            } else if (mState == BulletState.IDLE && time.stateTime >= IDLE_TIME) {
                setState(BulletState.RUN, 1); // 1 - время одного шага
            } else if (mState == BulletState.RUN) {
                setX(getX() + getVelocity() * delta * mTower.getTimeUp());
            }
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
                if (target.isDied()) {
                    it.remove();
                }
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
            getTime().stateTime = 0;
            getTime().statePart = 0;
            mStateLengthTime = lengthTime;
        }
    }

    public void setStateTime(float time) {
        getTime().stateTime = time;
        getTime().statePart = time / mStateLengthTime;
    }

    public boolean isRunning() {
        return mState == BulletState.RUN;
    }
}
