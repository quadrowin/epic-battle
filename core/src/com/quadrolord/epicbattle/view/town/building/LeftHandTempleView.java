package com.quadrolord.epicbattle.view.town.building;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuilding;
import com.quadrolord.epicbattle.screen.AbstractScreen;
import com.quadrolord.epicbattle.screen.HintScreen;
import com.quadrolord.epicbattle.screen.town.MapGrid;

/**
 * Created by Quadrowin on 30.01.2016.
 */
public class LeftHandTempleView extends BuildingView {

    public LeftHandTempleView(final AbstractScreen screen, MapGrid map, AbstractBuilding building) {
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
                -csx / 2, -csy / 2, csx * getBuilding().getSize().x / 2, csy * getBuilding().getSize().y,
                0f, 1f, 1f, 0f
        );
    }

}
