package com.quadrolord.epicbattle.screen.town.button;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.epicbattle.logic.town.building.BuildingItem;
import com.quadrolord.ejge.view.AbstractScreen;

/**
 * Created by Quadrowin on 08.05.2016.
 */
public class UpgradeControl extends Group {

    private BuildingItem mBuilding;

    private TextButton mButton;

    public UpgradeControl(final AbstractScreen screen, final BuildingItem building, Group parent) {
        mBuilding = building;
        String title;
        if (building.isInUpgrading()) {
            title = "Cancel Up";
        } else {
            title = "Start Up";
        }
        TextButton btn = new TextButton(title, screen.getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
        addActor(btn);
        btn.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (building.isInUpgrading()) {
                    building.getTown().terminateUpgrading(building);
                    updateState();
                } else {
                    building.getTown().levelUp(building, false);
                    updateState();
                }
            }

        });
        mButton = btn;
        setBounds(
                parent.getWidth() - 100,
                40,
                40,
                40
        );
        mButton.setBounds(0, 0, getWidth(), getHeight());
        parent.addActor(this);
    }

    public void updateState() {
        String title;
        if (mBuilding.isInUpgrading()) {
            title = "Cancel Up";
        } else {
            title = "Start Up";
        }
        mButton.setText(title);
    }

}
