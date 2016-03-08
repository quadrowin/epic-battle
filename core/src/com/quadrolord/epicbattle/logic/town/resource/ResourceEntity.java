package com.quadrolord.epicbattle.logic.town.resource;

import com.quadrolord.epicbattle.logic.configurable.AbstractEntity;

/**
 * Вид ресурса
 * Created by morph on 17.01.2016.
 */
public abstract class ResourceEntity extends AbstractEntity<ResourceItem> {
    protected String mIcon;
    protected String mTitle;
    protected String mShort;

    public ResourceEntity() {

    }

    @Override
    public Class getItemClass() {
        return ResourceItem.class;
    }

    @Override
    public void initItem(ResourceItem item) {

    }

    public String getShort() {
        return mShort;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }
}
