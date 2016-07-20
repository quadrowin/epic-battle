package com.quadrolord.epicbattle.logic.bullet.worker.forks;

import com.quadrolord.epicbattle.logic.bullet.leveling.SimpleStrategy;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.skill.SkillItem;

/**
 * Юнит раздваивается на 2 при смерти
 */
public class ForksLogic extends AbstractLogic<ForksBullet> {

    public ForksLogic() {
        setHeight(50);
        setWidth(39);
        setDescription("We are not the one.");
        setViewClass(com.quadrolord.epicbattle.view.worker.Forks.class);
        setLevelingStrategy(new SimpleStrategy());
    }

    @Override
    public void initBullet(SkillItem skill, AbstractBullet bullet) {
        initBulletBase(skill, bullet);
    }

    @Override
    public void onDeath(AbstractBullet blt) {
        ForksBullet fb = (ForksBullet)blt;
        if (fb.getStage() < 2) {
            for (int i = 0; i < 2; i++) {
                ForksBullet child = (ForksBullet)fb.getGame().createUnit(fb.getTower(), fb.getSkill(), false, false);
                child.setStage(fb.getStage() + 1);
                child.setX(fb.getX() - fb.getVelocity() * 1);
            }
        }

        super.onDeath(blt);
    }

}
