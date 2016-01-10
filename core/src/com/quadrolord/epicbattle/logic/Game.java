package com.quadrolord.epicbattle.logic;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.quadrolord.epicbattle.logic.bullet.BulletInfo;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.Simple;

import java.util.Iterator;

/**
 * Created by Quadrowin on 08.01.2016.
 */
public class Game {

    private Array<Tower> mTowers = new Array<Tower>();
    private Array<AbstractBullet> mBullets = new Array<AbstractBullet>();

    private ArrayMap<Class<? extends AbstractBullet>, BulletInfo> mBulletInfos = new ArrayMap<Class<? extends AbstractBullet>, BulletInfo>();

    private GameListener mListener;

    private float mTowerLeft;

    private float mTowerRight;

    public void act(float delta) {
        for (Iterator<Tower> towers = mTowers.iterator(); towers.hasNext(); ) {
            towers.next().act(delta);
        }

        int i = 0;
        for (Iterator<AbstractBullet> units = mBullets.iterator(); units.hasNext(); i++) {
            AbstractBullet unit = units.next();

            if (unit.isDied()) {
                units.remove();
                mListener.onBulletRemove(unit);
            } else {
                unit.act(delta);
            }
        }
    }

    public void createTower(float position, float speedRatio) {
        Tower tower = new Tower(this);
        tower.setX(position);
        tower.setSpeedRatio(speedRatio);
        tower.setWidth(60);
        mTowers.add(tower);
        mListener.onTowerCreate(tower);
    }

    public void createUnit(Tower tower, Class<? extends AbstractBullet> workerClass) {
        AbstractBullet bullet;
        try {
            bullet = workerClass.getConstructor(Game.class).newInstance(this);
        } catch (Exception e) {
            bullet = new Simple(this);
        }

        BulletInfo bi = mBulletInfos.get(workerClass);
        if (bi == null) {
            bi = new BulletInfo();
            mBulletInfos.put(workerClass, bi);
            bullet.initInfo(bi);
        }
        bullet.setInfo(bi);

        if (tower.isInCooldown(bullet)) {
            mListener.onBulletCreateFailCooldown();
            return ;
        }

        if (!tower.hasCash(bullet)) {
            mListener.onBulletCreateFailCash(tower.getCash(), bi.getCost());
            return;
        }

        bullet.setWidth(30);
        bullet.setMaxHp(bi.getMaxHp());
        bullet.setHp(bullet.getMaxHp());
        bullet.setVelocity(bi.getMoveSpeed() * tower.getSpeedRatio());
        bullet.setX(tower.getX());

        tower.setCash(tower.getCash() - bi.getCost());
        tower.addUnit(bullet);
        tower.toCooldown(bullet);

        mBullets.add(bullet);
        mListener.onBulletCreate(bullet);

        mListener.onBulletAttack(bullet, tower);
    }

    public GameListener getListener() {
        return mListener;
    }

    public void setListener(GameListener listener) {
        mListener = listener;
    }

    public void startLevel() {
        mBullets.clear();
        mTowers.clear();

        createTower(10, 1);
        createTower(640, -1);

        mTowerLeft = 10;
        mTowerRight = 640;
    }

    public void towerDeath(Tower tower) {
        mListener.onTowerDeath(tower);
    }

    public Array<Tower> getTowers() {
        return mTowers;
    }

    public float getTowerLeft() {
        return mTowerLeft;
    }

    public float getTowerRight() {
        return mTowerRight;
    }

}
