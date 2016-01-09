package com.quadrolord.epicbattle.view;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.quadrolord.epicbattle.logic.Tower;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class TowerView extends Group {

    private Tower mTower;

    private ImageButton mView;

    public TowerView(Tower tower, AbstractScreen screen) {
        mTower = tower;
        tower.setViewObject(this);

        setBounds(mTower.getX(), 10, mTower.getWidth(), 90);
        screen.getStage().addActor(this);

        mView = new ImageButton(screen.getSkin().getDrawable("tower"));
        mView.setBounds(0, 0, getWidth(), getHeight());
        addActor(mView);
    }

}
