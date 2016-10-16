package com.quadrolord.epicbattle.view;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.quadrolord.epicbattle.RM;
import com.quadrolord.epicbattle.logic.tower.Tower;
import com.quadrolord.ejge.view.AbstractScreen;
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

        setBounds(mTower.getX() - tower.getWidth() / 2, tower.getY(), tower.getWidth(), tower.getHeight());
        screen.getStage().addActor(this);

        mView = new ImageButton(new TextureRegionDrawable(new TextureRegion(RM.getTextures().get("tower.png"))));
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
