package com.quadrolord.epicbattle.logic.bullet.leveling;

import com.quadrolord.epicbattle.logic.bullet.BulletInfo;
import com.quadrolord.epicbattle.logic.bullet.BulletSkill;

/**
 * Created by Quadrowin on 23.01.2016.
 */
public class X15Strategy extends AbstractStrategy {

    @Override
    public void setLevel(BulletSkill skill, int level) {
        BulletInfo info = skill.getInfo();
        skill.assignBase(info);
        float factor = (float) Math.pow(1.15, level);
        skill.setAttackDamage(info.getAttackDamage() * factor);
        skill.setMaxHp((int)(info.getMaxHp() * factor));
    }

}
