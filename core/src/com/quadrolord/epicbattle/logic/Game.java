package com.quadrolord.epicbattle.logic;

import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

/**
 * Created by Quadrowin on 08.01.2016.
 */
public class Game {

    private Array<BulletUnit> mBullets = new Array<BulletUnit>();

    private BulletCallback mOnBulletCreate;

    public void act(float delta) {
        for (Iterator<BulletUnit> iter = mBullets.iterator(); iter.hasNext(); ) {
            BulletUnit bullet = iter.next();
            bullet.Position += bullet.MoveSpeed * delta;
        }
    }

    public void createBullet() {
        BulletUnit bullet = new BulletUnit();
        bullet.MoveSpeed = 10;
        mBullets.add(bullet);
        mOnBulletCreate.run(bullet);
    }

    public void setOnBulletCreate(BulletCallback callback) {
        mOnBulletCreate = callback;
    }

}
