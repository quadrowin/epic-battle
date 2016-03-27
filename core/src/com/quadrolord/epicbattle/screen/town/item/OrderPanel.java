package com.quadrolord.epicbattle.screen.town.item;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.quadrolord.epicbattle.logic.thing.AbstractThingEntity;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.screen.AbstractScreen;

import java.util.Iterator;

/**
 * Created by Quadrowin on 27.03.2016.
 */
public class OrderPanel extends Group {

    public OrderPanel(final AbstractScreen parentScreen, Group parent, AbstractBuildingItem building) {
        int index = 0;
        for (Iterator<AbstractThingEntity> it = building.getInfo().getAvailableThings().iterator(); it.hasNext(); index++) {
            AbstractThingEntity thing = it.next();
            OrderButton btn = new OrderButton(parentScreen, this, building, thing);
            btn.setX(index * 60);
        }
        setBounds(0, 150, 300, 50);
        parent.addActor(this);
    }

}
