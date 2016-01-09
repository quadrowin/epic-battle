package com.quadrolord.epicbattle.logic;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class GameUnit {

    private float mHp = 100;

    private float mMaxHp = 100;

    private float mVelocity = 0;

    private Object mViewObject;

    private float mX;

    protected Game mGame;

    public GameUnit(Game game) {
        mGame = game;
    }

    public Game getGame() {
        return mGame;
    }

    public float getHp() {
        return mHp;
    }

    public float getMaxHp() {
        return mMaxHp;
    }

    public float getVelocity() {
        return mVelocity;
    }

    public Object getViewObject() {
        return mViewObject;
    }

    public float getX() {
        return mX;
    }

    public void incHp(float delta) {
        mHp += delta;
    }

    public boolean isDied() {
        return (mHp <= 0);
    }

    public void setHp(float hp) {
        mHp = hp;
    }

    public void setMaxHp(float hp) {
        mMaxHp = hp;
    }

    public void setVelocity(float velocity) {
        mVelocity = velocity;
    }

    public void setX(float x) {
        mX = x;
    }


    public void setViewObject(Object obj) {
        mViewObject = obj;
    }

}
