package com.quadrolord.epicbattle.view.town.building;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.screen.AbstractScreen;
import com.quadrolord.epicbattle.screen.town.MapGrid;

/**
 * Created by Quadrowin on 31.01.2016.
 */
public class MineView extends AbstractBuildingView {

    public MineView(final AbstractScreen screen, MapGrid map, AbstractBuildingItem building) {
        super(screen, map, building);
    }

    @Override
    public TextureRegion loadBuildingTexture() {
        Texture t = new Texture("town/mine1.png");
        return new TextureRegion(t, 0f, 0f, 1f, 1f);
    }

}