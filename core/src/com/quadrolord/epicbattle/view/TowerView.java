package com.quadrolord.epicbattle.view;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.quadrolord.epicbattle.logic.tower.Tower;
import com.quadrolord.epicbattle.screen.AbstractScreen;
import com.quadrolord.epicbattle.screen.battle.TowerHp;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class TowerView extends Group {

    private Tower mTower;

    private ImageButton mView;

    private TowerHp mHpLabel;

    public TowerView(Tower tower, AbstractScreen screen) {
        mTower = tower;
        tower.setViewObject(this);

        setBounds(mTower.getX() - tower.getWidth() / 2, 45, tower.getWidth(), tower.getWidth());
        screen.getStage().addActor(this);

        mView = new ImageButton(screen.getSkin().getDrawable("tower"));
        mView.setBounds(0, 0, getWidth(), getHeight());
        addActor(mView);
    }

    public void setHpLabel(TowerHp hpLabel) {
        mHpLabel = hpLabel;
    }

    public TowerHp getHpLabel() {
        return mHpLabel;
    }
}
