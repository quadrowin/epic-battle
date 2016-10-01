package com.quadrolord.epicbattle.logic.bullet.worker.balls.horseshoe;

import com.quadrolord.epicbattle.logic.bullet.leveling.X15Strategy;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.view.bullet.balls.Horseshoe;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class HorseshoeLogic extends AbstractLogic<HorseshoeBullet> {

    public HorseshoeLogic() {
        setHeight(40);
        setWidth(40);
        setTitle("Horseshoe");
        setDescription("Can bring you a luck. But likely not.");
        setCost(50);
        setConstructionTime(3);
        setAttackDamage(50);
        setAttackDistance(20);
        setAttackTime(1);
        setMoveSpeed(50);
        setMaxTargetCount(1);
        setMaxHp(300);
        setIcon("balls/content/horseshoe.png");
        setViewClass(Horseshoe.class);
        setLevelingStrategy(new X15Strategy());
    }

    @Override
    public void initBullet(SkillItem skill, AbstractBullet bullet) {
        initBulletBase(skill, bullet);
    }

}
