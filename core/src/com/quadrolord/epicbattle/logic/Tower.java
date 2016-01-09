package com.quadrolord.epicbattle.logic;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class Tower {

    private float mSpeedRatio = 1;

    private float mPosition;

    private float mCash = 0;

    private float mCashGrowth = 100;

    public void act(float delta) {
        mCash += mCashGrowth * delta;
    }

    public float getCash() {
        return mCash;
    }

    public float getPosition() {
        return mPosition;
    }

    public float getSpeedRatio() {
        return mSpeedRatio;
    }

    public void setCash(float cash) {
        mCash = cash;
    }

    public void setCashGrowth(float growth) {
        mCashGrowth = growth;
    }

    public void setPosition(float pos) {
        mPosition = pos;
    }

    public void setSpeedRatio(float ratio) {
        mSpeedRatio = ratio;
    }

}
