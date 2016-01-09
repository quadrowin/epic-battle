package com.quadrolord.epicbattle.logic;

import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

/**
 * Created by Quadrowin on 08.01.2016.
 */
public class Game {

    private Array<BulletUnit> mBullets = new Array<BulletUnit>();

    private Array<Tower> mTowers = new Array<Tower>();

    private GameListener mListener;

    public void act(float delta) {
        for (Iterator<BulletUnit> iter = mBullets.iterator(); iter.hasNext(); ) {
            BulletUnit bullet = iter.next();
            bullet.act(delta);
        }
        for (Iterator<Tower> iter = mTowers.iterator(); iter.hasNext(); ) {
            iter.next().act(delta);
        }
    }

    public void createTower(float position, float speedRatio) {
        Tower tower = new Tower();
        tower.setPosition(position);
        tower.setSpeedRatio(speedRatio);
        mTowers.add(tower);
        mListener.onTowerCreate(tower);
    }

    public void createUnit(Tower tower, Class<? extends BulletUnit> bulletClass) {
        BulletUnit bullet;
        try {
            bullet = bulletClass.newInstance();
        } catch (Exception e) {
            bullet = new BulletUnit();
        }
        bullet.MoveSpeed = 10 * tower.getSpeedRatio();
        bullet.setPosition(tower.getPosition());
        mBullets.add(bullet);
        tower.setCash(tower.getCash() - 1000);
        mListener.onBulletCreate(bullet);
    }

    public void setListener(GameListener listener) {
        mListener = listener;
    }

    public void startLevel() {
        mTowers.clear();
        mBullets.clear();

        createTower(10, 1);
        createTower(350, -1);
    }

}
