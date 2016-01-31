package com.quadrolord.epicbattle.logic.profile;

import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;

/**
 * Здания на ферме игрока
 */
public class ProfileBuilding {

    private String building;

    private int level;

    public String getBuildingName() {
        return building;
    }

    public int getLevel() {
        return level;
    }

    public void setBuildingClass(Class<? extends AbstractBuildingItem> buildingClass) {
        building = buildingClass.getName();
    }

    public void setLevel(int value) {
        level = value;
    }

}
