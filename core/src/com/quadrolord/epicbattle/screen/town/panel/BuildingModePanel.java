package com.quadrolord.epicbattle.screen.town.panel;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.epicbattle.logic.town.MyTown;
import com.quadrolord.ejge.view.AbstractScreen;

/**
 * Created by Quadrowin on 27.02.2016.
 */
public class BuildingModePanel extends Group {

    private TextButton mBtnMoving;

    private TextButton mBtnBuilding;

    public BuildingModePanel(final AbstractScreen screen, final MyTown town) {
        // Подтверждение строительства
        mBtnBuilding = new TextButton("Build here", screen.getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
        mBtnBuilding.setBounds(10, 0, 150, 50);
        addActor(mBtnBuilding);
        mBtnBuilding.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                town.confirmBuilding();
            }

        });

        // Подтверждение перемещения
        mBtnMoving = new TextButton("Move here", screen.getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
        mBtnMoving.setBounds(10, 0, 150, 50);
        addActor(mBtnMoving);
        mBtnMoving.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                town.confirmMoving();
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

    public void showBuildingMode()
    {
        mBtnBuilding.setVisible(true);
        mBtnMoving.setVisible(false);
        setVisible(true);
    }

    public void showMovingMode()
    {
        mBtnBuilding.setVisible(false);
        mBtnMoving.setVisible(true);
        setVisible(true);
    }

}
