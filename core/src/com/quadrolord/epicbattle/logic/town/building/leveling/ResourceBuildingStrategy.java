package com.quadrolord.epicbattle.logic.town.building.leveling;

import com.quadrolord.epicbattle.logic.town.building.BuildingInfo;
import com.quadrolord.epicbattle.logic.town.building.ResourceBuildingInfo;

/**
 * Created by morph on 24.01.2016.
 */
public class ResourceBuildingStrategy extends SimpleStrategy {
    @Override
    protected void apply(BuildingInfo info, BuildingInfo newInfo) {
        super.apply(info, newInfo);

        if (((ResourceBuildingInfo)newInfo).getYieldTime() != 0) {
            ((ResourceBuildingInfo)info).setYieldTime(((ResourceBuildingInfo)newInfo).getYieldTime());
        }
    }
}
