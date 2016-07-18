package com.quadrolord.epicbattle.logic.tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ArrayMap;
import com.quadrolord.epicbattle.view.visualization.AbstractVisualization;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class GameUnit {

    public static float DIRECTION_LEFT = -1;

    public static float DIRECTION_RIGHT = 1;

    private float mDirection = DIRECTION_RIGHT;

    protected float mHp = 100;

    protected float mMaxHp = 100;

    private float mVelocity = 0;

    private Object mViewObject;

    private float mX;

    private float mY;

    private float mWidth = 10;

    private float mHeight = 10;

    protected BattleGame mGame;

    protected boolean mIsUnderAttack = false;

    protected Rectangle mAttackBounds = new Rectangle();

    private ArrayMap<Class, Class<? extends AbstractVisualization>> mVisualization = new ArrayMap<Class, Class<? extends AbstractVisualization>>();

    public GameUnit(BattleGame game) {
        mGame = game;
    }

    public BattleGame getGame() {
        return mGame;
    }

    public float getDirection() {
        return mDirection;
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

    public float getHeight() {
        return mHeight;
    }

    public float getWidth() {
        return mWidth;
    }

    public float getX() {
        return mX;
    }

    public float getY() {
        return mY;
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

    public Rectangle getAttackBounds() {
        return mAttackBounds;
    }

    public void onDeath() {

    }

    public boolean isUnderAttack() {
        return isUnderAttack();
    }

    public void setDirection(float direction) {
        mDirection = direction;
    }

    public void setHp(float hp) {
        mHp = hp;
    }

    public void setMaxHp(float hp) {
        mMaxHp = hp;
    }

    public void setPosition(float x, float y) {
        mX = x;
        mY = y;
    }

    public void setVelocity(float velocity) {
        mVelocity = velocity;
    }

    public void setX(float x) {
        mX = x;
    }

    public void setY(float y) {
        mY = y;
    }

    public void setHeight(float height) {
        mHeight = height;
    }

    public void setWidth(float width) {
        mWidth = width;
    }

    public void setViewObject(Object obj) {
        mViewObject = obj;
    }

}
