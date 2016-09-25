package com.quadrolord.epicbattle.logic.bullet.worker.wheels.big;

import com.quadrolord.epicbattle.logic.bullet.leveling.SimpleStrategy;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.view.bullet.wheels.Big;

/**
 * Created by Quadrowin on 02.07.2016.
 */
public class BigLogic extends AbstractLogic<BigBullet> {

    public BigLogic() {
        setHeight(90);
        setWidth(90);
        setDescription("He is real strong. He is real BIG. He will bring you victory.");
        setViewClass(Big.class);
        setLevelingStrategy(new SimpleStrategy());
    }

    @Override
    public void initBullet(SkillItem skill, AbstractBullet bullet) {
        initBulletBase(skill, bullet);
    }

}
