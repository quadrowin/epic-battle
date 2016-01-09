package com.quadrolord.epicbattle.logic;

import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

/**
 * Created by Quadrowin on 08.01.2016.
 */
public class Game {

    private Array<Tower> mTowers = new Array<Tower>();
    private Array<BulletUnit> mBullets = new Array<BulletUnit>();

    private GameListener mListener;

    public void act(float delta) {
        for (Iterator<Tower> towers = mTowers.iterator(); towers.hasNext(); ) {
            towers.next().act(delta);
        }

        int i = 0;
        for (Iterator<BulletUnit> units = mBullets.iterator(); units.hasNext(); i++) {
            BulletUnit unit = units.next();

            if (unit.isDied()) {
                units.remove();
            } else {
                unit.act(delta);
            }
        }
    }

    public void createTower(float position, float speedRatio) {
        Tower tower = new Tower(this);
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

        if (!tower.isInCooldown(bullet) && tower.hasCash(bullet)) {
            bullet.MoveSpeed = bullet.BaseMoveSpeed * tower.getSpeedRatio();
            bullet.setPosition(tower.getPosition());

            tower.setCash(tower.getCash() - bullet.Cost);
            tower.addUnit(bullet);
            tower.toCooldown(bullet);

            mBullets.add(bullet);
            mListener.onBulletCreate(bullet);
        }
    }

    public void setListener(GameListener listener) {
        mListener = listener;
    }

    public void startLevel() {
        mBullets.clear();
        mTowers.clear();

        createTower(10, 1);
        createTower(350, -1);
    }

    public Array<Tower> getTowers() {
        return mTowers;
    }

}
