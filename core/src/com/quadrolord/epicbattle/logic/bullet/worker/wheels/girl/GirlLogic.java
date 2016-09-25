package com.quadrolord.epicbattle.logic.bullet.worker.wheels.girl;

import com.quadrolord.epicbattle.logic.bullet.leveling.X15Strategy;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.view.bullet.wheels.Girl;

/**
 * Created by Quadrowin on 20.01.2016.
 */
public class GirlLogic extends AbstractLogic<GirlBullet> {

    public GirlLogic() {
        setHeight(40);
        setWidth(40);
        setDescription("Beauty is a horrible force.");
        setViewClass(Girl.class);
        setLevelingStrategy(new X15Strategy());
    }

    @Override
    public void initBullet(SkillItem skill, AbstractBullet bullet) {
        initBulletBase(skill, bullet);
    }

}
