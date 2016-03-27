package com.quadrolord.epicbattle.logic.town;

import com.quadrolord.epicbattle.logic.thing.ThingCostElement;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingEntity;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.logic.town.building.CraftPlanItem;

/**
 * Created by Quadrowin on 31.01.2016.
 */
public interface TownListener {

    enum BuildingAction {
        /**
         * Недостаточно гемов
         */
        CREATE_NO_GEMS,

        /**
         * Недостаточно ресурсов
         */
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

    void onBuildingSelect(AbstractBuildingItem building);

    void onCancelBuildingMode();

    void onConfirmBuilding();

    void onEnterBuildingMode(AbstractBuildingEntity building);

    void onEnterBuildingMode(AbstractBuildingItem building);

    void onUserActionFail(BuildingAction action);

    /**
     * Недостаток ресурсов при попытке заказе
     * @param cost
     */
    void onOrderResourceLack(ThingCostElement cost);

    void onThingAddToPlan(AbstractBuildingItem building, CraftPlanItem plan);

}
