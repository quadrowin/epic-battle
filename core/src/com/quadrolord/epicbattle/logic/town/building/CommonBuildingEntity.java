package com.quadrolord.epicbattle.logic.town.building;

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
        item.setSize((int)mSize.x, (int)mSize.y);
    }

}