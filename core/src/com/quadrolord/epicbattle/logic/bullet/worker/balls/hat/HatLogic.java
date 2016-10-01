package com.quadrolord.epicbattle.logic.bullet.worker.balls.hat;

import com.quadrolord.epicbattle.logic.bullet.leveling.X15Strategy;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.view.bullet.balls.Hat;

/**
 * Created by Quadrowin on 02.07.2016.
 */
public class HatLogic extends AbstractLogic<HatBullet> {

    public HatLogic() {
        setHeight(40);
        setWidth(40);
        setTitle("The hat");
        setDescription("An old hat of the some forgotten wizard.");
        setCost(500);
        setConstructionTime(10);
        setAttackDamage(70);
        setAttackDistance(20);
        setAttackTime(2);
        setMoveSpeed(30);
        setMaxTargetCount(2);
        setMaxHp(300);
        setIcon("balls/content/hat.png");
        setViewClass(Hat.class);
        setLevelingStrategy(new X15Strategy());
    }

    @Override
    public void initBullet(SkillItem skill, AbstractBullet bullet) {
        initBulletBase(skill, bullet);
    }

}
