package com.quadrolord.epicbattle.logic.town.building.leveling;

import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingEntity;
import com.quadrolord.epicbattle.logic.town.building.ResourceBuildingEntity;

/**
 * Created by morph on 24.01.2016.
 */
public class ResourceBuildingStrategy extends SimpleStrategy {
    @Override
    protected void apply(AbstractBuildingEntity info, AbstractBuildingEntity newInfo) {
        super.apply(info, newInfo);

        if (((ResourceBuildingEntity)newInfo).getYieldTime() != 0) {
            ((ResourceBuildingEntity)info).setYieldTime(((ResourceBuildingEntity)newInfo).getYieldTime());
        }
    }
}
