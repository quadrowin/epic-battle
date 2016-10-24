package com.quadrolord.epicbattle.logic.campaign;

/**
 * Created by Quadrowin on 11.01.2016.
 */
public class CampaignManager {

    private AbstractCampaign[] mCampaigns;

    private void addCampaign(int index, String name, String icon, String dir) {
        CampaignItem camp = new CampaignItem();
        camp.setIndex(index + 1);
        camp.setName(name);
        camp.setIcon(icon);
        camp.setDir(dir);
        mCampaigns[index] = camp;
    }

    public AbstractCampaign[] getCampaigns() {
        if (mCampaigns == null) {
            mCampaigns = new AbstractCampaign[3];
            addCampaign(0, "Let the combat begin", "pump.png", "let_the_combat_begin");
            addCampaign(1, "An endless adventure", "metal_sphere.png", "an_endless_adventure");
            addCampaign(2, "Wheelrmageddon", "wheel_on_fire.png", "wheelrmageddon");
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
