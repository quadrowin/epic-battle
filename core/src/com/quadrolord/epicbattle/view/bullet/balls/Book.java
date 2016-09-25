package com.quadrolord.epicbattle.view.bullet.balls;

import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.view.bullet.AbstractBallView;

/**
 * Created by Goorus on 25.09.2016.
 */

public class Book extends AbstractBallView {

    public Book(AbstractBullet bullet, AbstractScreen screen) {
        super(bullet, screen);
    }

    @Override
    protected String getContentTextureFile() {
        return "balls/content/book1.png";
    }

}
