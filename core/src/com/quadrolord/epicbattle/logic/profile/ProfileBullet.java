package com.quadrolord.epicbattle.logic.profile;

import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;

/**
 * Доступный игроку юнит, определяет здание на ферме
 */
public class ProfileBullet {

    private String bullet;

    private int level;

    public String getBulletName() {
        return bullet;
    }

    public int getLevel() {
        return level;
    }

    public void setBulletClass(Class<? extends AbstractBullet> bulletClass) {
        bullet = bulletClass.getName();
    }

    public void setLevel(int value) {
        level = value;
    }

}
