package com.quadrolord.epicbattle.view.town.building;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.screen.AbstractScreen;
import com.quadrolord.epicbattle.screen.HintScreen;
import com.quadrolord.epicbattle.screen.town.MapGrid;

/**
 * Created by Quadrowin on 30.01.2016.
 */
public class LeftHandTempleView extends BuildingView {

    public LeftHandTempleView(final AbstractScreen screen, MapGrid map, AbstractBuildingItem building) {
        super(screen, map, building);
        setBuildingTexture(new Texture("town/left-hand-tower.png"));

        addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                HintScreen hs = new HintScreen(screen, getX(), getY(), "It's your left hand");
                screen.getAdapter().switchToScreen(hs, false);
            }

        });
    }

    @Override
    public void drawBuilding(Batch batch) {
        float csx = getMap().getCellSideX();
        float csy = getMap().getCellSideY();
        batch.draw(
                getBuildingTexture(),
                0, 0, csx * getBuilding().getSize().x, csy * getBuilding().getSize().y * 2,
                0f, 1f, 1f, 0f
        );
    }

}
