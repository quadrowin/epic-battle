package com.quadrolord.epicbattle.logic.town.building.leveling;

import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingEntity;
import com.quadrolord.epicbattle.logic.town.building.BuildingItem;

/**
 * Created by morph on 23.01.2016.
 */
public class SimpleStrategy extends AbstractStrategy {

    @Override
    public void setLevel(AbstractBuildingEntity info, int level) {
        info.setLevel(level);
    }

    /**
     *
     * @param building
     * @return
     */
    public int getUpgradingCost(BuildingItem building)
    {
        return (building.getLevel() + 1) * 15;

    }

}
