package com.quadrolord.epicbattle.logic.town.building.leveling;

import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingEntity;

/**
 * Created by morph on 23.01.2016.
 */
public class SimpleStrategy extends AbstractStrategy {

    @Override
    public void setLevel(AbstractBuildingEntity info, int level) {
        for (int i = 2; i <= level; i++) {
            AbstractBuildingEntity newInfo = (AbstractBuildingEntity)info.getLevelUps().get(i - 2);

            if (newInfo == null) {
                continue;
            }

            apply(info, newInfo);
        }
    }

    protected void apply(AbstractBuildingEntity info, AbstractBuildingEntity newInfo) {
        if (newInfo.getTitle() != "") {
            info.setTitle(newInfo.getTitle());
        }

        if (newInfo.getRequiredLevel() != 0) {
            info.setRequiredLevel(newInfo.getRequiredLevel());
        }

        if (newInfo.getConstructionTime() != 0) {
            info.setConstructionTime(newInfo.getConstructionTime());
        }

        if (newInfo.getCostGem() != 0) {
            info.setCostGem(newInfo.getCostGem());
        }

        if (newInfo.getIcon() != null) {
            info.setIcon(newInfo.getIcon());
        }

        if (newInfo.getLevelingStrategy() != null) {
            info.setLevelingStrategy(newInfo.getLevelingStrategy());
        }

        if (newInfo.getSize() != null) {
            info.setSize(newInfo.getSize());
        }

        if (newInfo.getTileClass() != null) {
            info.setTileClass(newInfo.getTileClass());
        }

        if (newInfo.getViewClass() != null) {
            info.setViewClass(newInfo.getViewClass());
        }

        if (newInfo.getRequiredResources() != null) {
            info.setRequiredResources(newInfo.getRequiredResources());
        }
    }
}
