package com.quadrolord.epicbattle.logic.bullet.worker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.logic.GameUnit;
import com.quadrolord.epicbattle.logic.Tower;
import com.quadrolord.epicbattle.logic.bullet.BulletInfo;

import java.util.Iterator;

/**
 * Created by Quadrowin on 09.01.2016.
 */
abstract public class AbstractBullet extends GameUnit {

    protected BulletInfo mInfo;

    private Tower mTower;

    private Array<GameUnit> mTargets = new Array<GameUnit>();

    private Array<AbstractBullet> mAttackers = new Array<AbstractBullet>();

    private long mLastAttackedMills;
    private boolean mIsRunning = true;

    public AbstractBullet(Game game) {
        super(game);
    }

    public void addAttacker(AbstractBullet unit) {
        mAttackers.add(unit);
    }

    abstract public void initInfo(BulletInfo info);

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
        if (mInfo.getMaxTargetCount() > mTargets.size) {
            findTargets();
        }

        if (mTargets.size > 0) {
            mIsRunning = false;

            if (TimeUtils.timeSinceMillis(mLastAttackedMills) >= mInfo.getAttackTime() / mTower.getTimeUp()) {
                attack();
                mLastAttackedMills = TimeUtils.millis();
            }
        } else {
            mIsRunning = true;
        }

        if (mIsRunning) {
            setX(getX() + getVelocity() * delta * mTower.getTimeUp());
        }
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

    public boolean canAttack(GameUnit unit) {
        float dist = getX() < unit.getX()
                ? unit.getX() - getX() - getWidth()
                : getX() - unit.getX() - unit.getWidth();
        return Math.abs(dist) <= mInfo.getAttackDistance();
    }

    public void removeTarget(AbstractBullet unit) {
        Iterator<GameUnit> iter = mTargets.iterator();

        while (iter.hasNext()) {
            GameUnit next = iter.next();

            if (next.equals(unit)) {
                Gdx.app.log("bullets", "unit removed from targets");
                iter.remove();
            }
        }
    }

    @Override
    public void onDeath() {
        mIsRunning = false;

        for (Iterator<AbstractBullet> iter = mAttackers.iterator(); iter.hasNext(); ) {
            iter.next().removeTarget(this);
        }

        mTower.deleteUnit(this);

        Gdx.app.log("bullets", "unit was died");
    }

    public void setTower(Tower tower) {
        mTower = tower;
    }

    public void setInfo(BulletInfo info) {
        mInfo = info;
    }

}
