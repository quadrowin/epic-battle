package com.quadrolord.epicbattle.logic.profile;

import com.quadrolord.epicbattle.logic.town.building.BuildingItem;

/**
 * Здания на ферме игрока
 */
public class ProfileBuilding {

    private String building;

    private int level;

    private boolean rotated;

    private int x;

    private int y;

    public String getBuildingName() {
        return building;
    }

    public int getLevel() {
        return level;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isRotated() {
        return rotated;
    }

    public void setBuildingClass(Class<? extends BuildingItem> buildingClass) {
        building = buildingClass.getName();
    }

    public void setLevel(int value) {
        level = value;
    }

    public void setRotated(boolean value) {
        rotated = value;
    }

    public void setX(int value) {
        x = value;
    }

    public void setY(int value) {
        y = value;
    }

}
