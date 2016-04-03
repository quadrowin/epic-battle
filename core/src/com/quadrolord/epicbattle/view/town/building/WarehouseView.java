package com.quadrolord.epicbattle.view.town.building;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.screen.AbstractScreen;
import com.quadrolord.epicbattle.screen.town.MapGrid;
import com.quadrolord.epicbattle.view.TextureManager;

/**
 * Created by Quadrowin on 12.03.2016.
 */
public class WarehouseView extends AbstractBuildingView {

    public WarehouseView(final AbstractScreen screen, MapGrid map, AbstractBuildingItem building) {
        super(screen, map, building);
    }

    @Override
    public TextureRegion loadBuildingTexture(TextureManager textures) {
        Texture t = textures.get("town/warehouse128.png");
        return new TextureRegion(t, 0f, 0f, 1f, 1f);
    }

}