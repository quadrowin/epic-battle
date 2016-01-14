package com.quadrolord.epicbattle.logic.bullet;

/**
 * Created by morph on 14.01.2016.
 */
public abstract class BulletLevelUp {
    public BulletLevelUpDto getDdo() {
        return new BulletLevelUpDto();
    }

    public abstract BulletLevelUpDto action();
}