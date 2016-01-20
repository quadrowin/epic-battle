package com.quadrolord.epicbattle.logic.town.building;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.quadrolord.epicbattle.logic.town.building.leveling.LevelingDto;
import com.quadrolord.epicbattle.logic.town.resource.Resource;
import com.quadrolord.epicbattle.logic.town.tile.Tile;
import com.quadrolord.epicbattle.view.town.building.BuildingView;

/**
 * Created by morph on 17.01.2016.
 */
public class BuildingInfo {
    protected Vector2 mSize;
    protected Class<? extends BuildingView> mViewClass;
    protected Texture mIcon;
    protected Array<LevelingDto> mLevelUps = new Array<LevelingDto>();
    protected Class<? extends Tile> mTileClass;
    protected ArrayMap<Resource, Integer> mRequiredResources = new ArrayMap<Resource, Integer>();
    protected int mRequiredLevel = 1;
    protected float mYieldTime;

    public ArrayMap<Resource, Integer> getRequiredResources() {
        return mRequiredResources;
    }

    public int getRequiredLevel() {
        return mRequiredLevel;
    }

    public float getYieldTime() {
        return mYieldTime;
    }

}
