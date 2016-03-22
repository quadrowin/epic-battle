package com.quadrolord.epicbattle.logic.thing;

import com.quadrolord.epicbattle.logic.configurable.AbstractEntity;

/**
 * Created by Quadrowin on 20.03.2016.
 */
abstract public class AbstractThingEntity extends AbstractEntity<ThingItem> {

    private ThingCost mCost = new ThingCost();

    private String mTitle;

    private String mImage;

    @Override
    public Class<? extends ThingItem> getItemClass() {
        return ThingItem.class;
    }

    public String getTitle() {
        return mTitle;
    }

    @Override
    public void initItem(ThingItem item) {

    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public ThingCost getCost() {
        return mCost;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }
}
