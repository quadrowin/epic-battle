package com.quadrolord.epicbattle.logic.bullet.worker.big;

import com.quadrolord.epicbattle.logic.bullet.leveling.SimpleStrategy;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.skill.SkillItem;

/**
 * Created by Quadrowin on 02.07.2016.
 */
public class BigLogic extends AbstractLogic<BigBullet> {

    public BigLogic() {
        setDescription("He is real strong. He is real BIG. He will bring you victory.");
        setViewClass(com.quadrolord.epicbattle.view.worker.Big.class);
        setLevelingStrategy(new SimpleStrategy());
    }

    @Override
    public void initBullet(SkillItem skill, AbstractBullet bullet) {
        initBulletBase(skill, bullet);
    }

}
