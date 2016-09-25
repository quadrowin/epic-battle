package com.quadrolord.epicbattle.logic.bullet.worker.balls.Broom;

import com.quadrolord.epicbattle.logic.bullet.leveling.SimpleStrategy;
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
        setDescription("I will fight for you to the end.");
        setViewClass(Broom.class);
        setLevelingStrategy(new SimpleStrategy());
    }

    @Override
    public void initBullet(SkillItem skill, AbstractBullet bullet) {
        initBulletBase(skill, bullet);
    }

}
