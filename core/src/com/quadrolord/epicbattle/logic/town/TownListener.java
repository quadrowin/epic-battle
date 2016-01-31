package com.quadrolord.epicbattle.logic.town;

import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;

/**
 * Created by Quadrowin on 31.01.2016.
 */
public interface TownListener {

    enum BuildingAction {
        CREATE_NO_RESOURCES,
        CREATE_NO_LEVEL,
        CREATE_NO_PLACE,

        LEVEL_NO_RESOURCES,
        LEVEL_IN_UPDATING,
        LEVEL_BAD_USER_LEVEL,
        LEVEL_ALREADY_MAX,
    }

    void onBuildingAdd(AbstractBuildingItem building);

    void onBuildingChange(AbstractBuildingItem building);

    void onBuildingRemove(AbstractBuildingItem building);

    void onUserActionFail(BuildingAction action);

}
