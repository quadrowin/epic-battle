package com.quadrolord.epicbattle.screen.battle;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.quadrolord.epicbattle.logic.Tower;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class CashLabel extends Group {

    private Label mLabel;

    private Tower mTower;

    public CashLabel(Tower tower, AbstractScreen screen, Stage stage) {
        mTower = tower;
        mLabel = new Label(
                Integer.toString((int)tower.getCash()),
                screen.getSkin().get("default-label-style", Label.LabelStyle.class)
        );
        mLabel.setBounds(0, 0, getWidth(), getHeight());
        this.addActor(mLabel);

        this.setBounds(300, 250, 50, 50);
        stage.addActor(this);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        mLabel.setText(Integer.toString((int)mTower.getCash()));
    }

}
