package com.quadrolord.epicbattle.screen.town.button;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Created by Quadrowin on 08.05.2016.
 */
public class UpgradeButtonFactory {

    public static TextButton create(final AbstractScreen screen, final AbstractBuildingItem building, Group parent) {
        TextButton btn = new TextButton("Up", screen.getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
        btn.setBounds(
                parent.getWidth() - 100,
                40,
                40,
                40
        );
        parent.addActor(btn);
        btn.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                building.getTown().levelUp(building, false);
            }

        });
        return btn;
    }

}
