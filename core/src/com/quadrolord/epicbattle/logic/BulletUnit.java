package com.quadrolord.epicbattle.logic;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class BulletUnit {

    public int ConstructionTime = 100;

    public float CurrentHp = 10;

    public float MaxHp = 10;

    public float AttackDamage = 10;

    public float AttackTime = 1000;

    public int Cost = 100;

    public float MoveSpeed = 1;

    private float mPosition;

    private Object mUserObject;

    public void act(float delta) {
        mPosition += MoveSpeed * delta;
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
}
