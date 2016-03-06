package com.quadrolord.epicbattle.logic.skill;

import com.badlogic.gdx.utils.ArrayMap;

/**
 * Created by Quadrowin on 06.03.2016.
 */
public class SkillManager {

    private ArrayMap<Class, AbstractSkillEntity> mSkills = new ArrayMap<Class, AbstractSkillEntity>();

    public AbstractSkillEntity get(Class <? extends AbstractSkillEntity> clazz) {
        if (mSkills.containsKey(clazz)) {
            return mSkills.get(clazz);
        }
        AbstractSkillEntity skill;
        try {
            skill = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            skill = new DummySkill();
        }
        mSkills.put(clazz, skill);
        return skill;
    }

}
