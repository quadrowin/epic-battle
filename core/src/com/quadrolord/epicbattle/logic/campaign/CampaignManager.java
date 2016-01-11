package com.quadrolord.epicbattle.logic.campaign;

/**
 * Created by Quadrowin on 11.01.2016.
 */
public class CampaignManager {

    private AbstractCampaign[] mCampaigns;

    public AbstractCampaign[] getCampaigns() {
        if (mCampaigns == null) {
            mCampaigns = new AbstractCampaign[] {
                    new FirstCampaign(),
            };
        }
        return mCampaigns;
    }

    public Level getLevel(int campaignIndex, int levelIndex) {
        return getCampaigns()[campaignIndex].getLevels()[levelIndex];
    }

}
