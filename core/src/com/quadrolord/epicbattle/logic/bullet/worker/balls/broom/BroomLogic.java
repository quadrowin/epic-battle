package com.quadrolord.epicbattle.logic.bullet.worker.balls.broom;

import com.quadrolord.epicbattle.logic.bullet.leveling.X15Strategy;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.view.bullet.balls.Broom;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class BroomLogic extends AbstractLogic<BroomBullet> {

    public BroomLogic() {
        setHeight(50);
        setWidth(50);
        setTitle("The broom");
        setDescription("Just the flying broom.");
        setCost(75);
        setConstructionTime(3);
        setAttackDamage(50);
        setAttackDistance(20);
        setAttackTime(1);
        setMoveSpeed(50);
        setMaxTargetCount(1);
        setMaxHp(100);
        setIcon("balls/content/broom.png");
        setViewClass(Broom.class);
        setLevelingStrategy(new X15Strategy());
    }

    @Override
    public void initBullet(SkillItem skill, AbstractBullet bullet) {
        initBulletBase(skill, bullet);
    }

}
