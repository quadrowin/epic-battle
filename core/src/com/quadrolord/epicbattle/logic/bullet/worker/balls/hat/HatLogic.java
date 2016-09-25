package com.quadrolord.epicbattle.logic.bullet.worker.balls.hat;

import com.quadrolord.epicbattle.logic.bullet.leveling.SimpleStrategy;
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
        setDescription("He is real strong. He is real BIG. He will bring you victory.");
        setViewClass(Hat.class);
        setLevelingStrategy(new SimpleStrategy());
    }

    @Override
    public void initBullet(SkillItem skill, AbstractBullet bullet) {
        initBulletBase(skill, bullet);
    }

}
