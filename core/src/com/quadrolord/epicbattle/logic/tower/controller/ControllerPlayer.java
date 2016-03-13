package com.quadrolord.epicbattle.logic.tower.controller;

import com.badlogic.gdx.utils.Queue;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.tower.BattleGame;

/**
 * Created by Quadrowin on 12.01.2016.
 */
public class ControllerPlayer extends AbstractController {

    private Queue<Class<? extends AbstractBullet>> mFireCalls = new Queue<Class<? extends AbstractBullet>>();

    public ControllerPlayer(BattleGame game) {
        super(game);
    }

    @Override
    public void act(float delta) {
        for (; mFireCalls.size > 0; ) {
            Class<? extends AbstractBullet> bulletClass = mFireCalls.removeFirst();
            getGame().createUnit(getTower(), bulletClass);
        }
    }

    public void callFire(Class<? extends AbstractBullet> bulletClass) {
        mFireCalls.addLast(bulletClass);
    }

    public void reset() {
        mFireCalls.clear();
    }

}
