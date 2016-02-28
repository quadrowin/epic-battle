package com.quadrolord.epicbattle.screen.town.panel;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.epicbattle.logic.town.MyTown;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Created by Quadrowin on 27.02.2016.
 */
public class BuildingModePanel extends Group {

    public BuildingModePanel(final AbstractScreen screen, final MyTown town) {
        // Подтверждение строительства
        TextButton btnBuild = new TextButton("Build here", screen.getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnBuild.setBounds(10, 0, 150, 50);
        addActor(btnBuild);
        btnBuild.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                town.confirmBuilding();
            }

        });

        // отмена
        TextButton btnCancel = new TextButton("Cancel", screen.getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnCancel.setBounds(200, 0, 150, 50);
        addActor(btnCancel);
        btnCancel.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                town.cancelBuildingMode();
            }

        });

        setBounds(0, 240, 400, 60);
        setVisible(false);
        screen.getStage().addActor(this);
    }

}
