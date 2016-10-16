package com.quadrolord.epicbattle.logic.bullet.worker.balls.book;

import com.quadrolord.epicbattle.logic.bullet.leveling.X15Strategy;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.view.bullet.balls.Book;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class BookLogic extends AbstractLogic<BookBullet> {

    public BookLogic() {
        setWidthHeight(30);
        setTitle("The book");
        setDescription("The very big and hard book.");
        setCost(50);
        setConstructionTime(3);
        setAttackDamage(50);
        setAttackDistance(getWidth() / 2);
        setAttackTime(1);
        setMoveSpeed(50);
        setMaxTargetCount(1);
        setMaxHp(300);
        setIcon("balls/content/book1.png");
        setViewClass(Book.class);
        setLevelingStrategy(new X15Strategy());
    }

    @Override
    public void initBullet(SkillItem skill, AbstractBullet bullet) {
        initBulletBase(skill, bullet);
    }

}
