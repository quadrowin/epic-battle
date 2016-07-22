package com.quadrolord.epicbattle.logic.bullet.worker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
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

    public void act(float delta) {
        Gdx.app.log("MockBullet", "st " + getState().name() + " time " + getStateTime());
        if (getState() == BulletState.DEATH && getStateTime() > 3) {
            setState(BulletState.DEATH, 3);
            setStateTime(0);
        } else {
            setStateTime(getStateTime() + delta);
        }
    }

    public void setLogic(AbstractLogic logic) {
        mLogic = logic;
        setHeight(logic.getHeight());
        setWidth(logic.getWidth());
    }

    public void moveX(float delta, float stageWidth) {
        float halfWidth = getWidth() / 2;
        float originalX = getX() - halfWidth;
        float newX;
        float speed = 40;

        BulletState state = getState();
        if (state == BulletState.RUN || state == BulletState.WALK) {
            if (originalX > stageWidth) {
                newX = -getWidth();
            } else {
                newX = originalX + delta * speed;
            }
        } else {
            float centerX = stageWidth / 2;
            float deltaX = centerX - originalX;
            float stepLength = delta * speed;
            if (Math.abs(deltaX) < stepLength) {
                newX = centerX;
            } else {
                newX = originalX + Math.signum(deltaX) * stepLength;
            }
        }


        setX(newX + halfWidth);
    }

}
