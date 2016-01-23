package com.quadrolord.epicbattle.logic.bullet.worker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.logic.GameUnit;
import com.quadrolord.epicbattle.logic.bullet.BulletInfo;
import com.quadrolord.epicbattle.logic.bullet.BulletSkill;
import com.quadrolord.epicbattle.view.BulletUnitView;
import com.quadrolord.epicbattle.logic.tower.Tower;

import java.util.Iterator;

/**
 * Created by Quadrowin on 09.01.2016.
 */
abstract public class AbstractBullet extends GameUnit {

    private static float IDLE_TIME = 0.5f;

    public static float FOLD_BACK_TIME = 1;

    protected BulletSkill mSkill;

    protected Tower mTower;

    private Array<GameUnit> mTargets = new Array<GameUnit>();

    private Array<AbstractBullet> mAttackers = new Array<AbstractBullet>();

    /**
     * Состояние
     */
    private BulletState mState;

    /**
     * Время в текущем состоянии
     */
    private float mStateTime = 0;

    private float mLastAttackedTime;
    private float mTime = 0;

    public AbstractBullet(Game game) {
        super(game);
    }

    public void addAttacker(AbstractBullet unit) {
        mAttackers.add(unit);
    }

    abstract public void initInfo(BulletInfo info);

    public void findTargets() {
        Array<AbstractBullet> enemies = mTower.getEnemy().getUnits();

        for (Iterator<AbstractBullet> it = enemies.iterator(); it.hasNext(); ) {
            if (mSkill.getMaxTargetCount() <= mTargets.size) {
                return;
            }

            AbstractBullet unit = it.next();

            if (canAttack(unit) && !unit.isDied()) {
                mTargets.add(unit);
                unit.addAttacker(this);
            }
        }

        if (mSkill.getMaxTargetCount() <= mTargets.size) {
            return;
        }

        GameUnit enemyTower = mTower.getEnemy();

        if (canAttack(enemyTower)) {
            mTargets.add(enemyTower);
        }
    }

    public BulletSkill getSkill() {
        return mSkill;
    }

    public void act(float delta) {
        mTime += delta;
        mStateTime += delta;

        Tower enemyTower = getTower().getEnemy();

        if (isAttackingTower(enemyTower) && enemyTower.getUnits().size > 0) {
            removeTarget(enemyTower);
        }

        if (mSkill.getMaxTargetCount() > mTargets.size) {
            findTargets();
        }

        mIsUnderAttack = false;
        boolean isStateFree = false;

        if (mState == BulletState.FOLD_BACK) {
            if (mStateTime >= FOLD_BACK_TIME) {
                setState(BulletState.IDLE);
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
                setState(BulletState.ATTACK);

                if ((mTime - mLastAttackedTime) >= mSkill.getAttackTime() / mTower.getTimeUp()) {
                    attack();
                    mLastAttackedTime = mTime;
                }
            } else {
                setState(BulletState.RUN);
            }
        }

        if (isRunning()) {
            setX(getX() + getVelocity() * delta * mTower.getTimeUp());
        }

        BulletUnitView bv = (BulletUnitView)getViewObject();

        mBounds.setWidth(getRealWidth() / 2 + mSkill.getAttackDistance() * Math.abs(bv.getScaleX()));
        mBounds.setHeight(bv.getRealHeight());
        mBounds.setY(bv.getY() + 15);
        mBounds.setX(bv.getX() - getRealWidth() / 2);
    }

    public boolean isAttackingTower(Tower tower) {
        return mTargets.contains(tower, false);
    }

    public void attack() {
        if (mTargets.size > 0) {
            mGame.getListener().onBulletAttack(this, mTargets.get(0));
        }
        for (int i = 0; i < mTargets.size; i++) {
            if (!mTargets.get(i).isDied()) {
                mTargets.get(i).harm(mSkill.getAttackDamage());
            }
        }
    }

    @Override
    public void harm(float damage) {
        super.harm(damage);

        mIsUnderAttack = true;
        mTower.getGame().getListener().onBulletInjure(this);
    }

    public Rectangle getBounds() {
        return super.getBounds();
    }

    public BulletState getState() {
        return mState;
    }

    public float getStateTime() {
        return mStateTime;
    }

    public boolean canAttack(GameUnit unit) {
        return getBounds().overlaps(unit.getBounds());
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
        setState(BulletState.DEATH);

        for (Iterator<AbstractBullet> iter = mAttackers.iterator(); iter.hasNext(); ) {
            iter.next().removeTarget(this);
        }

        mTower.getEnemy().reward(this);
        mTower.deleteUnit(this);

        Gdx.app.log("bullets", "unit was died");
    }

    public Tower getTower() {
        return mTower;
    }

    public void setTower(Tower tower) {
        mTower = tower;
    }

    public void setSkill(BulletSkill skill) {
        mSkill = skill;
    }

    public void setState(BulletState state) {
        if (mState != state) {
            mState = state;
            mStateTime = 0;
        }
    }

    public boolean isRunning() {
        return mState == BulletState.RUN;
    }
}
