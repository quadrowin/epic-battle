package com.quadrolord.epicbattle.logic.campaign;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

/**
 * Created by Quadrowin on 11.01.2016.
 */
public class CampaignItem extends AbstractCampaign {

    private int mIndex;

    private Level[] mLevels;

    public String getLevelsFile() {
        return "campaign/" + getDir() + "/levels.json";
    }

    @Override
    public Level[] getLevels() {
        if (mLevels == null) {
            Json json = new Json();
            json.addClassTag("Level", Level.class);
            json.addClassTag("Unit", EnemyUnit.class);
            json.addClassTag("EnemyTower", EnemyTower.class);
            mLevels = json.fromJson(Level[].class, Gdx.files.internal(getLevelsFile()));
            for (int i = 0; i < mLevels.length; i++) {
                mLevels[i].setCampaign(this);
                mLevels[i].setIndex(i);
            }
        }
        return mLevels;
    }

    @Override
    public int getIndex() {
        if (mIndex == 0) {
            throw new RuntimeException("Campaign index not received");
        }
        return mIndex;
    }

    public void setIndex(int index) {
        mIndex = index;
    }

}
