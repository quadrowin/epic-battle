package com.quadrolord.epicbattle.logic.bullet.worker.balls.spider;

import com.quadrolord.epicbattle.logic.bullet.leveling.X15Strategy;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.view.bullet.balls.Spider;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class SpiderLogic extends AbstractLogic<SpiderBullet> {

    public SpiderLogic() {
        setHeight(40);
        setWidth(40);
        setTitle("Spider");
        setDescription("He can cast eight spells at the same time.");
        setCost(50);
        setConstructionTime(3);
        setAttackDamage(50);
        setAttackDistance(20);
        setAttackTime(1);
        setMoveSpeed(50);
        setMaxTargetCount(1);
        setMaxHp(300);
        setIcon("balls/content/spider.png");
        setViewClass(Spider.class);
        setLevelingStrategy(new X15Strategy());
    }

    @Override
    public void initBullet(SkillItem skill, AbstractBullet bullet) {
        initBulletBase(skill, bullet);
    }

}
