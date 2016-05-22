package com.quadrolord.epicbattle.screen.town.item;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.quadrolord.epicbattle.logic.town.building.BuildingItem;
import com.quadrolord.epicbattle.logic.town.building.CraftPlanItem;
import com.quadrolord.epicbattle.logic.town.listener.OnThingAddToPlan;
import com.quadrolord.epicbattle.screen.AbstractScreen;

import java.util.Iterator;

/**
 * Created by Quadrowin on 22.03.2016.
 */
public class OrderPlanPanel extends Group implements OnThingAddToPlan {

    private int mPlanLength = 0;

    private AbstractScreen mScreen;

    public OrderPlanPanel(AbstractScreen screen, Group parent, BuildingItem building) {
        mScreen = screen;
        for (Iterator<CraftPlanItem> it = building.getCraftPlan().iterator(); it.hasNext();) {
            CraftPlanItem plan = it.next();
            onThingAddToPlan(building, plan);
        }
        setBounds(0, 100, 300, 50);
        parent.addActor(this);
    }

    @Override
    public void onThingAddToPlan(BuildingItem building, CraftPlanItem plan) {
        OrderPlanControl ctrl = new OrderPlanControl(mScreen, this, plan);
        ctrl.setX(mPlanLength * 60);
        mPlanLength++;
    }

}
