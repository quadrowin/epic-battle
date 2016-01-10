package com.quadrolord.epicbattle.screen.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.quadrolord.epicbattle.logic.Tower;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class TowerHp extends Group {

    private Label mLabel;

    private Tower mTower;

    public TowerHp(Tower tower, AbstractScreen screen) {
        mTower = tower;
        mLabel = new Label(
                Integer.toString((int)mTower.getHp()),
                screen.getSkin().get("default-label-style", Label.LabelStyle.class)
        );
        mLabel.setBounds(0, 0, getWidth(), getHeight());
        this.addActor(mLabel);

        float x = mTower.getX() + (mTower.getWidth() - mLabel.getPrefWidth()) / 2;

        this.setBounds(x, 100, 50, 50);
        screen.getStage().addActor(this);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        mLabel.setText(Integer.toString((int)mTower.getHp()));
    }

}
