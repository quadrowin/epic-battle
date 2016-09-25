package com.quadrolord.epicbattle.logic.bullet.worker.wheels.epic;

import com.quadrolord.epicbattle.logic.bullet.leveling.SimpleStrategy;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.view.bullet.wheels.Epic;

/**
 * Created by Goorus on 28.07.2016.
 */
public class EpicLogic extends AbstractLogic<EpicBullet> {

    public EpicLogic() {
        setHeight(40);
        setWidth(40);
        setDescription("A very very old wheel.");
        setViewClass(Epic.class);
        setLevelingStrategy(new SimpleStrategy());
    }

    @Override
    public void initBullet(SkillItem skill, AbstractBullet bullet) {
        initBulletBase(skill, bullet);
    }

}

