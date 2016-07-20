package com.quadrolord.epicbattle.logic.bullet.worker;

import com.quadrolord.epicbattle.logic.bullet.loader.AbstractLoader;
import com.quadrolord.epicbattle.logic.tower.BattleGame;

/**
 * Created by Goorus on 20.07.2016.
 */
public class MockBullet extends AbstractBullet {

    private AbstractLogic mLogic;

    public MockBullet(BattleGame game) {
        super(game);
    }

    public void setLogic(AbstractLogic logic) {
        mLogic = logic;
        setHeight(logic.getHeight());
        setWidth(logic.getWidth());
    }

}
