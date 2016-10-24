package com.quadrolord.epicbattle.logic.campaign;

/**
 * Created by Quadrowin on 11.01.2016.
 */
abstract public class AbstractCampaign {

    private String mDir;

    private String mIcon;

    private String mName;

    abstract public Level[] getLevels();

    abstract public int getIndex();

    public String getDir() {
        return mDir;
    }

    public String getName() {
        return mName;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setDir(String dir) {
        mDir = dir;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

}
