package com.quadrolord.epicbattle.logic.campaign;

import com.badlogic.gdx.utils.ArrayMap;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.Big;
import com.quadrolord.epicbattle.logic.bullet.worker.Simple;

/**
 * Created by Quadrowin on 12.01.2016.
 * Настройки инита, доступного вражеской башне
 */
public class EnemyUnit {

    private static ArrayMap<String, Class<? extends AbstractBullet>> workerTypes = new ArrayMap<String, Class<? extends AbstractBullet>>();

    {
        workerTypes.put("Simple", Simple.class);
        workerTypes.put("Big", Big.class);
    }

    /**
     * Класс юнита
     */
    private String workerType = "Simple";

    /**
     * Первая секунда, когда вызывается этот юнит
     */
    private float firstTime = 5;

    /**
     * Задержка перед вызовом этого юнита
     */
    private float deltaTime = 3;

    private int level = 1;

    public Class<? extends AbstractBullet> getWorkerClass() {
        return workerTypes.get(workerType);
    }

    public float getFirstTime() {
        return firstTime;
    }

    public float getDeltaTime() {
        return deltaTime;
    }

}
