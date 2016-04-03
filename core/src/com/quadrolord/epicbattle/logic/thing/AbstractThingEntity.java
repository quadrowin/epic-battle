package com.quadrolord.epicbattle.logic.thing;

import com.quadrolord.epicbattle.logic.configurable.AbstractEntity;

/**
 * Created by Quadrowin on 20.03.2016.
 */
abstract public class AbstractThingEntity extends AbstractEntity<ThingItem> {

    /**
     * Стоимость крафта
     */
    private ThingCost mCost = new ThingCost();

    /**
     * Длительность крафта в секундах
     */
    private long mCraftTime;

    /**
     * Предмет стакается и не занимает лишнего места в инветаре
     */
    private boolean mStackable = false;

    private String mShort;

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

    public long getCraftTime() {
        return mCraftTime;
    }

    public void setCraftTime(long craftTime) {
        mCraftTime = craftTime;
    }

    public String getShort() {
        return mShort;
    }

    public void setShort(String aShort) {
        mShort = aShort;
    }

    public boolean isStackable() {
        return mStackable;
    }

    public void setStackable(boolean stackable) {
        mStackable = stackable;
    }

}
