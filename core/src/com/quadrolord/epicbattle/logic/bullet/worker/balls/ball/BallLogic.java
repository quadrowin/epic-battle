package com.quadrolord.epicbattle.logic.bullet.worker.balls.ball;

import com.quadrolord.epicbattle.logic.bullet.leveling.X15Strategy;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.view.bullet.balls.Ball;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class BallLogic extends AbstractLogic<BallBullet> {

    public BallLogic() {
        setHeight(40);
        setWidth(40);
        setTitle("The ball");
        setDescription("The magic ball inside another magic ball.\n" +
                "Very very magic magic.");
        setCost(50);
        setConstructionTime(3);
        setAttackDamage(50);
        setAttackDistance(20);
        setAttackTime(1);
        setMoveSpeed(50);
        setMaxTargetCount(1);
        setMaxHp(300);
        setIcon("balls/bubble1.png");
        setViewClass(Ball.class);
        setLevelingStrategy(new X15Strategy());
    }

    @Override
    public void initBullet(SkillItem skill, AbstractBullet bullet) {
        initBulletBase(skill, bullet);
    }

}
