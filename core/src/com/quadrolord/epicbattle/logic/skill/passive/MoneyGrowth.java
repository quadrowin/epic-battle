package com.quadrolord.epicbattle.logic.skill.passive;

import com.quadrolord.epicbattle.logic.skill.AbstractSkillEntity;
import com.quadrolord.epicbattle.logic.skill.SkillItem;

/**
 * Created by Quadrowin on 03.07.2016.
 */
public class MoneyGrowth extends AbstractSkillEntity {

    public MoneyGrowth() {
        setDescription("Increase in battle cash growth.");
        setName("Money Growth");
        setIcon("icons/skills/wave.png");
    }

    @Override
    public void act(SkillItem skill, float delta) {
        float delta_cash = (skill.getLevel() + 1) * 50 * delta;
        skill.getTower().setCash( skill.getTower().getCash() + delta_cash );
    }



}
