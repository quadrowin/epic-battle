package com.quadrolord.epicbattle.logic.bullet.worker.balls.dice;

import com.quadrolord.epicbattle.logic.bullet.leveling.X15Strategy;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.view.bullet.balls.Dice;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class DiceLogic extends AbstractLogic<DiceBullet> {

    public DiceLogic() {
        setWidthHeight(30);
        setTitle("The dice");
        setDescription("Drop it and see what happens. Nothing? It's according a plan.");
        setCost(50);
        setConstructionTime(3);
        setAttackDamage(50);
        setAttackDistance(getWidth() / 2);
        setAttackTime(1);
        setMoveSpeed(50);
        setMaxTargetCount(1);
        setMaxHp(300);
        setIcon("balls/content/dice.png");
        setViewClass(Dice.class);
        setLevelingStrategy(new X15Strategy());
    }

    @Override
    public void initBullet(SkillItem skill, AbstractBullet bullet) {
        initBulletBase(skill, bullet);
    }

}
