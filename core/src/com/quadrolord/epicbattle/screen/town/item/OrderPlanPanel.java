package com.quadrolord.epicbattle.screen.town.item;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.logic.town.building.CraftPlanItem;
import com.quadrolord.epicbattle.screen.AbstractScreen;

import java.util.Iterator;

/**
 * Created by Quadrowin on 22.03.2016.
 */
public class OrderPlanPanel extends Group {

    public OrderPlanPanel(AbstractScreen screen, Group parent, AbstractBuildingItem building) {
        int index = 0;
        for (Iterator<CraftPlanItem> it = building.getCraftPlan().iterator(); it.hasNext(); index++) {
            CraftPlanItem plan = it.next();
            OrderPlanControl ctrl = new OrderPlanControl(screen, this, plan);
            ctrl.setX(index * 60);
        }
        setBounds(0, 100, 300, 50);
        parent.addActor(this);
    }

}
