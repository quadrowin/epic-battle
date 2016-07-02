package com.quadrolord.epicbattle.logic.skill;

import com.badlogic.gdx.utils.ArrayMap;
import com.quadrolord.epicbattle.logic.tower.BattleGame;

/**
 * Загрузчик логик скилов
 * Created by Quadrowin on 06.03.2016.
 */
public class SkillManager {

    private BattleGame mGame;

    private ArrayMap<Class, AbstractSkillEntity> mSkills = new ArrayMap<Class, AbstractSkillEntity>();

    public SkillManager(BattleGame game) {
        mGame = game;
    }

    public AbstractSkillEntity get(Class <? extends AbstractSkillEntity> clazz) {
        if (mSkills.containsKey(clazz)) {
            return mSkills.get(clazz);
        }
        AbstractSkillEntity skill;
        try {
            AbstractBulletSkill.debug = 1;
            skill = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            skill = new DummySkill();
        }
        skill.initSkill(mGame);
        mSkills.put(clazz, skill);
        return skill;
    }

}
