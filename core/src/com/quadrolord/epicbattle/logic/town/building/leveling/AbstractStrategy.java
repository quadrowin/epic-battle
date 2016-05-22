package com.quadrolord.epicbattle.logic.town.building.leveling;

import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingEntity;
import com.quadrolord.epicbattle.logic.town.building.BuildingItem;

/**
 * Created by morph on 23.01.2016.
 */
public abstract class AbstractStrategy {

    /**
     *
     * @param building
     * @return
     */
    public boolean canLevelUp(BuildingItem building) {
        return building.getLevel() < 10;
    }

    public abstract void setLevel(AbstractBuildingEntity info, int level);

}
