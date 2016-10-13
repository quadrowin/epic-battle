package com.quadrolord.epicbattle.screen.battle;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.quadrolord.epicbattle.RM;
import com.quadrolord.epicbattle.logic.tower.Tower;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.screen.SES;

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
                RM.getLabelStyle()
        );
        mLabel.setFontScale(0.7f);
        mLabel.setAlignment(Align.center, Align.bottom);
        mLabel.setBounds(0, 0, tower.getWidth(), SES.BUTTON_HEIGHT);
        addActor(mLabel);

        setBounds(
                mTower.getX() - mTower.getWidth() / 2, mTower.getY() + mTower.getHeight(),
                mTower.getWidth(), SES.BUTTON_HEIGHT
        );

        screen.getStage().addActor(this);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        mLabel.setText(Integer.toString((int)mTower.getHp()));
    }

}
