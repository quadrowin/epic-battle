package com.quadrolord.epicbattle.view.town.tile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.quadrolord.epicbattle.logic.town.tile.Tile;

/**
 * Created by morph on 17.01.2016.
 */
public class TileView extends Group {
    protected Texture mTexture;
    protected Tile mTile;

    public Texture getTexture() {
        return mTexture;
    }

    public void setTexture(Texture texture) {
        mTexture = texture;
    }
}
