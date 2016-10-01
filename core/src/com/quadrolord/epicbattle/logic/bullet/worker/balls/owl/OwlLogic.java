package com.quadrolord.epicbattle.logic.bullet.worker.balls.owl;

import com.quadrolord.epicbattle.logic.bullet.leveling.X15Strategy;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.view.bullet.balls.Owl;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class OwlLogic extends AbstractLogic<OwlBullet> {

    public OwlLogic() {
        setHeight(40);
        setWidth(40);
        setTitle("Owl");
        setDescription("The smarty owl. He doesn't fly. Too clever and old for it.");
        setCost(50);
        setConstructionTime(9);
        setAttackDamage(50);
        setAttackDistance(20);
        setAttackTime(1);
        setMoveSpeed(50);
        setMaxTargetCount(1);
        setMaxHp(100);
        setIcon("balls/content/owl.png");
        setViewClass(Owl.class);
        setLevelingStrategy(new X15Strategy());
    }

    @Override
    public void initBullet(SkillItem skill, AbstractBullet bullet) {
        initBulletBase(skill, bullet);
    }

}
