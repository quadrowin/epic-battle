package com.quadrolord.epicbattle.view.town.building;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.quadrolord.epicbattle.logic.town.building.Building;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Created by morph on 17.01.2016.
 */
public class BuildingView extends Group {

    protected Animation mAnimation;

    protected Texture mIcon;

    private Building mBuilding;

    private ImageButton mBtn;

    public BuildingView(final AbstractScreen screen, Building building) {
        mBuilding = building;

        setBounds(
                building.getX() - building.getWidth() / 2,
                building.getY() - building.getHeight() / 2,
                building.getWidth(),
                building.getHeight()
        );

        Texture txTower = new Texture("tower.png");
        mBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion(txTower)));
        mBtn.setBounds(0, 0, getWidth(), getHeight());
        addActor(mBtn);

        screen.getStage().addActor(this);
    }

}
