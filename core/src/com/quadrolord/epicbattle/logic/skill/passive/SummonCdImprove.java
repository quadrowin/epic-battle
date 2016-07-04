package com.quadrolord.epicbattle.logic.skill.passive;

import com.quadrolord.epicbattle.logic.skill.AbstractSkillEntity;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.logic.tower.Tower;

/**
 * Created by Quadrowin on 04.07.2016.
 */
public class SummonCdImprove extends AbstractSkillEntity {

    @Override
    public void initTower(SkillItem skill, Tower tower) {
        float ratio = Math.max(0.25f, 1 - 0.05f * skill.getLevel());
        tower.setSummonSkillCdRatio(ratio);
    }

}
