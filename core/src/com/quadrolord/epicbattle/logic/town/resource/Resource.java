package com.quadrolord.epicbattle.logic.town.resource;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by morph on 17.01.2016.
 */
public abstract class Resource {
    protected Texture mIcon;
    protected String mTitle;

    public Resource() {

    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Texture getIcon() {
        return mIcon;
    }

    public void setIcon(Texture icon) {
        mIcon = icon;
    }
}
