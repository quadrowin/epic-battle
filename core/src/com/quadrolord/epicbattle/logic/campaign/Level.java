package com.quadrolord.epicbattle.logic.campaign;

/**
 * Created by Quadrowin on 11.01.2016.
 */
public class Level {

    private AbstractCampaign mCampaign;

    private int mIndex;

    private String name;

    private EnemyTower enemyTower;

    public AbstractCampaign getCampaign() {
        return mCampaign;
    }

    public EnemyTower getEnemyTower() {
        return enemyTower;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return mIndex;
    }

    public void setCampaign(AbstractCampaign campaign) {
        mCampaign = campaign;
    }

    public void setName(String val) {
        name = val;
    }

    public void setIndex(int index) {
        mIndex = index;
    }

}
