package com.quadrolord.epicbattle.view.town.building;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.quadrolord.epicbattle.logic.town.building.BuildingItem;
import com.quadrolord.epicbattle.screen.AbstractScreen;
import com.quadrolord.epicbattle.screen.town.MapGrid;
import com.quadrolord.epicbattle.view.TextureManager;

/**
 * Created by Quadrowin on 31.01.2016.
 */
public class RightLegTempleView extends AbstractBuildingView {

    public RightLegTempleView(final AbstractScreen screen, MapGrid map, BuildingItem building) {
        super(screen, map, building);
    }

    @Override
    public void act(float delta) {
        float csx = getMap().getCellSideX();
        float csy = getMap().getCellSideY();
        setSize(csx * getBuilding().getSize().x, csy * getBuilding().getSize().y * 2);
    }

    @Override
    public void drawBuilding(Batch batch) {
        float csx = getMap().getCellSideX();
        float csy = getMap().getCellSideY();
        batch.draw(
                getBuildingTexture(),
                0, csx / 4, csx * getBuilding().getSize().x, csy * getBuilding().getSize().y * 2
        );
    }

    @Override
    public TextureRegion loadBuildingTexture(TextureManager textures) {
        Texture t = textures.get("town/left-hand-tower.png");
        return new TextureRegion(t, 1f, 0f, 0f, 1f);
    }

}
