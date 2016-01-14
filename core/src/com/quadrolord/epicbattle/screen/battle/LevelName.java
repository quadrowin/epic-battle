package com.quadrolord.epicbattle.screen.battle;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.quadrolord.epicbattle.logic.campaign.Level;

/**
 * Created by Quadrowin on 11.01.2016.
 */
public class LevelName extends Group {

    private float mTime = 0;

    public LevelName(Level level, Skin skin, Stage stage) {
        setBounds(0, 300, 400, 30);

        Label label = new Label(level.getName(), skin.get("default-label-style", Label.LabelStyle.class));
        label.setBounds(0, 0, getWidth(), getHeight());
        label.setAlignment(Align.center, Align.center);
        addActor(label);

        stage.addActor(this);
    }

    @Override
    public void act(float delta) {
        mTime += delta;
        if (mTime < 2) {
            setY(getY() - 40 * delta);
            return;
        }
        if (mTime < 4) {
            return;
        }
        if (mTime < 5) {
            setY(getY() + 40 * delta);
            return;
        }
        remove();
    }

}
