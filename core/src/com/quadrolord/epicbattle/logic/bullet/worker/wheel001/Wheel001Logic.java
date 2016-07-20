package com.quadrolord.epicbattle.logic.bullet.worker.wheel001;

import com.quadrolord.epicbattle.logic.bullet.leveling.SimpleStrategy;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.skill.SkillItem;

/**
 * Created by Quadrowin on 10.07.2016.
 */
public class Wheel001Logic extends AbstractLogic<Wheel001Bullet> {

    public Wheel001Logic() {
        setHeight(40);
        setWidth(40);
        setDescription("I will fight for you to the end.");
        setViewClass(com.quadrolord.epicbattle.view.worker.Wheel.class);
        setLevelingStrategy(new SimpleStrategy());
    }

    @Override
    public void initBullet(SkillItem skill, AbstractBullet bullet) {
        initBulletBase(skill, bullet);
    }

}

