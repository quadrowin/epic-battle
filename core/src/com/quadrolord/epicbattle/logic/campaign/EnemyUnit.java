package com.quadrolord.epicbattle.logic.campaign;

import com.badlogic.gdx.utils.ArrayMap;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.bullet.worker.big.BigLogic;
import com.quadrolord.epicbattle.logic.bullet.worker.simple.SimpleLogic;

/**
 * Created by Quadrowin on 12.01.2016.
 * Настройки инита, доступного вражеской башне
 */
public class EnemyUnit {

    private static ArrayMap<String, Class<? extends AbstractLogic>> workerTypes = new ArrayMap<String, Class<? extends AbstractLogic>>();

    {
        workerTypes.put("SimpleLogic", SimpleLogic.class);
        workerTypes.put("BigBullet", BigLogic.class);
    }

    /**
     * Класс юнита
     */
    private String workerType = "SimpleLogic";

    /**
     * Первая секунда, когда вызывается этот юнит
     */
    private float firstTime = 5;

    /**
     * Задержка перед вызовом этого юнита
     */
    private float deltaTime = 3;

    private int level = 1;

    public Class<? extends AbstractLogic> getWorkerClass() {
        return workerTypes.get(workerType);
    }

    public float getFirstTime() {
        return firstTime;
    }

    public float getDeltaTime() {
        return deltaTime;
    }

}
