package com.quadrolord.epicbattle.logic.profile;

import com.quadrolord.epicbattle.logic.skill.AbstractSkillEntity;
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

    public Class<? extends AbstractSkillEntity> getSkillClass() {
        try {
            return (Class<? extends AbstractSkillEntity>) Class.forName(skill);
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

    public void setSkillClass(Class<? extends AbstractSkillEntity> skillClass) {
        skill = skillClass.getName();
    }

}
