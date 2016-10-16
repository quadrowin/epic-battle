package com.quadrolord.epicbattle.logic.bullet.worker.balls.butterfly;

import com.quadrolord.epicbattle.logic.bullet.leveling.X15Strategy;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.view.bullet.balls.Butterfly;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class ButterflyLogic extends AbstractLogic<BatterflyBullet> {

    public ButterflyLogic() {
        setWidthHeight(30);
        setTitle("Butterfly");
        setDescription("The child of darkness and absurdity.\n" +
                "It can fly!");
        setCost(50);
        setConstructionTime(3);
        setAttackDamage(50);
        setAttackDistance(getWidth() / 2);
        setAttackTime(1);
        setMoveSpeed(50);
        setMaxTargetCount(1);
        setMaxHp(300);
        setIcon("balls/content/butterfly.png");
        setViewClass(Butterfly.class);
        setLevelingStrategy(new X15Strategy());
    }

    @Override
    public void initBullet(SkillItem skill, AbstractBullet bullet) {
        initBulletBase(skill, bullet);
    }

}
