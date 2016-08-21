package com.quadrolord.epicbattle.screen.town.panel;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.epicbattle.logic.town.MyTown;
import com.quadrolord.epicbattle.logic.town.building.entity.DoodleShop;
import com.quadrolord.epicbattle.logic.town.building.entity.IronMine;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.screen.menu.CampaignSelectScreen;
import com.quadrolord.epicbattle.screen.NewBuildingScreen;

/**
 * Основной GUI города
 */
public class GeneralPanel extends Group {

    public GeneralPanel(final AbstractScreen screen, final MyTown town) {
        Skin skin = screen.getSkin();
        TextButton btnToCampaignSelect = new TextButton("Select campaign", skin.get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnToCampaignSelect.setBounds(10, 0, 150, 50);
        addActor(btnToCampaignSelect);
        btnToCampaignSelect.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.getAdapter().switchToScreen(CampaignSelectScreen.class);
            }

        });

        TextButton btnBuild1 = new TextButton("Build M", skin.get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnBuild1.setBounds(170, 0, 65, 50);
        addActor(btnBuild1);
        btnBuild1.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                town.build(
                        IronMine.class,
                        town.getBuildings().size * 2, 2,
                        false, false, false
                );
            }

        });

        TextButton btnBuild2 = new TextButton("Build D", skin.get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnBuild2.setBounds(240, 0, 65, 50);
        addActor(btnBuild2);
        btnBuild2.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                town.build(
                        DoodleShop.class,
                        town.getBuildings().size * 2, 2,
                        false, false, false
                );
            }

        });

        TextButton btnBuildScreen = new TextButton("Build ?", skin.get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnBuildScreen.setBounds(320, 0, 65, 50);
        addActor(btnBuildScreen);
        btnBuildScreen.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                AbstractScreen scr = new NewBuildingScreen(screen, town);
                screen.getAdapter().switchToScreen(scr, false);
            }

        });

        setBounds(0, 240, 400, 60);
        screen.getStage().addActor(this);
    }

}
