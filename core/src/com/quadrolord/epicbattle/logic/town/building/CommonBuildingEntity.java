package com.quadrolord.epicbattle.logic.town.building;

import com.badlogic.gdx.utils.TimeUtils;
import com.quadrolord.epicbattle.logic.town.resource.ResourceSourceEntity;
import com.quadrolord.epicbattle.logic.town.resource.ResourceSourceItem;

import java.util.Iterator;

/**
 * Created by Quadrowin on 31.01.2016.
 */
public class CommonBuildingEntity extends AbstractBuildingEntity<CommonBuildingItem> {

    @Override
    public Class<? extends CommonBuildingItem> getItemClass() {
        return CommonBuildingItem.class;
    }

    @Override
    public void initItem(CommonBuildingItem item) {
        initItemResources(item);
        item.setSize((int)mSize.x, (int)mSize.y);
    }

    public void initItemResources(CommonBuildingItem item) {
        for (Iterator<ResourceSourceEntity> it = getResources().iterator(); it.hasNext(); ) {
            ResourceSourceEntity rse = it.next();
            ResourceSourceItem rsi = new ResourceSourceItem();
            rsi.setEntity(rse);
            rsi.setLastYield(TimeUtils.millis());
            item.getResources().add(rsi);
        }
    }

}