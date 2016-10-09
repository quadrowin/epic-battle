package com.quadrolord.epicbattle.screen.battle;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.quadrolord.epicbattle.RM;
import com.quadrolord.epicbattle.logic.campaign.Level;
import com.quadrolord.epicbattle.screen.SES;

/**
 * Created by Quadrowin on 11.01.2016.
 */
public class LevelName extends Group {

    private float mTime = 0;

    private float mSpeed = 40;

    public LevelName(Level level, Stage stage) {
        setBounds(0, SES.SCREEN_HEIGHT, SES.SCREEN_WIDTH, 30 * SES.F);

        Label label = new Label(level.getName(), RM.getLabelStyle());
        label.setBounds(0, 0, getWidth(), getHeight());
        label.setAlignment(Align.center, Align.bottom);
        addActor(label);

        stage.addActor(this);
    }

    @Override
    public void act(float delta) {
        mTime += delta;
        if (mTime < 2) {
            setY(getY() - mSpeed * delta * SES.F);
            return;
        }
        if (mTime < 4) {
            return;
        }
        if (mTime < 6) {
            setY(getY() + mSpeed * delta * SES.F);
            return;
        }
        remove();
    }

}
