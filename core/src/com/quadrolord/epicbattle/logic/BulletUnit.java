package com.quadrolord.epicbattle.logic;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class BulletUnit {

    public float CurrentHp = 10;

    public float MaxHp = 10;

    public float AttackDamage = 10;

    public float AttackTime = 1000;

    public float MoveSpeed = 1;

    public float Position;

    private Object mUserObject;

    public Object getUserObject() {
        return mUserObject;
    }

    public void setUserObject(Object obj) {
        mUserObject = obj;
    }

}
