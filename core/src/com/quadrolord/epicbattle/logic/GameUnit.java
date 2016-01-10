package com.quadrolord.epicbattle.logic;

import com.badlogic.gdx.Gdx;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class GameUnit {

    protected float mHp = 100;

    protected float mMaxHp = 100;

    private float mVelocity = 0;

    private Object mViewObject;

    private float mX;

    private float mWidth = 10;

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

    public float getWidth() {
        return mWidth;
    }

    public float getX() {
        return mX;
    }

    public void harm(float damage) {
        Gdx.app.log("bullets", "unit damaged for " + Math.round(damage));
        incHp(-damage);

        if (isDied()) {
            onDeath();
        }
    }

    public void incHp(float delta) {
        mHp += delta;
    }

    public boolean isDied() {
        return (mHp <= 0);
    }

    public void onDeath() {

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

    public void setWidth(float width) {
        mWidth = width;
    }

    public void setViewObject(Object obj) {
        mViewObject = obj;
    }

}
