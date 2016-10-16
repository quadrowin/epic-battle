package com.quadrolord.epicbattle.logic.bullet.worker.balls.magicwand;

import com.quadrolord.epicbattle.logic.bullet.leveling.X15Strategy;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.view.bullet.balls.MagicWand;

/**
 * Created by Quadrowin on 10.07.2016.
 */
public class MagicWandLogic extends AbstractLogic<MagicWandBullet> {

    public MagicWandLogic() {
        setWidthHeight(30);
        setDescription("Magic oak log with the star at the top.");
        setTitle("Magic Wand");
        setCost(50);
        setConstructionTime(3);
        setAttackDamage(50);
        setAttackDistance(getWidth() / 2);
        setAttackTime(1);
        setMoveSpeed(30);
        setMaxTargetCount(1);
        setMaxHp(300);
        setIcon("balls/content/magic_wand.png");
        setViewClass(MagicWand.class);
        setLevelingStrategy(new X15Strategy());
    }

    @Override
    public void initBullet(SkillItem skill, AbstractBullet bullet) {
        initBulletBase(skill, bullet);
    }

}

