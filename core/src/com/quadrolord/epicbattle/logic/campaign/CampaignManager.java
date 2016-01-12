package com.quadrolord.epicbattle.logic.campaign;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

/**
 * Created by Quadrowin on 11.01.2016.
 */
public class CampaignManager {

    private AbstractCampaign[] mCampaigns;

    public AbstractCampaign[] getCampaigns() {
        if (mCampaigns == null) {
            Json json = new Json();
            json.addClassTag("Campaign", CampaignItem.class);
            mCampaigns = json.fromJson(AbstractCampaign[].class, Gdx.files.internal("campaign/index.json"));
        }
        return mCampaigns;
    }

    public Level getLevel(int campaignIndex, int levelIndex) {
        return getCampaigns()[campaignIndex].getLevels()[levelIndex];
    }

    public Level getNextLevel(Level level) {
        AbstractCampaign camp = level.getCampaign();
        int levelIndex = level.getIndex() + 1;
        Level[] levels = camp.getLevels();
        if (levelIndex >= levels.length) {
            // TODO Go to next campaign
            levelIndex = 0;
        }
        return levels[levelIndex];
    }

}
