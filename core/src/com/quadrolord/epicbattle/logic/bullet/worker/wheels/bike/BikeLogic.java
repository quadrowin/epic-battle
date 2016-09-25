package com.quadrolord.epicbattle.logic.bullet.worker.wheels.bike;

import com.quadrolord.epicbattle.logic.bullet.leveling.SimpleStrategy;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.view.bullet.wheels.Bike;

/**
 * Created by Quadrowin on 10.07.2016.
 */
public class BikeLogic extends AbstractLogic<BikeBullet> {

    public BikeLogic() {
        setHeight(80);
        setWidth(80);
        setDescription("I will fight for you to the end.");
        setViewClass(Bike.class);
        setLevelingStrategy(new SimpleStrategy());
    }

    @Override
    public void initBullet(SkillItem skill, AbstractBullet bullet) {
        initBulletBase(skill, bullet);
    }

}

