package com.quadrolord.epicbattle.screen.battle;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.quadrolord.epicbattle.logic.tower.Tower;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.screen.SES;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class Cash extends Group {

    private Label mLabel;

    private Tower mTower;

    public Cash(Tower tower, AbstractScreen screen, Stage stage) {
        mTower = tower;
        mLabel = new Label(
                Integer.toString((int)tower.getCash()),
                screen.getSkin().get("default-label-style", Label.LabelStyle.class)
        );
        mLabel.setBounds(0, 0, getWidth(), getHeight());
        addActor(mLabel);

        setBounds(50 * SES.F, 200 * SES.F, 50, 50);
        stage.addActor(this);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        mLabel.setText(Integer.toString((int)mTower.getCash()));
    }

}
