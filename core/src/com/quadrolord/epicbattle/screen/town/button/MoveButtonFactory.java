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
public class MoveButtonFactory {

    public static TextButton create(final AbstractScreen screen, final BuildingItem building, Group parent) {
        TextButton btn = new TextButton("Move", screen.getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
        btn.setBounds(
                parent.getWidth() - 160,
                40,
                40,
                40
        );
        parent.addActor(btn);
        btn.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                building.getTown().enterMovingMode(building);
                screen.getAdapter().switchToScreen(screen.getParentScreen(), true);
            }

        });
        return btn;
    }

}
