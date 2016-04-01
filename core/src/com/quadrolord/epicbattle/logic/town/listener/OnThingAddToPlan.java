package com.quadrolord.epicbattle.logic.town.listener;

import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.logic.town.building.CraftPlanItem;

/**
 * Created by Quadrowin on 01.04.2016.
 */
public interface OnThingAddToPlan {

    void onThingAddToPlan(AbstractBuildingItem building, CraftPlanItem plan);

}
