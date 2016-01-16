package com.quadrolord.epicbattle.logic.bullet.worker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.logic.GameUnit;
import com.quadrolord.epicbattle.logic.tower.Tower;
import com.quadrolord.epicbattle.logic.bullet.BulletInfo;
import com.quadrolord.epicbattle.screen.battle.BleedAnimation;

import java.util.Iterator;

/**
 * Created by Quadrowin on 09.01.2016.
 */
abstract public class AbstractBullet extends GameUnit {

    protected BulletInfo mInfo;

    protected Tower mTower;

    private Array<GameUnit> mTargets = new Array<GameUnit>();

    private Array<AbstractBullet> mAttackers = new Array<AbstractBullet>();

    private boolean mIsRunning = true;
    private boolean mIsAttacking = false;

    private float mLastAttackedTime;
    private float mTime = 0;

    public AbstractBullet(Game game) {
        super(game);
    }

    public void addAttacker(AbstractBullet unit) {
        mAttackers.add(unit);
    }

    public void initInfo() {
        mInfo = mGame.getBulletInfoManager().getBulletInfo(getClass());
    }

    public void findTargets() {
        Array<AbstractBullet> enemies = mTower.getEnemy().getUnits();
        Iterator<AbstractBullet> iter = enemies.iterator();

        while (iter.hasNext()) {
            if (mInfo.getMaxTargetCount() <= mTargets.size) {
                return;
            }

            AbstractBullet unit = iter.next();

            if (canAttack(unit) && !unit.isDied()) {
                mTargets.add(unit);
                unit.addAttacker(this);
            }
        }

        if (mInfo.getMaxTargetCount() <= mTargets.size) {
            return;
        }

        GameUnit enemyTower = mTower.getEnemy();

        if (canAttack(enemyTower)) {
            mTargets.add(enemyTower);
        }
    }

    public BulletInfo getInfo() {
        return mInfo;
    }

    public void act(float delta) {
        mTime += delta;
        Tower enemyTower = getTower().getEnemy();

        if (isAttackingTower(enemyTower) && enemyTower.getUnits().size > 0) {
            removeTarget(enemyTower);
        }

        if (mInfo.getMaxTargetCount() > mTargets.size) {
            findTargets();
        }

        mIsUnderAttack = false;

        if (mTargets.size > 0) {
            mIsRunning = false;
            mIsAttacking = true;

            if ((mTime - mLastAttackedTime) >= mInfo.getAttackTime() / mTower.getTimeUp()) {
                attack();
                mLastAttackedTime = mTime;
            }
        } else {
            mIsRunning = true;
            mIsAttacking = false;
        }

        if (mIsRunning) {
            setX(getX() + getVelocity() * delta * mTower.getTimeUp());
        }
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
                mTargets.get(i).harm(mInfo.getAttackDamage());
            }
        }
    }

    @Override
    public void harm(float damage) {
        super.harm(damage);

        mIsUnderAttack = true;
        mTower.getGame().getListener().onBulletInjure(this);
    }

    public boolean canAttack(GameUnit unit) {
        float dist = getX() < unit.getX()
                ? unit.getX() - getX() - getWidth()
                : getX() - unit.getX() - unit.getWidth();
        return Math.abs(dist) <= mInfo.getAttackDistance();
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
        mIsRunning = false;
        mIsAttacking = false;

        for (Iterator<AbstractBullet> iter = mAttackers.iterator(); iter.hasNext(); ) {
            iter.next().removeTarget(this);
        }

        mTower.getEnemy().incCash(this);
        mTower.deleteUnit(this);

        Gdx.app.log("bullets", "unit was died");
    }

    public Tower getTower() {
        return mTower;
    }

    public void setTower(Tower tower) {
        mTower = tower;
    }

    public void setInfo(BulletInfo info) {
        mInfo = info;
    }

    public boolean isAttacking() {
        return mIsAttacking;
    }

    public boolean isRunning() {
        return mIsRunning;
    }
}
