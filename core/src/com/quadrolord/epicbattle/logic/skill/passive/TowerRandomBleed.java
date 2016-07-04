package com.quadrolord.epicbattle.logic.skill.passive;

import com.badlogic.gdx.Gdx;
import com.quadrolord.epicbattle.logic.skill.AbstractSkillEntity;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.logic.tower.Tower;

/**
 * Created by Quadrowin on 14.01.2016.
 */
public class TowerRandomBleed extends AbstractSkillEntity {

    public TowerRandomBleed() {
        setDescription("Sometimes your tower bleeding. Nothing more.");
        setName("Random bleed");
        setIcon("icons/skills/random_bleed.png");
        setIsPassive(true);
    }

    @Override
    public void act(SkillItem skill, float delta) {
        float time = skill.getTime() + delta;
        //Gdx.app.log("TowerRandomBleed.act", "mTime = " + mTime);
        if (time < 3) {
            skill.setTime(time);
            return ;
        }
        time -= 3;
        skill.setTime(time);
        Tower tower = skill.getTower();
        tower.getGame().getListener().onVisualEvent(
                tower.getX(),
                60,
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
    public void initTower(SkillItem skill, Tower tower) {
        skill.setTime(0);
        skill.setTower(tower);
        Gdx.app.log("initTower", "TowerRandomBleed");
    }

}
