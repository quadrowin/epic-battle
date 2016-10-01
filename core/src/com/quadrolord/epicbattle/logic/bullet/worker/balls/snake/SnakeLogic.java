package com.quadrolord.epicbattle.logic.bullet.worker.balls.snake;

import com.quadrolord.epicbattle.logic.bullet.leveling.X15Strategy;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.view.bullet.balls.Snake;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class SnakeLogic extends AbstractLogic<SnakeBullet> {

    public SnakeLogic() {
        setHeight(40);
        setWidth(40);
        setTitle("Little snake");
        setDescription("It's long.");
        setCost(50);
        setConstructionTime(3);
        setAttackDamage(50);
        setAttackDistance(20);
        setAttackTime(1);
        setMoveSpeed(50);
        setMaxTargetCount(1);
        setMaxHp(300);
        setIcon("balls/content/snake.png");
        setViewClass(Snake.class);
        setLevelingStrategy(new X15Strategy());
    }

    @Override
    public void initBullet(SkillItem skill, AbstractBullet bullet) {
        initBulletBase(skill, bullet);
    }

}
