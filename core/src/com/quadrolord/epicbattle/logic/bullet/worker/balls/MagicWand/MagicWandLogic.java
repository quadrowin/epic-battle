package com.quadrolord.epicbattle.logic.bullet.worker.balls.MagicWand;

import com.quadrolord.epicbattle.logic.bullet.leveling.SimpleStrategy;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.view.bullet.balls.MagicWand;

/**
 * Created by Quadrowin on 10.07.2016.
 */
public class MagicWandLogic extends AbstractLogic<MagicWandBullet> {

    public MagicWandLogic() {
        setHeight(40);
        setWidth(40);
        setDescription("I will fight for you to the end.");
        setViewClass(MagicWand.class);
        setLevelingStrategy(new SimpleStrategy());
    }

    @Override
    public void initBullet(SkillItem skill, AbstractBullet bullet) {
        initBulletBase(skill, bullet);
    }

}

