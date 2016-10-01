package com.quadrolord.epicbattle.logic.bullet.worker.balls.batterfly;

import com.quadrolord.epicbattle.logic.bullet.leveling.X15Strategy;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.view.bullet.balls.Bat;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class BatterflyLogic extends AbstractLogic<BatterflyBullet> {

    public BatterflyLogic() {
        setHeight(40);
        setWidth(40);
        setTitle("Batterfly");
        setDescription("The child of darkness and absurdity.\n" +
                "It can fly!");
        setCost(50);
        setConstructionTime(3);
        setAttackDamage(50);
        setAttackDistance(20);
        setAttackTime(1);
        setMoveSpeed(50);
        setMaxTargetCount(1);
        setMaxHp(300);
        setIcon("balls/content/batterfly.png");
        setViewClass(Bat.class);
        setLevelingStrategy(new X15Strategy());
    }

    @Override
    public void initBullet(SkillItem skill, AbstractBullet bullet) {
        initBulletBase(skill, bullet);
    }

}
