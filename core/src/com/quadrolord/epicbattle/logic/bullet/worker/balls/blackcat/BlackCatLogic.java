package com.quadrolord.epicbattle.logic.bullet.worker.balls.blackcat;

import com.quadrolord.epicbattle.logic.bullet.leveling.X15Strategy;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.view.bullet.balls.BlackCat;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class BlackCatLogic extends AbstractLogic<BlackCatBullet> {

    public BlackCatLogic() {
        setWidthHeight(30);
        setTitle("Black cat");
        setDescription("He is great and he knows it.");
        setCost(50);
        setConstructionTime(3);
        setAttackDamage(50);
        setAttackDistance(getWidth() / 2);
        setAttackTime(1);
        setMoveSpeed(50);
        setMaxTargetCount(1);
        setMaxHp(300);
        setIcon("balls/content/black_cat.png");
        setViewClass(BlackCat.class);
        setLevelingStrategy(new X15Strategy());
    }

    @Override
    public void initBullet(SkillItem skill, AbstractBullet bullet) {
        initBulletBase(skill, bullet);
    }

}
