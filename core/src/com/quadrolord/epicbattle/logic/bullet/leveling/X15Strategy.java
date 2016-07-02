package com.quadrolord.epicbattle.logic.bullet.leveling;

import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.skill.AbstractBulletSkill;

/**
 * Created by Quadrowin on 23.01.2016.
 */
public class X15Strategy extends AbstractStrategy {

    @Override
    public void setLevel(AbstractBulletSkill skill, int level) {
        AbstractLogic info = skill.getBulletLogic();
        skill.assignBase(info);
        float factor = (float) Math.pow(1.15, level);
        skill.setAttackDamage(info.getAttackDamage() * factor);
        skill.setMaxHp((int)(info.getMaxHp() * factor));
        skill.setCost((int)(info.getCost() * factor));
    }

}
