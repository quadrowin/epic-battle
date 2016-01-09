package com.quadrolord.epicbattle.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class BulletUnit {

    public int ConstructionTime = 1000;

    public float CurrentHp = 100;

    public float MaxHp = 100;

    public float AttackDamage = 50;

    public float AttackTime = 1000;

    public float AttackDistance = 1;

    public int Cost = 100;

    public float MoveSpeed;

    public float BaseMoveSpeed = 50;

    public int MaxTargetCount = 1;

    private float mPosition;

    private Object mUserObject;

    private Array<BulletUnit> targets = new Array<BulletUnit>();
    private Array<BulletUnit> attackers = new Array<BulletUnit>();

    private Tower mTower;
    private long mLastAttackedMills;
    private boolean mIsRunning = true;

    public boolean isDied() {
        return (CurrentHp <= 0);
    }

    public void addAttacker(BulletUnit unit) {
        attackers.add(unit);
    }

    public void act(float delta) {
        if (MaxTargetCount > targets.size) {
            findTargets();
        }

        if (targets.size > 0) {
            mIsRunning = false;

            if (TimeUtils.timeSinceMillis(mLastAttackedMills) >= AttackTime) {
                this.attack();
                mLastAttackedMills = TimeUtils.millis();
            }
        } else {
            mIsRunning = true;
        }

        if (mIsRunning) {
            mPosition += MoveSpeed * delta;
        }
    }

    public void attack() {
        for (int i = 0; i < targets.size; i++) {
            targets.get(i).harm(AttackDamage);
        }
    }

    public void harm(float damage) {
        Gdx.app.log("bullets", "unit damaged for " + Math.round(damage));
        CurrentHp -= damage;

        if (isDied()) {
            onDeath();
        }
    }

    public boolean canAttack(BulletUnit unit) {
        return Math.abs(this.mPosition - unit.getPosition()) <= unit.AttackDistance;
    }

    public void findTargets() {
        Array<BulletUnit> enemies = mTower.getEnemy().getUnits();
        Iterator<BulletUnit> iter = enemies.iterator();

        while (iter.hasNext()) {
            if (MaxTargetCount <= targets.size) {
                break;
            }

            BulletUnit unit = iter.next();

            if (this.canAttack(unit) && !unit.isDied()) {
                targets.add(unit);
                unit.addAttacker(this);
            }
        }
    }

    public void removeTarget(BulletUnit unit) {
        Iterator<BulletUnit> iter = targets.iterator();

        while (iter.hasNext()) {
            BulletUnit next = iter.next();

            if (next.equals(unit)) {
                Gdx.app.log("bullets", "unit removed from targets");
                iter.remove();
            }
        }
    }

    public void onDeath() {
        mIsRunning = false;

        for (Iterator<BulletUnit> iter = attackers.iterator(); iter.hasNext(); ) {
            iter.next().removeTarget(this);
        }

        mTower.deleteUnit(this);

        Gdx.app.log("bullets", "unit was died");
    }

    public float getPosition() {
        return mPosition;
    }

    public Object getUserObject() {
        return mUserObject;
    }

    public void setPosition(float pos) {
        mPosition = pos;
    }

    public void setUserObject(Object obj) {
        mUserObject = obj;
    }

    public void setTower(Tower tower) {
        mTower = tower;
    }
}
