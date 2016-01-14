package com.quadrolord.epicbattle.logic.profile;

import com.quadrolord.epicbattle.logic.skill.AbstractSkill;
import com.quadrolord.epicbattle.logic.skill.DummySkill;

/**
 * Created by Quadrowin on 14.01.2016.
 */
public class ProfileSkill {

    private String skill;

    private int level;

    public String getSkillName() {
        return skill;
    }

    public Class<? extends AbstractSkill> getSkillClass() {
        try {
            return (Class<? extends AbstractSkill>) Class.forName(skill);
        } catch (Exception e) {
            return DummySkill.class;
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int value) {
        level = value;
    }

    public void setSkillClass(Class<? extends AbstractSkill> skillClass) {
        skill = skillClass.getName();
    }

}
