package com.quadrolord.epicbattle.logic.ai;

import com.quadrolord.epicbattle.logic.skill.AbstractSkillEntity;
import com.quadrolord.epicbattle.logic.skill.bullet.balls.Snake;
import com.quadrolord.epicbattle.logic.tower.Tower;

/**
 * Created by Quadrowin on 10.01.2016.
 */
public class TowerAi {

    private float mTime = 0;

    private Tower mTower;

    private AbstractSkillEntity mMobSkill;

    public TowerAi(Tower tower) {
        mTower = tower;
        mMobSkill = tower.getGame().getSkillManager().get(Snake.class);
    }

    public void act(float delta) {
        int frame1 = (int)(mTime / 3);
        mTime += delta;
        int frame2 = (int)(mTime / 3);
        if (frame2 > frame1) {
            mTower.getGame().createUnit(mTower, mTower.getBulletSkill(mMobSkill), false, false);
        }
    }

}
