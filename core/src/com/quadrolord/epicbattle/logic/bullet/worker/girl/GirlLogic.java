package com.quadrolord.epicbattle.logic.bullet.worker.girl;

import com.quadrolord.epicbattle.logic.bullet.leveling.X15Strategy;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.skill.SkillItem;

/**
 * Created by Quadrowin on 20.01.2016.
 */
public class GirlLogic extends AbstractLogic<GirlBullet> {

    public GirlLogic() {
        setHeight(50);
        setWidth(39);
        setDescription("Beauty is a horrible force.");
        setViewClass(com.quadrolord.epicbattle.view.worker.Girl.class);
        setLevelingStrategy(new X15Strategy());
    }

    @Override
    public void initBullet(SkillItem skill, AbstractBullet bullet) {
        initBulletBase(skill, bullet);
    }

}
