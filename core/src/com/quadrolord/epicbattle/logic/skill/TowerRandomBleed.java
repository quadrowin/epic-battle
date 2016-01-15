package com.quadrolord.epicbattle.logic.skill;

import com.badlogic.gdx.Gdx;
import com.quadrolord.epicbattle.logic.tower.Tower;

/**
 * Created by Quadrowin on 14.01.2016.
 */
public class TowerRandomBleed extends AbstractSkill {

    private float mTime;

    private Tower mTower;

    @Override
    public void act(float delta) {
        mTime += delta;
        //Gdx.app.log("TowerRandomBleed.act", "mTime = " + mTime);
        if (mTime < 3) {
            return ;
        }
        mTime = 0;
        mTower.getGame().getListener().onVisualEvent(
                mTower.getX(),
                20,
                getClass()
        );
    }

    public String getTitle() {
        return "Random bleed";
    }

    public String getDescription() {
        return "Sometimes your tower bleed.";
    }

    /**
     * Инициализация башни в начале уровня
     * @param tower
     */
    @Override
    public void initTower(Tower tower) {
        mTime = 0;
        mTower = tower;
        tower.addActSkill(this);
        Gdx.app.log("initTower", "TowerRandomBleed");
    }

}
