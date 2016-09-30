package com.quadrolord.epicbattle.logic.bullet.worker.balls.bookball;

import com.quadrolord.epicbattle.logic.bullet.leveling.SimpleStrategy;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.view.bullet.balls.Book;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class BookLogic extends AbstractLogic<BookBullet> {

    public BookLogic() {
        setHeight(40);
        setWidth(40);
        setDescription("I will fight for you to the end.");
        setViewClass(Book.class);
        setLevelingStrategy(new SimpleStrategy());
    }

    @Override
    public void initBullet(SkillItem skill, AbstractBullet bullet) {
        initBulletBase(skill, bullet);
    }

}
